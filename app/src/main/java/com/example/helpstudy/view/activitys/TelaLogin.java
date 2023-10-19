package com.example.helpstudy.view.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.R;
import com.example.helpstudy.model.Usuario;
import com.example.helpstudy.utils.Preferencias;

public class TelaLogin extends AppCompatActivity {

    private EditText textEmail, textSenha;
    private View textCadastro;
    private Preferencias pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        pref = new Preferencias(getApplicationContext());
        if(pref.getEmailUsuario() != null){
            ControllerUsuario.setIdUsuario(pref.getIdUsuario());

            Intent intent = new Intent(TelaLogin.this, MainActivity.class);
            startActivity(intent);
        }else{
            ControllerUsuario.getInstancia(getApplicationContext());

            textCadastro = findViewById(R.id.textoLogin);
            textEmail = findViewById(R.id.login_email);
            textSenha = findViewById(R.id.login_senha);
            Button btnLogin = findViewById(R.id.btn_login);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        validarUsuario();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TelaLogin.this, "Desculpe, deu problema no nosso app, não se preocupe que a culpa não é sua", Toast.LENGTH_LONG).show();
                    }
                }
            });

            textCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void validarUsuario() throws Exception {
        Usuario usuario;
        usuario = ControllerUsuario.getInstancia(getApplicationContext()).buscarPorEmail(textEmail.getText().toString());
        if (usuario == null) {
            textEmail.setError("Email não encontrado!");
        }
        if (textEmail.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()) {
            textEmail.setError("O email não pode ser vazio!");
            textSenha.setError("A senha não pode ser vazia!");
        } else if (usuario.getSenha().isEmpty()) {
            textSenha.setError("A senha não pode ser vazia!");
        } else if (usuario.getEmail().isEmpty()) {
            textEmail.setError("Email de usuário inválido!");
        } else if (!usuario.getSenha().equals(textSenha.getText().toString())) {
            textSenha.setError("senha de usuário incorreta!");
        } else if (usuario.getSenha().equals(textSenha.getText().toString())) {
            Toast.makeText(this, "Bem vindo(a), " + usuario.getNome() + "!", Toast.LENGTH_LONG).show();

            pref.editEmailUsuario(textEmail.getText().toString());
            pref.editSenhaUsuario(textSenha.getText().toString());
            pref.editIdUsuario(usuario.getId());

            Intent intent = new Intent(TelaLogin.this, MainActivity.class);
            startActivity(intent);
        }
    }

}

