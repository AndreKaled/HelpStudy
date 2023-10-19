package com.example.helpstudy.view.adapters;

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

import androidx.fragment.app.FragmentManager;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.view.dialog.EditTarefaFragment;
import com.example.helpstudy.view.fragments.TarefasFragment;

import java.util.List;

public class TarefaAdapter extends BaseAdapter {

    static Context context;
    static List<Tarefa> list;
    private ControllerTarefas controllerTarefas;

    public TarefaAdapter(Context context){
        this.context = context;
        controllerTarefas = new ControllerTarefas(context);
        this.list = controllerTarefas.buscarTodos();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Tarefa getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);

        TextView textView_nome = v.findViewById(R.id.tituloTarefa);
        TextView textView_desc = v.findViewById(R.id.descricaoTarefa);
        Tarefa tarefa = list.get(position);
        textView_nome.setText(tarefa.getNome());
        textView_desc.setText(tarefa.getDescricao());
        Button bt = v.findViewById(R.id.menu_kebad);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(bt.getContext(), bt);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Tarefa t = controllerTarefas.buscarPorPosicao(position);

                        if (menuItem.getItemId() == R.id.editar) {

                            new EditTarefaFragment(t, controllerTarefas).show(FragmentManager.findFragment(parent).getFragmentManager(),"alalal");
                            Toast.makeText(bt.getContext(), "Editar", Toast.LENGTH_SHORT).show();

                        } else if (menuItem.getItemId() == R.id.deletar) {

                            Toast.makeText(bt.getContext(), "Deletar", Toast.LENGTH_SHORT).show();

                            controllerTarefas.remover(t);
                            TarefasFragment.updateViews();

                        } else {

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
