package com.example.helpstudy.controller;

import android.content.Context;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.datasource.Repository;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ControllerTarefas {

    private Repository repository;
    private static List<Tarefa> lista = new ArrayList<>();
    private static ControllerTarefas instancia = null;

    private DataSource db = new DataSource();
    private static long listaSelecionada;

    public ControllerTarefas(Context context){
        repository = new Repository(context);
        atualizarTarefas();
    }

    public void cadastrar(String nome, String descricao, String dataEntrega, boolean concluida){
        Tarefa t = new Tarefa();
        t.setNome(nome);
        t.setDataEntrega(dataEntrega);
        t.setDescricao(descricao);
        t.setConcluida(concluida);
        repository.adicionarTarefa(t, listaSelecionada);
    }

    public boolean atualizar(Tarefa tarefa){
        boolean resposta = repository.atualizarTarefa(tarefa, listaSelecionada);
        atualizarTarefas();
        return resposta;
    }
    public boolean remover(Tarefa tarefa){
        boolean resposta = repository.deletarTarefa(listaSelecionada, tarefa.getId());
        atualizarTarefas();
        return resposta;
    }
    public List<Tarefa> buscarTodos(){
        return lista;
    }

    public Tarefa buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }


    public void atualizarTarefas(){
        lista.clear();
        repository.buscarTarefas(listaSelecionada);
    }

    public static void add(Tarefa tarefa){
        lista.add(tarefa);
    }

    public static long getListaSelecionada() {
        return listaSelecionada;
    }

    public static void setListaSelecionada(long id) {
        listaSelecionada = id;
    }
}
