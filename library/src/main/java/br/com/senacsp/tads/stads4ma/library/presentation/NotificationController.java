package br.com.senacsp.tads.stads4ma.library.presentation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para enviar notificações ao tópico.
 * Permite testar o sistema de mensageria enviando mensagens manualmente.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final JmsTemplate topicJmsTemplate;

    public NotificationController(@Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate) {
        this.topicJmsTemplate = topicJmsTemplate;
    }

    /**
     * Envia uma notificação para o tópico.
     * Ambos os consumers (A e B) receberão a mensagem.
     * 
     * @param message Mensagem a ser enviada
     * @return Confirmação de envio
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody String message) {
        topicJmsTemplate.convertAndSend("shortlink.notification.topic", message);
        return ResponseEntity.ok("Notificação enviada para o tópico. Ambos os consumers receberão a mensagem.");
    }

    /**
     * Envia múltiplas notificações para o tópico.
     * 
     * @param count Número de mensagens a enviar
     * @return Confirmação de envio
     */
    @PostMapping("/send/{count}")
    public ResponseEntity<String> sendMultipleNotifications(@PathVariable int count) {
        for (int i = 1; i <= count; i++) {
            String message = "Notificação #" + i + " - Sistema ShortLink está funcionando!";
            topicJmsTemplate.convertAndSend("shortlink.notification.topic", message);
        }
        return ResponseEntity.ok(String.format("%d notificações enviadas para o tópico.", count));
    }
}

