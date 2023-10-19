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
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.view.dialog.EditListFragment;
import com.example.helpstudy.view.fragments.ListaFragment;

import java.util.List;

public class ListasAdapter extends BaseAdapter {

    static Context context;
    static List<Listas> listas;
    private ControllerListas controllerListas;

    public ListasAdapter(Context context){
        this.context = context;
        controllerListas = new ControllerListas(context);
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

        Button bt = v.findViewById(R.id.menu_kebad);
        Listas list = listas.get(position);
        textView_titulo.setText(list.getTitulo());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(bt.getContext(), bt);

                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId() == R.id.editar){
                            new EditListFragment(list, controllerListas).show(FragmentManager.findFragment(parent).getFragmentManager(),"alalal");
                            Toast.makeText(bt.getContext(), "Editar lista", Toast.LENGTH_SHORT).show();
                        } else if (menuItem.getItemId() == R.id.deletar){
                            controllerListas.remover(list);
                            ListaFragment.updateView();
                            Toast.makeText(bt.getContext(), "Editar deletar", Toast.LENGTH_SHORT).show();
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

    public void atualizaView(){
        notifyAll();
    }

}
