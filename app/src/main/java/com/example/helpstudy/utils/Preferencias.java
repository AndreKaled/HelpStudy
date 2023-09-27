package com.example.helpstudy.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    SharedPreferences shared;
    SharedPreferences.Editor editor;
    Context context;

    public Preferencias(Context context) {
        this.context = context;
        shared = context.getSharedPreferences("dados", Context.MODE_PRIVATE);
        editor = shared.edit();
    }

    public String getEmailUsuario(){
        return shared.getString("email", null);
    }
    public String getSenhaUsuario(){
        return shared.getString("senha", null);
    }
    public String getIdUsuario(){return shared.getString("id",null);}

    public void editEmailUsuario(String email){
        editor.putString("email", email);
        editor.commit();
    }

    public void editSenhaUsuario(String senha){
        editor.putString("senha", senha);
        editor.commit();
    }

    public  void editIdUsuario(String id){
        editor.putString("id", id);
        editor.commit();
    }
}
