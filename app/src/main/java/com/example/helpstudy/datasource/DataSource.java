package com.example.helpstudy.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DatabaseId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataSource {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    private final String TAG = "DataBase", COLECAO_USUARIOS = "usuarios", COLECAO_FLASHCARDS = "flashcards";
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void salvarFlashcard(String titulo, String descricao, int codigo){
        FlashCard flashCard = new FlashCard();
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        flashCard.setCodigo(codigo);

        dataBase.collection(COLECAO_FLASHCARDS).document(titulo).set(flashCard).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_FLASHCARDS +" registrado com sucesso!" +flashCard);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_FLASHCARDS +" não foi registrado com sucesso");
            }
        });
    }

    public void salvarUsuario(String nome, String email, String dataNasc,String senha){
        Usuario user = new Usuario();
        user.setNome(nome);
        user.setEmail(email);
        user.setDataNasc(dataNasc);
        user.setSenha(senha);
        user.setId(nome+"-" +email);

        dataBase.collection(COLECAO_USUARIOS).document(user.getId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_USUARIOS +" registrado com sucesso! " +user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_USUARIOS +" não foi registrado com sucesso!");
            }
        });
    }

    //quebrando cabeca aqui, by: dre
    public List<Usuario> consultaUsuarios() throws Exception{
        List<Usuario> usuarios = new ArrayList<Usuario>();
        mDatabase.child(COLECAO_USUARIOS).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", "AAAA "+String.valueOf(task.getResult().getValue()));
                }
            }
        });
        return usuarios;
    }
}
