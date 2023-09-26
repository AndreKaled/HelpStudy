package com.example.helpstudy.controller;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    private static ArrayList<Listas> lista = new ArrayList<>();
    private static ControllerListas instancia = null;
    private String selecaoId;

    private DataSource db = new DataSource();

    public ControllerListas(){
        db.consultarListas();
    }

    public static ControllerListas getInstancia(){
        if (instancia == null)
            instancia = new ControllerListas();
        return instancia;
    }
    public void cadastrar(String titulo){
        db.salvarListas(titulo);

    }
    public boolean alterar(Listas listas){
        for (int i = 0; i < lista.size(); i++) {
            if (listas.getId()==lista.get(i).getId()){
                lista.set(i, listas);
                return true;
            }
        }
        return false;
    }
    public void remover(Listas listas){

                db.excluirLista(listas.getId());

    }
    public ArrayList<Listas> buscarTodos(){
        return lista;
    }

    public void atualizarLista(){
        lista.clear();
        db.consultarListas();
    }

    public static Listas buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public static void add(Listas listas){
        lista.add(listas);
        System.out.println("AA " +lista);
    }
}
