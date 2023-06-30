package com.example.helpstudy.controller;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private int proxId;
    private final List<Usuario> lista;
    private DataSource db = new DataSource();
    private static ControllerUsuario instancia = null;

    private ControllerUsuario(){
        lista = new ArrayList<>();
    }

    public int getProxId(){
        return proxId;
    }

    public static ControllerUsuario getInstancia(){
        if (instancia == null)
            instancia = new ControllerUsuario();
        return instancia;
    }

    public void cadastrar(String nome, String email, String dataNasc, String senha){
        db.salvarUsuario(nome, email, dataNasc, senha);
    }

    public boolean alterar(Usuario usuario){
        for (int i = 0; i < lista.size(); i++) {
            if (usuario.getId()==lista.get(i).getId()){
                lista.set(i, usuario);
                return true;
            }
        }
        return false;
    }

    public int remover(Usuario usuario){
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (usuario.getId()==lista.get(i).getId()){
                lista.remove(i);
                cont += 1;
            }
        }
        return cont;
    }

    /*public List<Usuario> buscarTodos() throws Exception {
        return db.consultaUsuarios();
    }*/

    public Usuario buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public Usuario buscarPorId(String id){
        for (Usuario usuario : lista){
            if (usuario.getId().equals(id))
                return usuario;
        }
        return null;
    }

    /*public Usuario buscarPorEmail(String emailDeBusca) throws Exception {
        for (Usuario user: db.consultaUsuarios()) {
            if(user.getEmail().equals(emailDeBusca))
                return user;
        }
        return null;
    }*/
}
