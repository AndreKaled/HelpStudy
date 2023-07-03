package com.example.helpstudy.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    private final String TAG = "DataBase", COLECAO_USUARIOS = "usuarios", COLECAO_FLASHCARDS = "flashcards";
    private CollectionReference usuarioRef = dataBase.collection(COLECAO_USUARIOS);
    private CollectionReference flashcardRef = dataBase.collection(COLECAO_FLASHCARDS);

    //CRUD dos FLASHCARDS
    public void salvarFlashcard(String titulo, String descricao, int codigo) {
        FlashCard flashCard = new FlashCard();
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        flashCard.setCodigo(codigo);

        flashcardRef.document(titulo).set(flashCard).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_FLASHCARDS + "-> registrado com sucesso!" + flashCard);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_FLASHCARDS + "-> não foi registrado com sucesso");
                e.printStackTrace();
            }
        });
    }

    public List<FlashCard> consultarFlashcards(){
        List<FlashCard> flashCards = new ArrayList<>();
        flashcardRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FlashCard flashCard = documentSnapshot.toObject(FlashCard.class);
                    flashCards.add(flashCard);
                }
                Log.i(TAG, COLECAO_FLASHCARDS +"-> Query realizada com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "açargsed");
                Log.e(TAG, COLECAO_FLASHCARDS +"-> Erro ao executar Query no Banco");
                e.printStackTrace();
            }
        });
        return flashCards;
    }

    private void alterarFlashcard(String id, String titulo, String descricao){
        FlashCard flashCard = new FlashCard();
        //flashCard.setCodigo(id);
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        Log.i(TAG, COLECAO_FLASHCARDS +"-> alterando dados do flashcard " +flashCard);

        flashcardRef.document(id).set(flashCard).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, COLECAO_FLASHCARDS +"-> dados alterados com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, COLECAO_FLASHCARDS +"-> falha ao alterar dados!");
                e.printStackTrace();
            }
        });
    }

    public boolean excluirFlashcards(String id){
        Log.i(TAG,COLECAO_FLASHCARDS +"-> excluindo flashcard " +id);
        usuarioRef.document(id).delete();

        try {
            Log.i(TAG, COLECAO_FLASHCARDS +"-> flashcard excluído com sucesso!");
            return consultarFlashcards().contains(id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, COLECAO_FLASHCARDS +"-> falha ao excluir flashcard");
        }
        return false;
    }

    //CRUD dos USUARIOS
    public void salvarUsuario(String nome, String email, String dataNasc, String senha) {
        Usuario user = new Usuario();
        user.setNome(nome);
        user.setEmail(email);
        user.setDataNasc(dataNasc);
        user.setSenha(senha);
        user.setId(nome + "-" + email);

        usuarioRef.document(user.getId()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_USUARIOS + "-> registrado com sucesso! " + user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_USUARIOS + "-> não foi registrado com sucesso!");
                e.printStackTrace();
            }
        });
    }

    public List<Usuario> consultaUsuarios() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Usuario usuario = documentSnapshot.toObject(Usuario.class);
                    usuario.setId(documentSnapshot.getId());
                    usuarios.add(usuario);
                }
                Log.i(TAG, COLECAO_USUARIOS +"-> Query realizada com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "desgraçaaaaaaaaaaaaaaaaaaaaaaa");
                Log.e(TAG, COLECAO_USUARIOS +"-> Erro ao executar Query no Banco");
                e.printStackTrace();
            }
        });
        return usuarios;
    }

    private boolean excluirUsuario(String id){

        Log.i(TAG,COLECAO_USUARIOS +"-> excluindo usuário " +id);
        usuarioRef.document(id).delete();

        try {
            Log.i(TAG, COLECAO_USUARIOS +"-> usuário excluído com sucesso!");
            return consultaUsuarios().contains(id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, COLECAO_USUARIOS +"-> falha ao excluir usuário");
        }
        return false;
    }

    private void alterarUsuario(String id, String nome, String email, String dataNasc, String senha){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setDataNasc(dataNasc);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        Log.i(TAG, COLECAO_USUARIOS +"-> alterando dados do usuário " +usuario);

        usuarioRef.document(id).set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, COLECAO_USUARIOS +"-> dados alterados com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, COLECAO_FLASHCARDS +"-> falha ao alterar dados!");
                e.printStackTrace();
            }
        });
    }
}
