package br.com.senacsp.tads.stads4ma.library.presentation;

import br.com.senacsp.tads.stads4ma.library.domainmodel.Click;
import br.com.senacsp.tads.stads4ma.library.domainmodel.Link;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.ClickRepository;
import br.com.senacsp.tads.stads4ma.library.domainmodel.repository.LinkRepository;
import br.com.senacsp.tads.stads4ma.library.service.GeoLocationService;
import br.com.senacsp.tads.stads4ma.library.service.UserAgentService;
import br.com.senacsp.tads.stads4ma.library.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Controlador para redirecionamento de links encurtados.
 * - Registra click no link com informações completas (região, cidade, dispositivo, referer).
 * - Redireciona o usuário para a URL original.
 */
@RestController
@RequestMapping("/")
public class RedirectController {

    private final LinkRepository linkRepository;
    private final ClickRepository clickRepository;
    private final GeoLocationService geoLocationService;
    private final UserAgentService userAgentService;
    private final JmsTemplate queueJmsTemplate;

    public RedirectController(
            LinkRepository linkRepository,
            ClickRepository clickRepository,
            GeoLocationService geoLocationService,
            UserAgentService userAgentService,
            @Qualifier("queueJmsTemplate") JmsTemplate queueJmsTemplate) {
        this.linkRepository = linkRepository;
        this.clickRepository = clickRepository;
        this.geoLocationService = geoLocationService;
        this.userAgentService = userAgentService;
        this.queueJmsTemplate = queueJmsTemplate;
    }

    /**
     * @apiNote Registra click no link e redireciona o usuário.
     * @param shortCode Código curto do link.
     * @param request HttpServletRequest para capturar informações do cliente.
     * @return Redirecionamento HTTP 302 para a URL original.
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectLink(
            @PathVariable String shortCode,
            HttpServletRequest request) {
        
        // Busca o link pelo shortCode
        Link link = linkRepository.findByShortCode(shortCode)
                .orElse(null);

        if (link == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Link não encontrado");
        }

        // Valida se o link está ativo
        if (!link.isActive()) {
            return ResponseEntity.status(HttpStatus.GONE)
                    .body("Link inativo");
        }

        // Valida se o link não expirou
        if (link.getExpiresAt() != null && link.getExpiresAt().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.GONE)
                    .body("Link expirado");
        }

        // Captura informações do request
        String clientIp = HttpRequestUtil.getClientIpAddress(request);
        String userAgent = HttpRequestUtil.getUserAgent(request);
        String referer = HttpRequestUtil.getReferer(request);

        // Identifica o dispositivo
        String device = userAgentService.identifyDevice(userAgent);

        // Obtém informações de geolocalização
        Map<String, String> location = geoLocationService.getLocationByIp(clientIp);
        String region = location.get("region");
        String city = location.get("city");

        // Registra o clique com todas as informações
        Click click = Click.builder()
                .link(link)
                .date(LocalDate.now())
                .clickedAt(LocalDateTime.now())
                .region(region)
                .city(city)
                .device(device)
                .referer(referer)
                .build();

        clickRepository.save(click);

        // Envia mensagem para a fila de redirecionamentos
        String redirectMessage = String.format(
            "[REDIRECT] Device: %s | Hora: %s | Link: %s | Redirecionado para: %s",
            device,
            LocalDateTime.now(),
            shortCode,
            link.getOriginalUrl()
        );
        queueJmsTemplate.convertAndSend("shortlink.redirect.queue", redirectMessage);

        // Redireciona para a URL original
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", link.getOriginalUrl())
                .build();
    }
}

