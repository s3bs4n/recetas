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


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///
    @GetMapping("/crearReceta")
    public String mostrarFormulario() {
        return "crearReceta"; // Renderiza el archivo crearReceta.html
    }


    @PostMapping("/crearReceta")
    public String procesarFormulario(Receta nuevaReceta) {
        ResponseEntity<Receta> response = recetaService.crearReceta(nuevaReceta);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/home"; // Redirige al home si se creó correctamente
        } else {
            return "error"; // Muestra una página de error en caso de fallo
        }
    }


/////////////////////////////////////////////////////////////////////////////////////////

@PostMapping("/receta/detalles/comentario")
public String agregarComentario(
        @RequestParam("recetaId") Long recetaId,
        @RequestParam("recetaNombre") String recetaNombre,
        @RequestParam("comentario") String comentarioText,
        @RequestParam("valoracion") int valoracion
) {
    // Construye el objeto Comentario
    Comentario comentario = new Comentario();
    comentario.setComentario(comentarioText);
    comentario.setValoracion(valoracion);
    Receta receta = new Receta();
    receta.setId(recetaId);
    comentario.setReceta(receta);

    // Llama al servicio para enviar el comentario
    ResponseEntity<Comentario> response = recetaService.agregarComentario(comentario);

    if (response.getStatusCode().is2xxSuccessful()) {
        // Redirige de vuelta a la página de detalles de la receta
        return "redirect:/receta/detalles?nombre=" + recetaNombre;
    } else {
        return "error"; // Maneja errores apropiadamente
    }
}









}
