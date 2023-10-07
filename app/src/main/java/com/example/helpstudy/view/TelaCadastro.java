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

public class TelaCadastro extends AppCompatActivity {

    EditText textNome, textEmail, textDataNasc, textSenha;
    String nome, email, dataNasc, senha;

    private ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        TextView text;

        text = findViewById(R.id.textoLogin);

        textNome = findViewById(R.id.cadastro_nome);
        textEmail = findViewById(R.id.cadastro_email);
        textDataNasc = findViewById(R.id.cadastro_dataNasc);
        textSenha = findViewById(R.id.cadastro_senha);
        Button btCadastrar = findViewById(R.id.btn_cadastro);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(intent);

            }
        });


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
                Intent it = new Intent(TelaCadastro.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    private void validarCampos(){

        nome = textNome.getText().toString();
        email = textEmail.getText().toString();
        dataNasc = textDataNasc.getText().toString();
        senha = textSenha.getText().toString();

        if(textNome.getText().toString().isEmpty() && textEmail.getText().toString().isEmpty() &&
                textDataNasc.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() && textEmail.getText().toString().isEmpty() &&
                textDataNasc.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() && textEmail.getText().toString().isEmpty() &&
                 textSenha.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() &&
                textDataNasc.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textEmail.getText().toString().isEmpty() && textDataNasc.getText().toString().isEmpty() &&
                textSenha.getText().toString().isEmpty()){
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() && textEmail.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty() && textDataNasc.getText().toString().isEmpty()){
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textEmail.getText().toString().isEmpty() && textDataNasc.getText().toString().isEmpty()) {
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textEmail.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()){
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textDataNasc.getText().toString().isEmpty() && textSenha.getText().toString().isEmpty()){
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        }
        else if(textNome.getText().toString().isEmpty())
            textNome.setError("Ei, você esqueceu de me preencher! 😓");
        else if (textDataNasc.getText().toString().isEmpty())
            textDataNasc.setError("Ei, você esqueceu de me preencher! 😓");
        else if (textEmail.getText().toString().isEmpty())
            textEmail.setError("Ei, você esqueceu de me preencher! 😓");
        else if(textSenha.getText().toString().isEmpty())
            textSenha.setError("Ei, você esqueceu de me preencher! 😓");
        else if(usuarioExiste())
            Toast.makeText(TelaCadastro.this, "Já encontramos seu cadastro aqui, faça o login para entrar!", Toast.LENGTH_LONG).show();
        else{
            controllerUsuario.cadastrar(nome, email, dataNasc, senha);
            Toast.makeText(TelaCadastro.this, "Cadastro realizado com sucesso!",Toast.LENGTH_LONG).show();
        }
    }

    private boolean usuarioExiste(){
        if(controllerUsuario.buscarPorEmail(email)==null)
            return false;
        return true;
    }
}