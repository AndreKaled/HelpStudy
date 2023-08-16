package com.example.helpstudy.controller;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class ControllerFlashCard {
    private int proxCodigo;
    private final List<FlashCard> lista;
    private static DataSource db = new DataSource();
    private static ControllerFlashCard instancia = null;

    public ControllerFlashCard(){
        lista = db.consultarFlashcards();
        proxCodigo = 1;
    }
    public int getProxCodigo(){
        return proxCodigo;
    }

    public static ControllerFlashCard getInstancia(){
        if (instancia == null)
            instancia = new ControllerFlashCard();
        return instancia;
    }
    public void cadastrar(String titulo, String resposta){
        db.salvarFlashcard(titulo, resposta, proxCodigo);

    }
    public boolean alterar(FlashCard flashCard){
        for (int i = 0; i < lista.size(); i++) {
            if (flashCard.getCodigo()==lista.get(i).getCodigo()){
                lista.set(i, flashCard);
                return true;
            }
        }
        return false;
    }
    public int remover(FlashCard flashCard){
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (flashCard.getCodigo()==lista.get(i).getCodigo()){
                lista.remove(i);
                cont += 1;
            }
        }
        return cont;
    }
    public ArrayList<FlashCard> buscarTodos(){
        return new ArrayList<>(lista);
    }

    public FlashCard buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public FlashCard buscarPorcodigo(int codigo){

        for (FlashCard flashCard : lista){
            if (flashCard.getCodigo()==codigo)
                return flashCard;
        }
        return null;
    }
}