package com.example.helpstudy;

import com.example.helpstudy.model.Usuario;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private int proxId;
    private final List<Usuario> lista;

    private static ControllerUsuario instancia = null;

    private ControllerUsuario(){
        lista = new ArrayList<>();
        proxId = 1;
    }

    public int getProxId(){
        return proxId;
    }

    public static ControllerUsuario getInstancia(){
        if (instancia == null)
            instancia = new ControllerUsuario();
        return instancia;
    }

    public void cadastrar(Usuario usuario){
        usuario.setId(proxId);
        boolean resultado = lista.add(usuario);
        if (resultado)
            proxId += 1;
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

    public List<Usuario> buscarTodos(){
        return new ArrayList<>(lista);
    }

    public Usuario buscarPorPosicao(int posicao){
        return lista.get(posicao);
    }

    public Usuario buscarPorId(int id){
        for (Usuario usuario : lista){
            if (usuario.getId()==id)
                return usuario;
        }
        return null;
    }

    public Usuario buscarPorEmail(String emailDeBusca){
        for (Usuario user: lista) {
            if(user.getEmail().equals(emailDeBusca))
                return user;
        }
        return null;
    }
}
