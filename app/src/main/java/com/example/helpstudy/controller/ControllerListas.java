package com.example.helpstudy.controller;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Listas;
import java.util.ArrayList;
import java.util.List;

public class ControllerListas {

    private final List<Listas> listas;
    private int proxid;
    private static ControllerListas instancia = null;

    private DataSource db = new DataSource();

    private ControllerListas() throws Exception {
        listas = db.consultarListas();
    }

    public int getProxId() {
        return proxid;
    }

    public static ControllerListas getInstancia() {
        if (instancia == null) {
            try {
                instancia = new ControllerListas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }



    public void cadastrar(Listas lista_editText){
        lista_editText.setId(proxid);
        boolean resultado = listas.add(lista_editText);
        if (resultado)
            proxid += 1;
        
    }

    public boolean alterar(Listas lista_editText){
        for (int i = 0; i < listas.size(); i++) {
            if (lista_editText.getId()== listas.get(i).getId()){
                listas.set(i, lista_editText);
                return true;
            }
        }
        return false;
    }


    public int remover(Listas lista_editText){
        int cont = 0;
        for (int i = 0; i < listas.size(); i++) {
            if (lista_editText.getId()== listas.get(i).getId()){
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
