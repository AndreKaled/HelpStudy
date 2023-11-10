package com.example.helpstudy.datasource;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.controller.ControllerUsuario;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.model.Tarefa;
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
    private final String TAG = "DataBase", COLECAO_USUARIOS = "usuarios", COLECAO_FLASHCARDS = "flashcards", COLECAO_LISTAS = "listas", COLECAO_TAREFAS = "tarefas";
    private CollectionReference usuarioRef = dataBase.collection(COLECAO_USUARIOS);
    private CollectionReference flashcardRef;
    private CollectionReference listasRef, tarefaRef;
    private Context context;
    private Repository repository;

    public DataSource(Context context){
        this.context = context;
        repository = new Repository(context);
    }

    public void excluirLista(String id){
        Log.i(TAG,COLECAO_LISTAS +"-> excluindo lista " +id);
        listasRef.document(id).delete();
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

    public List<Usuario> consultaUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Usuario usuario = documentSnapshot.toObject(Usuario.class);
                    usuarios.add(usuario);
                }
                Log.i(TAG, COLECAO_USUARIOS +"-> Query realizada com sucesso!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "erro");
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

    public void alterarUsuario(Usuario usuario2){

        Usuario usuario = new Usuario();
        usuario.setNome(usuario2.getNome());
        usuario.setEmail(usuario2.getEmail());
        usuario.setSenha(usuario2.getSenha());
        usuario.setId(usuario2.getId());
        Log.i(TAG, COLECAO_USUARIOS +"-> alterando dados do usuário " +usuario);

        usuarioRef.document(ControllerUsuario.getIdUsuario()).set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    public void fazerBackup() throws RuntimeException{
        class FazBackupLista extends AsyncTask<Listas, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Listas... listas) {
                listasRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS);

                for (Listas l : listas) {
                    listasRef.document(String.valueOf(l.getId())).set(l).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i(TAG, COLECAO_LISTAS + "-> SALVO!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, COLECAO_LISTAS + "-> NÃO SALVO!");
                            throw new RuntimeException(e);
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
            }
        }

        //salvando todas as listas
        List<Listas> list = new ControllerListas(context).buscarTodos();
        new FazBackupLista().execute(list.toArray(new Listas[list.size()]));

        class FazBackupTarefas extends AsyncTask<Tarefa, Void, Void>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Tarefa... tarefas) {
                tarefaRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS).document(String.valueOf(ControllerTarefas.getListaSelecionada())).collection(COLECAO_TAREFAS);

                for (Tarefa tarefa: tarefas){
                    tarefaRef.document(String.valueOf(tarefa.getId())).set(tarefa).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i(TAG, COLECAO_TAREFAS + "-> SALVO!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, COLECAO_TAREFAS + "-> NÃO SALVO!");
                            throw new RuntimeException(e);
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }

        //salvando todas as tarefas das listas
        List<Listas> lis =new ControllerListas(context).buscarTodos();
        for(Listas lista: lis) {
            List<Tarefa> listTaref = new ControllerTarefas(context).buscarTodos();
            ControllerTarefas.setListaSelecionada(lista.getId());
            new FazBackupTarefas().execute(listTaref.toArray(new Tarefa[listTaref.size()]));
        }

        class FazBackupFlashcard extends AsyncTask<FlashCard, Void, Void>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(FlashCard... flashCards) {
                flashcardRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_FLASHCARDS);
                for (FlashCard f: flashCards) {
                    flashcardRef.document(String.valueOf(f.getCodigo())).set(f).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i(TAG, COLECAO_FLASHCARDS + "-> SALVO!");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)  {
                            Log.w(TAG, COLECAO_FLASHCARDS + "-> NÃO SALVO!");
                            throw new RuntimeException(e);
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(context, "Finalizou!", Toast.LENGTH_SHORT).show();
            }
        }

        //salvando todos os flashcards
        List<FlashCard> listFlash = new ControllerFlashCard(context).buscarTodos();
        new FazBackupFlashcard().execute(listFlash.toArray(new FlashCard[listFlash.size()]));
    }

    public void resgatarBackup() throws RuntimeException{

        class ResgataBackupListas extends AsyncTask<Void, Void, Void>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                listasRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS);
                listasRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Listas listas = documentSnapshot.toObject(Listas.class);
                            new ControllerListas(context).cadastrar(listas.getTitulo());
                        }
                        Log.i(TAG, COLECAO_LISTAS + "-> Query de listas feita!");
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, COLECAO_LISTAS + "-> Erro ao executar Query!");
                        throw new RuntimeException(e);
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }
        new ResgataBackupListas().execute();
        class ResgataBackupTarefas extends AsyncTask<Void, Void, Void>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                tarefaRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_LISTAS).document(String.valueOf(ControllerTarefas.getListaSelecionada())).collection(COLECAO_TAREFAS);
                tarefaRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Tarefa tarefa = documentSnapshot.toObject(Tarefa.class);
                            new ControllerTarefas(context).cadastrar(tarefa.getNome(), tarefa.getDescricao(), tarefa.getDataEntrega(),
                                    tarefa.isConcluida());
                            Log.i(TAG, COLECAO_TAREFAS +"-> Query feita!");
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, COLECAO_TAREFAS +"-> Erro ao executar Query!");
                        throw new RuntimeException(e);
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }
        for(Listas l: new ControllerListas(context).buscarTodos()){
            ControllerTarefas.setListaSelecionada(l.getId());
            new ResgataBackupTarefas().execute();
        }
        class ResgataBackupFlashcards extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                flashcardRef = usuarioRef.document(ControllerUsuario.getIdUsuario()).collection(COLECAO_FLASHCARDS);
                flashcardRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            FlashCard flashCard = documentSnapshot.toObject(FlashCard.class);
                            new ControllerFlashCard(context).cadastrar(flashCard.getTitulo(), flashCard.getDescricao());
                        }
                        Log.i(TAG, COLECAO_FLASHCARDS + "-> Query feita!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, COLECAO_FLASHCARDS + "-> Erro ao executar Query!");
                        throw new RuntimeException(e);
                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }
        new ResgataBackupFlashcards().execute();
    }
}