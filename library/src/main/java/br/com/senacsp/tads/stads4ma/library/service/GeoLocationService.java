package br.com.senacsp.tads.stads4ma.library.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Serviço para obter informações de geolocalização baseado no IP do cliente.
 * Utiliza a API gratuita ip-api.com (sem necessidade de chave de API).
 */
@Service
public class GeoLocationService {

    private static final String GEO_API_URL = "http://ip-api.com/json/{ip}?fields=status,message,country,regionName,city";
    private final RestTemplate restTemplate;

    public GeoLocationService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Obtém informações de geolocalização baseado no IP.
     * 
     * @param ipAddress Endereço IP do cliente
     * @return Map com informações de região e cidade, ou null se não conseguir obter
     */
    public Map<String, String> getLocationByIp(String ipAddress) {
        try {
            // Ignora IPs locais/privados
            if (isLocalOrPrivateIp(ipAddress)) {
                return createDefaultLocation();
            }

            GeoLocationResponse response = restTemplate.getForObject(
                    GEO_API_URL,
                    GeoLocationResponse.class,
                    ipAddress
            );

            if (response != null && "success".equals(response.status)) {
                Map<String, String> location = new HashMap<>();
                location.put("region", response.regionName != null ? response.regionName : "Desconhecido");
                location.put("city", response.city != null ? response.city : "Desconhecido");
                return location;
            }
        } catch (Exception e) {
            // Log do erro (em produção, usar um logger adequado)
            System.err.println("Erro ao obter geolocalização: " + e.getMessage());
        }
        
        return createDefaultLocation();
    }

    private boolean isLocalOrPrivateIp(String ip) {
        if (ip == null) return true;
        return ip.startsWith("127.") || 
               ip.startsWith("192.168.") || 
               ip.startsWith("10.") || 
               ip.startsWith("172.16.") || 
               ip.equals("localhost") ||
               ip.equals("0:0:0:0:0:0:0:1") ||
               ip.equals("::1");
    }

    private Map<String, String> createDefaultLocation() {
        Map<String, String> location = new HashMap<>();
        location.put("region", "Não identificado");
        location.put("city", "Não identificado");
        return location;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class GeoLocationResponse {
        @JsonProperty("status")
        public String status;
        
        @JsonProperty("message")
        public String message;
        
        @JsonProperty("regionName")
        public String regionName;
        
        @JsonProperty("city")
        public String city;
    }
}

