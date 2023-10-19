package com.example.helpstudy.utils;

import android.content.Context;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.view.adapters.ListaFlashCardAdapter;
import com.example.helpstudy.view.adapters.ListasAdapter;
import com.example.helpstudy.view.adapters.TarefaAdapter;

public class ROOT {
    private Context context;
    public ROOT(Context context){
        this.context = context;
    }
    public void buscarTudo(){
        new ControllerListas(context);
        new ControllerFlashCard(context);
    }

    public void sincTarefas(){
        //te odeio
        try {
            Thread.sleep(1000);
            new TarefaAdapter(context).notifyDataSetChanged();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sincFlashcard(){
        //te odeio
        try {
            Thread.sleep(1000);
            new ListaFlashCardAdapter(context).notifyDataSetChanged();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void sincListas(){
        try {
            Thread.sleep(1000);
            new ListasAdapter(context).notifyDataSetChanged();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
