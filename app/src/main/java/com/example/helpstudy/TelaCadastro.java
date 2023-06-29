package com.example.helpstudy;

import androidx.appcompat.app.AppCompatActivity;

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

        viewNome = findViewById(R.id.editTextNome);
        viewEmail = findViewById(R.id.editTextEmail);
        //viewDataNasc = findViewById(DATA_AQUI);
        viewSenha = findViewById(R.id.editTextSenha);
        Button btCadastrar = findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario();
                user.setNome(viewNome.getText().toString());
                //DATA DE NASCIMENTO, FALTA INPUT
                //user.setDataNasc(viewDataNasc.getText().toString());
                user.setEmail(viewEmail.getText().toString());
                user.setSenha(viewSenha.getText().toString());
                controllerUsuario.cadastrar(user);
                System.out.println(user + "cadastrado! :)");
                Toast.makeText(TelaCadastro.this, user +" cadastrado! :)",Toast.LENGTH_LONG).show();

            }
        });

    }
}