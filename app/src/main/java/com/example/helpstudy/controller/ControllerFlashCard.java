package com.example.helpstudy.controller;

import android.content.Context;

import com.example.helpstudy.datasource.Repository;
import com.example.helpstudy.model.FlashCard;

import java.util.ArrayList;

public class ControllerFlashCard {
    private Repository repository;
    private static ArrayList<FlashCard> lista = new ArrayList<>();
    private static ControllerFlashCard instancia = null;

    public ControllerFlashCard(Context context){
        repository = new Repository(context);
        consultarFlashcards();
    }

    public boolean cadastrar(String titulo, String resposta){
        FlashCard f = new FlashCard();
        f.setTitulo(titulo);
        f.setDescricao(resposta);
        boolean retorno = repository.adicionarFlashcard(f);
        consultarFlashcards();
        return retorno;
    }
    public boolean atualizar(FlashCard flashCard){
        boolean retorno = repository.atualizarFlashcard(flashCard);
        consultarFlashcards();
        return retorno;
    }
    public boolean remover(FlashCard flashCard){
        boolean retorno = repository.deletarFlashcard(flashCard.getCodigo());
        consultarFlashcards();
        return retorno;
    }
    public ArrayList<FlashCard> buscarTodos(){
        return lista;
    }

    private void consultarFlashcards(){
        lista.clear();
        repository.buscarFlashcards();
    }

    public static void add(FlashCard flashCard){
        lista.add(flashCard);
        System.out.println("AA " +lista);
    }

}