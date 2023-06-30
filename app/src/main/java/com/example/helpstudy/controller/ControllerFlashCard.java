package com.example.helpstudy.controller;

import com.example.helpstudy.model.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class ControllerFlashCard {
    private int proxCodigo;
    private final List<FlashCard> lista;

    private static ControllerFlashCard instancia = null;

    private ControllerFlashCard(){
        lista = new ArrayList<>();
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

    public void cadastrar(FlashCard flashCard){
        flashCard.setCodigo(proxCodigo);
        boolean resultado = lista.add(flashCard);
        if (resultado)
            proxCodigo += 1;

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

    public List<FlashCard> buscarTodos(){
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
