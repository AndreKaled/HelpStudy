package com.example.helpstudy.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.view.ListaFlashCardAdapter;
import com.example.helpstudy.view.ListasAdapter;
import com.example.helpstudy.view.TarefaAdapter;

public class ROOT {
    private Context context;
    public ROOT(Context context){
        this.context = context;
    }
    public void buscarTudo(){
        new ControllerListas(context);
        new ControllerFlashCard(context);
    }

    public void sincListas(){
        //te odeio
        try {
            Thread.sleep(500);
            new ListasAdapter(context).notifyDataSetChanged();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
}
