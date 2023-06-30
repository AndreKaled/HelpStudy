package com.example.helpstudy.controller;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    private final List<Listas> listy;
    private int proxid;
    private static ControllerListas instancia = null;

    private ControllerListas(){
        listy = new ArrayList<>();
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
        boolean resultado = listy.add(list);
        if (resultado)
            proxid += 1;
    }

    public boolean alterar(Listas list){
        for (int i = 0; i < listy.size(); i++) {
            if (list.getId()==listy.get(i).getId()){
                listy.set(i, list);
                return true;
            }
        }
        return false;
    }


    public int remover(Listas list){
        int cont = 0;
        for (int i = 0; i < listy.size(); i++) {
            if (list.getId()==listy.get(i).getId()){
                listy.remove(i);
                cont += 1;
            }
        }
        return cont;
    }

    public List<Listas> buscarTodos(){
        return new ArrayList<>(listy);
    }


    public Listas buscarPorId(int id){
        for (Listas list : listy){
            if (list.getId()==id)
                return list;
        }
        return null;
    }

}
