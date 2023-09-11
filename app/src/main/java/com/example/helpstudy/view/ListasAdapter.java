package com.example.helpstudy.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.helpstudy.R;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.datasource.DataSource;
import com.example.helpstudy.model.Listas;
import java.util.List;

public class ListasAdapter extends BaseAdapter {

    static Context context;
    static List<Listas> listas;

    public ListasAdapter(Context context){
        this.context = context;
        ControllerListas controllerListas = ControllerListas.getInstancia();
        this.listas = controllerListas.buscarTodos();
    }

    @Override
    public int getCount() {
        return listas.size();
    }

    @Override
    public Listas getItem(int i) {
        return listas.get(i);
    }
 //ERRO AQUI AAA
    @Override
    public long getItemId(int i) {
        return i;//getItem(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.itens_listas, parent, false);

        TextView textView_titulo = v.findViewById(R.id.tituloLista);

        Listas list = listas.get(position);
        textView_titulo.setText(list.getTitulo());
        return v;
    }
}
