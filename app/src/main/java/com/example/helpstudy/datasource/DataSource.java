package com.example.helpstudy.datasource;

import androidx.annotation.NonNull;

import com.example.helpstudy.model.FlashCard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataSource {
    private FirebaseFirestore dataBase = FirebaseFirestore.getInstance();

    public void salvarFlashcard(String titulo, String descricao, int codigo){
        FlashCard flashCard = new FlashCard();
        flashCard.setTitulo(titulo);
        flashCard.setDescricao(descricao);
        flashCard.setCodigo(codigo);
        //cidsanbcibds

        dataBase.collection("flashcards").document(titulo).set(flashCard).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                System.out.println("Salvo!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Não salvo!");
            }
        });
    }
}
