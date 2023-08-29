package com.example.helpstudy.controller;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    private static ArrayList<Listas> lista = new ArrayList<>();
    private int proxid;
    private static ControllerListas instancia = null;

    private DataSource db = new DataSource();

    public ControllerListas(){
        db.consultarListas();
        proxid = 1;
    }
    public int getProxCodigo(){
        return proxid;
    }

    public static ControllerListas getInstancia(){
        if (instancia == null)
            instancia = new ControllerListas();
        return instancia;
    }
    public void cadastrar(String titulo, String resposta){
        db.salvarFlashcard(titulo, resposta, proxid);

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
    public int remover(Listas listas){
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (listas.getId()==lista.get(i).getId()){
                lista.remove(i);
                cont += 1;
            }
        }
        return cont;
    }
    public ArrayList<Listas> buscarTodos(){
        return lista;
    }

    public Listas buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public Listas buscarPorcodigo(int codigo){

        for ( Listas flashCard : lista){
            if (flashCard.getId()==codigo)
                return flashCard;
        }
        return null;
    }

    public static void add(Listas listas){
        lista.add(listas);
        System.out.println("AA " +lista);
    }

    public static void passarLista(ArrayList<Listas> arrayList){
        lista = arrayList;
    }
}
