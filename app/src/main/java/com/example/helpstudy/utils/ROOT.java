package com.example.helpstudy.utils;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;

public class ROOT {
    public void buscarTudo(){
        ControllerListas.getInstancia();
        ControllerFlashCard.getInstancia();
    }
}
