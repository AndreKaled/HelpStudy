package com.example.helpstudy.controller;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    private final List<Listas> listas;
    private int proxid;
    private static ControllerListas instancia = null;

    private ControllerListas(){
        listas = new ArrayList<>();
        proxid = 1;
    }
    public int getProxId(){
        return proxid;
    }


    public static ControllerListas getInstancia(){
        if (instancia == null)
            instancia = new ControllerListas();
        return instancia;
    }


    public void cadastrar(Listas list){
        list.setId(proxid);
        boolean resultado = listas.add(list);
        if (resultado)
            proxid += 1;
    }

    public boolean alterar(Listas list){
        for (int i = 0; i < listas.size(); i++) {
            if (list.getId()== listas.get(i).getId()){
                listas.set(i, list);
                return true;
            }
        }
        return false;
    }


    public int remover(Listas list){
        int cont = 0;
        for (int i = 0; i < listas.size(); i++) {
            if (list.getId()== listas.get(i).getId()){
                listas.remove(i);
                cont += 1;
            }
        }
        return cont;
    }

    public List<Listas> buscarTodos(){
        return new ArrayList<>(listas);
    }


    public Listas buscarPorId(int id){
        for (Listas list : listas){
            if (list.getId()==id)
                return list;
        }
        return null;
    }

}
