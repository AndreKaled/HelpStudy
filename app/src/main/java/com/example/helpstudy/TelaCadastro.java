package com.example.helpstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {



    private ControllerUsuario controllerUsuario = ControllerUsuario.getInstancia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        TextView viewNome, viewEmail, viewDataNasc, viewSenha;

        TextView text;

        text = findViewById(R.id.textoLogin);

        viewNome = findViewById(R.id.editText_Nome);
        viewEmail = findViewById(R.id.editText_Email);
        viewDataNasc = findViewById(R.id.editTextNasc);
        viewSenha = findViewById(R.id.editText_Senha);
        Button btCadastrar = findViewById(R.id.btLogin);

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
                Usuario user = new Usuario();
                user.setNome(viewNome.getText().toString());
                //DATA DE NASCIMENTO, FALTA INPUT
                //botei o edittext, mas bugou a instância aparentemente
                user.setDataNasc(viewDataNasc.getText().toString());
                user.setEmail(viewEmail.getText().toString());
                user.setSenha(viewSenha.getText().toString());
                controllerUsuario.cadastrar(user);
                System.out.println(user + "cadastrado! :)");
                Toast.makeText(TelaCadastro.this, user +" cadastrado! :)",Toast.LENGTH_LONG).show();

            }
        });

    }
}