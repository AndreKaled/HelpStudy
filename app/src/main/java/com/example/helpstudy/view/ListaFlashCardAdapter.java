package com.example.helpstudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.R;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.utils.ROOT;

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

        ControllerFlashCard controllerFlashCard = ControllerFlashCard.getInstancia();
        TextView textView_titulo = v.findViewById(R.id.tituloCard);
        TextView textViewDescricao = v.findViewById(R.id.descricaoCard);
        Button bt = v.findViewById(R.id.menu_kebad);
        FlashCard flashCard = flashCards.get(position);
        textView_titulo.setText(flashCard.getTitulo());
        textViewDescricao.setText(flashCard.getDescricao());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(bt.getContext(), bt);

                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId() == R.id.editar){

                            Toast.makeText(bt.getContext(), "Editar", Toast.LENGTH_SHORT).show();

                        } else if (menuItem.getItemId() == R.id.deletar){

                            Toast.makeText(bt.getContext(), "Deletar", Toast.LENGTH_SHORT).show();

                        } else{

                            Toast.makeText(bt.getContext(), "Erro", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
        return v;
    }
}
