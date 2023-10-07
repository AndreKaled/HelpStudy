package com.example.helpstudy.model;

public class Listas {

    private String titulo;
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
