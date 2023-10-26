package com.example.helpstudy.view.dialog;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.utils.MensagemBar;
import com.example.helpstudy.view.fragments.FlashCardFragment;

public class EditFlashcardFragment extends DialogFragment {

    private EditText editTituloFlashcard, editRespostaFlashcard;
    private Button btnConfirmaFlashcard;

    private FlashCard flashCard;
    private ControllerFlashCard c;

    public EditFlashcardFragment(FlashCard flashCard, ControllerFlashCard c){
        this.flashCard = flashCard;
        this.c = c;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_flashcard, container, false);
        editTituloFlashcard = v.findViewById(R.id.editTextTituloFlashcard);
        editRespostaFlashcard = v.findViewById(R.id.editTextRespostaFlashcard);
        btnConfirmaFlashcard = v.findViewById(R.id.buttonConfirmaFlashcardEdit);

        editTituloFlashcard.setText(flashCard.getTitulo());
        editRespostaFlashcard.setText(flashCard.getDescricao());

        btnConfirmaFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashCard.setTitulo(editTituloFlashcard.getText().toString());
                flashCard.setDescricao(editRespostaFlashcard.getText().toString());
                c.atualizar(flashCard);
                FlashCardFragment.updateViews();
                dismiss();
                MensagemBar msg = new MensagemBar(new FlashCardFragment().findLayoutView(), "Flashcard alterado!");
                msg.defineSnackLongo();
                msg.mostrar();
            }
        });
        return v;
    }
}