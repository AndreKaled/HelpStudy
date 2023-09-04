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
    private static String listaSelecionada;

    public static ControllerTarefas getInstancia(){
        if (instancia == null)
            instancia = new ControllerTarefas();
        return instancia;
    }
    public void cadastrar(String nome, String descricao, String dataEntrega, boolean concluida){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(nome);
        tarefa.setNome(nome);
        tarefa.setDataEntrega(dataEntrega);
        tarefa.setConcluida(concluida);
        db.salvarTarefa(nome, descricao, dataEntrega, concluida);
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
    }

    public static String getListaSelecionada() {
        return listaSelecionada;
    }

    public static void setListaSelecionada(String id) {
        listaSelecionada = id;
    }
}
