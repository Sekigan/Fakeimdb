package com.its.fakeimdb;


public class pelicula {
        private String titulo;
        private String genero;
        private String año;
        private String actores;
        private String imagen;
        private int valor;

    public pelicula(String titulo, String genero, String año, String actores, String imagen, int valor) {
        this.titulo = titulo;
        this.genero = genero;
        this.año = año;
        this.actores = actores;
        this.imagen = imagen;
        this.valor = valor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    public pelicula(){

    }
}
