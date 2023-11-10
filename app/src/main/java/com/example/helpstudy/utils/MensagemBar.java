package com.example.helpstudy.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.helpstudy.R;
import com.example.helpstudy.view.dialog.AddDialogFragment;
import com.google.android.material.snackbar.Snackbar;

public class MensagemBar implements View.OnClickListener {

    private static final int TEMPO_CURTO = Snackbar.LENGTH_LONG, TEMPO_LONGO = Snackbar.LENGTH_SHORT;
    private static final int TEMPO_PADRAO = TEMPO_CURTO;
    private static final String TEXTO_PADRAO = "Ok";
    private final View.OnClickListener listenerFechar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            meuSnackBar.dismiss();
        }
    };
    private View view;
    private Fragment fragment;
    private String mensagem;
    private Snackbar meuSnackBar;
    public MensagemBar(View view, String mensagem) {
        this.view = view;
        this.fragment = fragment;
        this.mensagem = mensagem;
    }
    public Snackbar defineSnack(){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_PADRAO);
        meuSnackBar.setAction(TEXTO_PADRAO,listenerFechar);
        return meuSnackBar;
    }
    public Snackbar defineSnack(String texto){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_PADRAO);
        meuSnackBar.setAction(texto, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meuSnackBar.dismiss();
            }
        });
        return meuSnackBar;
    }
    public Snackbar defineSnack(String[] texto, View.OnClickListener... acao){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_PADRAO);
        for (int i= 0; i < texto.length && i < acao.length; i++)
            meuSnackBar.setAction(texto[i],acao[i]);
        return meuSnackBar;
    }
    public Snackbar defineSnackLongo(){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_LONGO);
        meuSnackBar.setAction(TEXTO_PADRAO, listenerFechar);
        return meuSnackBar;
    }

    public Snackbar defineSnackLongo(String texto){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_LONGO);
        meuSnackBar.setAction(texto, listenerFechar);
        return meuSnackBar;
    }

    public Snackbar defineSnackLongo(String[] texto, View.OnClickListener... acao){
        meuSnackBar = Snackbar.make(view, mensagem, TEMPO_LONGO);
        for (int i= 0; i < texto.length && i < acao.length; i++)
            meuSnackBar.setAction(texto[i],acao[i]);
        return meuSnackBar;
    }

    public void mostrar(){
        meuSnackBar.show();
    }
    @Override
    public void onClick(View v) {

    }
}
