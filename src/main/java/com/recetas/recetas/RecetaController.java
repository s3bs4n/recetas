// RecetaController.java

package com.recetas.recetas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping("/home")
    public String mostrarRecetas(Model model) {
        List<String> recetas = recetaService.obtenerRecetas(); 
        // System.out.println(recetas); 
        model.addAttribute("recetas", recetas); 
        return "home"; 
    }

    @GetMapping("/receta/detalles")
    public String obtenerDetalleReceta(@RequestParam("nombre") String nombreReceta, Model model) {
        Map<String, Object> recetaDetalles = recetaService.obtenerDetallesReceta(nombreReceta);
        model.addAttribute("receta", recetaDetalles);
        return "detalleReceta"; 
    }


    @GetMapping("/crearReceta")
    public String mostrarFormulario() {
        return "crearReceta"; 
    }


    @PostMapping("/crearReceta")
    public String procesarFormulario(Receta nuevaReceta) {
        ResponseEntity<Receta> response = recetaService.crearReceta(nuevaReceta);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/home"; 
        } else {
            return "error"; 
        }
    }



@PostMapping("/receta/detalles/comentario")
public String agregarComentario(
        @RequestParam("recetaId") Long recetaId,
        @RequestParam("recetaNombre") String recetaNombre,
        @RequestParam("comentario") String comentarioText,
        @RequestParam("valoracion") int valoracion
) {

    Comentario comentario = new Comentario();
    comentario.setComentario(comentarioText);
    comentario.setValoracion(valoracion);
    Receta receta = new Receta();
    receta.setId(recetaId);
    comentario.setReceta(receta);

    ResponseEntity<Comentario> response = recetaService.agregarComentario(comentario);

    if (response.getStatusCode().is2xxSuccessful()) {
        return "redirect:/receta/detalles?nombre=" + recetaNombre;
    } else {
        return "error";

    }
}









}
