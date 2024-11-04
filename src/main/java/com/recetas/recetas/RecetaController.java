// RecetaController.java

package com.recetas.recetas;

import com.recetas.recetas.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("recetas", recetas);
        return "home"; // Página principal donde se muestran las recetas
    }

    @GetMapping("/receta/detalles")
    public String obtenerDetalleReceta(@RequestParam("nombre") String nombreReceta, Model model) {
        Map<String, Object> recetaDetalles = recetaService.obtenerDetalleReceta(nombreReceta);
        model.addAttribute("receta", recetaDetalles);
        return "detalleReceta"; // Página para los detalles de la receta
    }
}
