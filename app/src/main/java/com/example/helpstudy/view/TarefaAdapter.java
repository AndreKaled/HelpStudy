package com.example.helpstudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.Tarefa;
import com.example.helpstudy.utils.ROOT;

import java.util.List;

public class TarefaAdapter extends BaseAdapter {

    static Context context;
    static List<Tarefa> list;

    public TarefaAdapter(Context context){
        this.context = context;
        ControllerTarefas controllerTarefas = ControllerTarefas.getInstancia();
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
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tarefa, viewGroup, false);

        TextView textView_nome = v.findViewById(R.id.tituloTarefa);
        TextView textView_desc = v.findViewById(R.id.descricaoTarefa);

        Tarefa tarefa = list.get(position);
        textView_nome.setText(tarefa.getNome());
        textView_desc.setText(tarefa.getDescricao());

        return v;
    }

}
