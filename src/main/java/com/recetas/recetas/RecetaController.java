// RecetaController.java

package com.recetas.recetas;

import com.recetas.recetas.RecetaService;
import com.recetas.recetas.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    // @GetMapping("/home")
    // public String mostrarRecetas(Model model) {
    // List<String> recetas = recetaService.obtenerRecetas();
    // model.addAttribute("recetas", recetas);
    // return "home"; // Página principal donde se muestran las recetas
    // }

    @GetMapping("/home")
    public String mostrarRecetas(Model model) {
        List<String> recetas = recetaService.obtenerRecetas(); // Obtener nombres desde la API
        System.out.println(recetas); // Depuración: imprime los nombres para verificar
        model.addAttribute("recetas", recetas); // Pasar los nombres al modelo
        return "home"; // Retorna la página de recetas
    }

    @GetMapping("/receta/detalles")
    public String obtenerDetalleReceta(@RequestParam("nombre") String nombreReceta, Model model) {
        Map<String, Object> recetaDetalles = recetaService.obtenerDetallesReceta(nombreReceta);
        model.addAttribute("receta", recetaDetalles);
        return "detalleReceta"; // Página para los detalles de la receta
    }

    // @GetMapping("/crearReceta")
    // public String mostrarFormularioCrear() {
    // return "crearReceta"; // Busca en
    // src/main/resources/templates/crearReceta.html
    // }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    @GetMapping("/crearReceta")
    public String mostrarFormulario() {
        return "crearReceta"; // Renderiza el archivo crearReceta.html
    }

    // @PostMapping("/crearReceta")
    // public String procesarFormulario(
    // @RequestParam String nombre,
    // @RequestParam String tipoCocina,
    // @RequestParam String paisOrigen,
    // @RequestParam String dificultad,
    // @RequestParam String instrucciones,
    // @RequestParam int tiempoCoccion,
    // @RequestParam String ingredientes,
    // @RequestParam(required = false) String fotografia,
    // @RequestParam(required = false) String videoUrl
    // ) {
    // // Crear objeto JSON para enviar al microservicio
    // Receta nuevaReceta = new Receta();
    // nuevaReceta.setNombre(nombre);
    // nuevaReceta.setTipoCocina(tipoCocina);
    // nuevaReceta.setPaisOrigen(paisOrigen);
    // nuevaReceta.setDificultad(dificultad);
    // nuevaReceta.setInstrucciones(instrucciones);
    // nuevaReceta.setTiempoCoccion(tiempoCoccion);
    // nuevaReceta.setIngredientes(List.of(ingredientes.split(","))); // Convertir a
    // lista
    // nuevaReceta.setFotografia(fotografia);
    // nuevaReceta.setVideoUrl(videoUrl);

    // // Enviar datos al microservicio mediante RestTemplate
    // RestTemplate restTemplate = new RestTemplate();
    // String url = "http://localhost:8082/api/recetas/crear";
    // restTemplate.postForObject(url, nuevaReceta, Receta.class);

    // // Redirigir al Home después de crear la receta
    // return "redirect:/home";
    // }


    @PostMapping("/crearReceta")
    public String procesarFormulario(Receta nuevaReceta) {
        ResponseEntity<Receta> response = recetaService.crearReceta(nuevaReceta);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/home"; // Redirige al home si se creó correctamente
        } else {
            return "error"; // Muestra una página de error en caso de fallo
        }
    }

}
