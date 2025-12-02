package br.com.senacsp.tads.stads4ma.library.service;

import org.springframework.stereotype.Service;

/**
 * Serviço para identificar o tipo de dispositivo baseado no User-Agent.
 * Faz parsing básico do User-Agent string.
 */
@Service
public class UserAgentService {

    /**
     * Identifica o tipo de dispositivo baseado no User-Agent.
     * 
     * @param userAgent String do User-Agent do navegador
     * @return Tipo de dispositivo (Desktop, Mobile, Tablet, Bot, ou Desconhecido)
     */
    public String identifyDevice(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) {
            return "Desconhecido";
        }

        String ua = userAgent.toLowerCase();

        // Detecta bots/crawlers
        if (ua.contains("bot") || ua.contains("crawler") || ua.contains("spider")) {
            return "Bot";
        }

        // Detecta tablets
        if (ua.contains("tablet") || 
            ua.contains("ipad") || 
            (ua.contains("android") && !ua.contains("mobile"))) {
            return "Tablet";
        }

        // Detecta mobile
        if (ua.contains("mobile") || 
            ua.contains("android") || 
            ua.contains("iphone") || 
            ua.contains("ipod") || 
            ua.contains("blackberry") || 
            ua.contains("windows phone")) {
            return "Mobile";
        }

        // Detecta desktop
        if (ua.contains("windows") || 
            ua.contains("macintosh") || 
            ua.contains("linux") || 
            ua.contains("x11")) {
            return "Desktop";
        }

        return "Desconhecido";
    }
}

