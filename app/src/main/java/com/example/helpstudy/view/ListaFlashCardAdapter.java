package com.example.helpstudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;

import java.util.List;

public class ListaFlashCardAdapter extends BaseAdapter {
    Context context;
    List<FlashCard> flashCards;

    public ListaFlashCardAdapter(Context context){
        this.context = context;
        ControllerFlashCard controllerFlashCard = ControllerFlashCard.getInstancia();
        this.flashCards = controllerFlashCard.buscarTodos();
    }

    @Override
    public int getCount() {
        return flashCards.size();
    }

    @Override
    public FlashCard getItem(int i) {
        return flashCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_lista_flashcards, parent, false);

        TextView textView_titulo = v.findViewById(R.id.tituloCard);
        TextView textViewDescricao = v.findViewById(R.id.descricaoCard);

        FlashCard flashCard = flashCards.get(position);
        textView_titulo.setText(flashCard.getTitulo());
        textViewDescricao.setText(flashCard.getDescricao());

        System.out.println(flashCard);

        return v;
    }
}
