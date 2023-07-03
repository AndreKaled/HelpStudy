package com.example.helpstudy.controller;

import android.util.Log;

import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private int proxId;
    private final List<Usuario> lista;
    private DataSource db = new DataSource();
    private static ControllerUsuario instancia = null;

    private ControllerUsuario() throws Exception {
        lista = db.consultaUsuarios();
    }

    public int getProxId() {
        return proxId;
    }

    public static ControllerUsuario getInstancia() {
        if (instancia == null) {
            try {
                instancia = new ControllerUsuario();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instancia;
    }

    public void cadastrar(String nome, String email, String dataNasc, String senha) {
        db.salvarUsuario(nome, email, dataNasc, senha);
    }

    public boolean alterar(Usuario usuario) {
        for (int i = 0; i < lista.size(); i++) {
            if (usuario.getId() == lista.get(i).getId()) {
                lista.set(i, usuario);
                return true;
            }
        }
        return false;
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
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
