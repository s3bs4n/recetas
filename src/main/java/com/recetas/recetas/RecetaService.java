// RecetaService.java

package com.recetas.recetas;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RecetaService {

    private static final String API_URL = "http://localhost:8082/api/recetas/buscar";
    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3MzM3NjA1NTYsImV4cCI6MTczNDYyNDU1Nn0.W2on1NTBLh6c-SP55jEQ0GOt7I1Kuq8ajQQKmB0cGV-ntBf2SUHWWT4OdNa6F6P2ra0rxQT4kH8_9hItHDfhcg";

    public Map<String, Object> obtenerDetallesReceta(String nombreReceta) {
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
        RestTemplate restTemplate = new RestTemplate();

        // URL del endpoint
        String url = "http://localhost:8082/api/recetas/nombres";

        // Configura encabezados si es necesario (por ejemplo, autorización)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN); // Asegúrate de que TOKEN es válido
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realiza la solicitud GET al endpoint
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

        // Devuelve la lista de nombres
        return response.getBody();
    }

    //////////////////////////////////////////////////////////////////////////

    // Método para crear una nueva receta
    public ResponseEntity<Receta> crearReceta(Receta nuevaReceta) {
        RestTemplate restTemplate = new RestTemplate();

        // Configura encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN); // Asegúrate de que TOKEN es válido
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Configura la solicitud
        HttpEntity<Receta> request = new HttpEntity<>(nuevaReceta, headers);

        // URL del endpoint
        String url = "http://localhost:8082/api/recetas/crear";

        // Realiza la solicitud POST
        return restTemplate.postForEntity(url, request, Receta.class);
    }



    ///////////////////////////////////////////////////////////////////////////////////
    /// 


    public ResponseEntity<Comentario> agregarComentario(Comentario comentario) {
        RestTemplate restTemplate = new RestTemplate();
    
        // Configura encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        // Configura la solicitud
        HttpEntity<Comentario> request = new HttpEntity<>(comentario, headers);
    
        // URL del endpoint
        String url = "http://localhost:8082/api/recetas/comentarios";
    
        // Realiza la solicitud POST
        return restTemplate.postForEntity(url, request, Comentario.class);
    }
    











}
