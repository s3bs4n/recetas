// RecetaService.java

package com.recetas.recetas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class RecetaService {

    private static final String API_URL = "http://localhost:8082/api/recetas/buscar";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MzA3NTgzMTEsImV4cCI6MTczMTYyMjMxMX0.FyT4OA3yr9WQwQHaGlTVp9A5u3UY2m7IZyxN_qw4smozDNuVwGGCOOUs85j1XFCGc9MKbcawNQhkoPVxUpK0dQ";

    public Map<String, Object> obtenerDetalleReceta(String nombreReceta) {
        RestTemplate restTemplate = new RestTemplate();

        // Construye la URL con el parámetro de búsqueda
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("nombre", nombreReceta)
                .toUriString();

        // Configura el encabezado Authorization con el token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realiza la solicitud GET a la API
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }
    
    public List<String> obtenerRecetas() {
        // Simulación de recetas; este método debería conectar con una API o base de datos
        return List.of("Paella", "Sushi", "Tacos al Pastor", "Pizza Margherita", "Pad Thai", "Ceviche", "Falafel", "Ramen", "Chili con Carne", "Brigadeiro");
    }
}
