package com.example.helpstudy.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DataSource {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    private final String TAG = "DataBase", COLECAO_USUARIOS = "usuarios", COLECAO_FLASHCARDS = "flashcards", COLECAO_LISTAS = "listas";
    private CollectionReference usuarioRef = dataBase.collection(COLECAO_USUARIOS);
    private CollectionReference flashcardRef;
    private CollectionReference listasRef;


    //CRUD LISTAS

    public void salvarListas(String titulo){

        Listas listasarray = new Listas();
        listasarray.setTitulo(titulo);

        listasRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS);

        listasRef.document(titulo).set(listasarray).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_LISTAS + "-> registrado com sucesso!" + listasarray);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_LISTAS + "-> não foi registrado com sucesso");
                e.printStackTrace();
            }
        });
    }


    public void consultarListas(){
            final ExecutorService threadpool = Executors.newFixedThreadPool(3);

            class Result implements Callable<ArrayList>{

                @Override
                public ArrayList call() throws Exception {

                    ArrayList<Listas> list = new ArrayList<>();
                    listasRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS);
                    listasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Listas listas = documentSnapshot.toObject(Listas.class);
                                //flashCard.setCodigo(Integer.parseInt(documentSnapshot.getId()));
                                ControllerListas.add(listas);
                            }
                            Log.i(TAG, COLECAO_LISTAS + "-> Query realizada com sucesso!");
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "açargsed");
                            Log.e(TAG, COLECAO_LISTAS + "-> Erro ao executar Query no Banco");
                            e.printStackTrace();
                        }
                    });
                    return null;
                }
            }

            try{
                Result task = new Result();
                Future<ArrayList> future = threadpool.submit(task);
                while(!future.isDone()){
                    Log.i(TAG, COLECAO_LISTAS +" -> Buscando no BD...");
                    Thread.sleep(1);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }



    //CRUD dos FLASHCARDS
    public void salvarFlashcard(String titulo, String descricao,  int codigo) {
        FlashCard flashCard = new FlashCard();
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        flashCard.setCodigo(codigo);

        flashcardRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_FLASHCARDS);
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
    public void consultarFlashcards(){
        final ExecutorService threadpool = Executors.newFixedThreadPool(3);

        class Result implements Callable<ArrayList>{

            @Override
            public ArrayList call() throws Exception {

                ArrayList<FlashCard> list = new ArrayList<>();
                flashcardRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_FLASHCARDS);
                flashcardRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            FlashCard flashCard = documentSnapshot.toObject(FlashCard.class);
                            //flashCard.setCodigo(Integer.parseInt(documentSnapshot.getId()));
                            ControllerFlashCard.add(flashCard);
                        }
                        Log.i(TAG, COLECAO_FLASHCARDS + "-> Query realizada com sucesso!");
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "açargsed");
                        Log.e(TAG, COLECAO_FLASHCARDS + "-> Erro ao executar Query no Banco");
                        e.printStackTrace();
                    }
                });
                return null;
            }
        }

        try{
            Result task = new Result();
            Future<ArrayList> future = threadpool.submit(task);
            while(!future.isDone()){
                Log.i(TAG, COLECAO_FLASHCARDS +" -> Buscando no BD...");
                Thread.sleep(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private void salvarTarefa(String args){
        //fazer
    }
}