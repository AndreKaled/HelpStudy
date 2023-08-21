package com.example.helpstudy.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DataSource {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
    private final String TAG = "DataBase", COLECAO_USUARIOS = "usuarios", COLECAO_FLASHCARDS = "flashcards", COLECAO_LISTAS = "listas";
    private CollectionReference usuarioRef = dataBase.collection(COLECAO_USUARIOS);
    private CollectionReference flashcardRef = usuarioRef.document("Andre-2021333729@ifam.edu.br").collection(COLECAO_FLASHCARDS);

    private CollectionReference listasRef = usuarioRef.document("Andre-2021333729@ifam.edu.br").collection(COLECAO_LISTAS);


    //CRUD LISTAS

    public void salvarListas(String titulo){

        Listas listas = new Listas();
        listas.setTitulo(titulo);
        listasRef = usuarioRef.document("Andre-2021333729@ifam.edu.br").collection(COLECAO_LISTAS);
        listasRef.document(titulo).set(listas).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i(TAG, COLECAO_LISTAS + "-> registrado com sucesso!" + listas);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, COLECAO_LISTAS + "-> não foi registrado com sucesso");
                e.printStackTrace();
            }
        });
    }

    public ArrayList<Listas> consultarListas(){
        ArrayList<Listas> listas = new ArrayList<>();
        listasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Listas lista_interna = documentSnapshot.toObject(Listas.class);
                    lista_interna.setId(Integer.parseInt(documentSnapshot.getId()));
                    listas.add(lista_interna);
                }
                Log.i(TAG, COLECAO_LISTAS +"-> Query realizada com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "açargsed");
                Log.e(TAG, COLECAO_LISTAS +"-> Erro ao executar Query no Banco");
                e.printStackTrace();
            }
        });
        return listas;
    }

    private void alterarListas(String titulo){
        Listas listas = new Listas();
        //flashCard.setCodigo(id);
        listas.setTitulo(titulo);
        Log.i(TAG, COLECAO_LISTAS +"-> alterando dados da lista " +listas);

        listasRef.document("Andre-2021333729@ifam.edu.br").set(listas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, COLECAO_LISTAS +"-> dados alterados com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, COLECAO_LISTAS +"-> falha ao alterar dados!");
                e.printStackTrace();
            }
        });
    }

    public boolean excluirListas(String id){
        Log.i(TAG,COLECAO_LISTAS +"-> excluindo lista " +id);
        listasRef.document("Andre-2021333729@ifam.edu.br").delete();

        try {
            Log.i(TAG, COLECAO_LISTAS +"-> lista excluída com sucesso!");
            return consultarListas().contains(id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, COLECAO_LISTAS +"-> falha ao excluir a lista");
        }
        return false;
    }

    //CRUD dos FLASHCARDS
    public void salvarFlashcard(String titulo, String descricao,  int codigo) {
        FlashCard flashCard = new FlashCard();
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        flashCard.setCodigo(codigo);

        flashcardRef = usuarioRef.document("Andre-2021333729@ifam.edu.br").collection(COLECAO_FLASHCARDS);
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
    public ArrayList<FlashCard> consultarFlashcards(){
        class RetornarFlashcards implements Callable<ArrayList<FlashCard>> {
            @Override
            public ArrayList<FlashCard> call() throws Exception {
                ArrayList<FlashCard> flashCards = new ArrayList<>();
                flashcardRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {@Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        FlashCard flashCard = documentSnapshot.toObject(FlashCard.class);
                        flashCard.setCodigo(Integer.parseInt(documentSnapshot.getId()));
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
        }
        ArrayList<FlashCard> flashCards = null;
        try {
            final ExecutorService threadpool = Executors.newFixedThreadPool(3);
            RetornarFlashcards task = new RetornarFlashcards();
            Future<ArrayList<FlashCard>> futureTask = threadpool.submit(task);
            while(!futureTask.isDone()){
                Log.i(TAG, COLECAO_FLASHCARDS +" -> Buscando do BD...");
                Thread.sleep(10);
            }
            flashCards = futureTask.get();
        }catch(Exception e){
            Log.e(TAG, COLECAO_FLASHCARDS +" -> Falha ao buscar no BD... 😒");
            e.printStackTrace();
        }
        return flashCards;
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