package com.example.helpstudy.controller;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.model.Tarefa;

import java.util.ArrayList;

public class ControllerTarefas {

    //fazer
    private static ArrayList<Tarefa> lista = new ArrayList<>();
    private static ControllerTarefas instancia = null;

    private DataSource db = new DataSource();

    public static ControllerTarefas getInstancia(){
        if (instancia == null)
            instancia = new ControllerTarefas();
        return instancia;
    }
    public void cadastrar(String titulo){
        db.salvarListas(titulo);
    }
//    public boolean alterar(Listas listas){
//        for (int i = 0; i < lista.size(); i++) {
//            if (listas.getId()==lista.get(i).getId()){
//                lista.set(i, listas);
//                return true;
//            }
//        }
//        return false;
//    }
//    public int remover(Listas listas){
//        int cont = 0;
//        for (int i = 0; i < lista.size(); i++) {
//            if (listas.getId()==lista.get(i).getId()){
//                lista.remove(i);
//                cont += 1;
//            }
//        }
//        return cont;
//    }
    public ArrayList<Tarefa> buscarTodos(){
        return lista;
    }

    public Tarefa buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

//    public Listas buscarPorcodigo(int codigo){
//
//        for ( Listas flashCard : lista){
//            if (flashCard.getId()==codigo)
//                return flashCard;
//        }
//        return null;
//    }

    public static void add(Tarefa tarefa){
        lista.add(tarefa);
        System.out.println("AA " +lista);
    }
}
