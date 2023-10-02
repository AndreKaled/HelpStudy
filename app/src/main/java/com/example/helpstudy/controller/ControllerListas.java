package com.example.helpstudy.controller;
import android.content.Context;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.datasource.Repository;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    Repository repository;
    private static ArrayList<Listas> lista = new ArrayList<>();
    private static ControllerListas instancia = null;
    private String selecaoId;

    private DataSource db = new DataSource();

    public ControllerListas(Context context){
        repository = new Repository(context);
        consultaListas();
    }

    private void consultaListas() {
        lista.clear();
        repository.buscarListas();
    }

    public void cadastrar(String titulo){
        Listas lista = new Listas();
        lista.setTitulo(titulo);
        repository.adicionarLista(lista);
        atualizarLista();
    }
    public boolean atualizar(Listas listas){
        boolean retorno = repository.atualizarLista(listas);
        atualizarLista();
        return retorno;
    }
    public boolean remover(Listas listas){
        boolean retorno = repository.deletarLista(listas.getId());
        atualizarLista();
        return retorno;
    }
    public ArrayList<Listas> buscarTodos(){
        return lista;
    }

    public void atualizarLista(){
        lista.clear();
        repository.buscarListas();
    }

    public static Listas buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public static void add(Listas listas){
        lista.add(listas);
        System.out.println("AA " +lista);
    }
}
