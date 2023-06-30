package com.example.helpstudy.model;

public class Listas {

    private String titulo;
    private int id;

    public Listas(){}

    public Listas(String titulo, int id) {
        this.titulo = titulo;
        this.id = id;
    }




    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    @Override
    public String toString() {
        return "Listas{" +
                "titulo='" + titulo + '\'' +
                '}';
    }
}
