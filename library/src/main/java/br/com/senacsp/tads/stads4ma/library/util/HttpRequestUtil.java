package br.com.senacsp.tads.stads4ma.library.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Utilitário para extrair informações do HttpServletRequest.
 */
public class HttpRequestUtil {

    /**
     * Obtém o endereço IP real do cliente, considerando proxies e load balancers.
     * 
     * @param request HttpServletRequest
     * @return IP do cliente
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // Se houver múltiplos IPs (X-Forwarded-For pode conter vários), pega o primeiro
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    /**
     * Obtém o User-Agent do request.
     * 
     * @param request HttpServletRequest
     * @return User-Agent ou "Desconhecido" se não disponível
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? userAgent : "Desconhecido";
    }

    /**
     * Obtém o Referer do request.
     * 
     * @param request HttpServletRequest
     * @return Referer ou null se não disponível
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }
}

