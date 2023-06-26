package com.example.helpstudy;

import java.util.ArrayList;
import java.util.List;

public class GerenciaFlashCard {
    List<FlashCard> lista = new ArrayList();

    public void add(FlashCard flashCard){
        lista.add(flashCard);
    }

    public void remove(FlashCard flashCard){
        lista.remove(flashCard);
    }

    public FlashCard buscaPorTitulo(String flashCardTitulo){
        for (FlashCard f: lista) {
            if(f.getTitulo() == flashCardTitulo)
                return f;
        }
        return null;
    }
}
