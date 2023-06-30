package com.example.helpstudy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.R;
import com.example.helpstudy.model.Usuario;

public class TelaLogin extends AppCompatActivity {

    private EditText textNome, textEmail, textSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        TextView text = findViewById(R.id.textoLogin);
        textNome = findViewById(R.id.login_nome);
        textEmail = findViewById(R.id.login_email);
        textSenha = findViewById(R.id.login_senha);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = null;
                try {
                    //user = ControllerUsuario.getInstancia().buscarPorEmail(textEmail.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                validarUsuario(user);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void validarUsuario(Usuario usuario){
        if(usuario.getSenha().equals(textSenha.getText().toString()))
            Toast.makeText(this, "Bem vindo(a), " +usuario.getNome(), Toast.LENGTH_LONG).show();
        else
            textSenha.setError("Senha de usuário incorreta!");
    }

}
