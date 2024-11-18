package com.recetas.recetas;

import java.util.List;

public class Receta {

    private String nombre;
    private String tipoCocina;
    private String paisOrigen;
    private String dificultad;
    private String instrucciones;
    private int tiempoCoccion;
    private List<String> ingredientes;
    private String fotografia;
    private String videoUrl;

    // Constructor vacío
    public Receta() {
    }

    // Constructor con parámetros
    public Receta(String nombre, String tipoCocina, String paisOrigen, String dificultad, String instrucciones,
                  int tiempoCoccion, List<String> ingredientes, String fotografia, String videoUrl) {
        this.nombre = nombre;
        this.tipoCocina = tipoCocina;
        this.paisOrigen = paisOrigen;
        this.dificultad = dificultad;
        this.instrucciones = instrucciones;
        this.tiempoCoccion = tiempoCoccion;
        this.ingredientes = ingredientes;
        this.fotografia = fotografia;
        this.videoUrl = videoUrl;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", tipoCocina='" + tipoCocina + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                ", tiempoCoccion=" + tiempoCoccion +
                ", ingredientes=" + ingredientes +
                ", fotografia='" + fotografia + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
