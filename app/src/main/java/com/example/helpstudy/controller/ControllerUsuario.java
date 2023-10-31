package com.example.helpstudy.controller;

import android.content.Context;
import android.util.Log;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private static String idUsuario;
    private final List<Usuario> lista;
    private DataSource db;
    private static ControllerUsuario instancia = null;

    public ControllerUsuario(Context context){
        db = new DataSource(context);
        lista = db.consultaUsuarios();
    }

    public static ControllerUsuario getInstancia(Context context) {
        if (instancia == null) {
            try {
                instancia = new ControllerUsuario(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }

    public void cadastrar(String nome, String email, String dataNasc, String senha) {
        db.salvarUsuario(nome, email, dataNasc, senha);
        idUsuario = nome +"-" +email;

    }

    public void atualizar(Usuario usuario) {

        db.alterarUsuario(usuario);
    }

    public int remover(Usuario usuario) {
        int cont = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (usuario.getId() == lista.get(i).getId()) {
                lista.remove(i);
                cont += 1;
            }
        }
        return cont;
    }

    /*public List<Usuario> buscarTodos() throws Exception {
        return db.consultaUsuarios();
    }*/

    public Usuario buscarPorPosicao(int posicao) {
        return lista.get(posicao);
    }

    public Usuario buscarPorId(String id) {
        for (Usuario usuario : lista) {
            if (usuario.getId().equals(id))
                return usuario;
        }
        return null;
    }

    public Usuario buscarPorEmail(String emailDeBusca) {
        try {
            for (Usuario user : lista) {
                if (user.getEmail().equals(emailDeBusca)) {
                    Log.i("TAG", "achei vc, otario: "+user.getEmail());
                    idUsuario = user.getId();
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIdUsuario(){
        return idUsuario;
    }

    public static void setIdUsuario(String idUsuario1){
        idUsuario = idUsuario1;
    }
}
