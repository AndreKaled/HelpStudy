package com.example.helpstudy.model;

public class Listas {

    private String titulo;
    private String id;

    public Listas(){}


    @Override
    public String toString() {
        return "Listas{" +
                "titulo='" + titulo + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
