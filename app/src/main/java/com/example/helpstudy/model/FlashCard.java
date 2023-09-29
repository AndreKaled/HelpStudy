package com.example.helpstudy.model;

public class FlashCard {
    String titulo, descricao;
    long codigo;

    public FlashCard(){}

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + '\'' + "Descrição: " + descricao + '\'' + "Código:" + codigo;
    }

}
