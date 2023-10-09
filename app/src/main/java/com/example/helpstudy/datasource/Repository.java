package com.example.helpstudy.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.helpstudy.controller.ControllerFlashCard;
import com.example.helpstudy.controller.ControllerListas;
import com.example.helpstudy.controller.ControllerTarefas;
import com.example.helpstudy.model.FlashCard;
import com.example.helpstudy.model.Listas;
import com.example.helpstudy.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    DataLocal data;

    public Repository(Context context) {
        data = new DataLocal(context);
    }

    public void buscarListas(){
        SQLiteDatabase db = data.getReadableDatabase();
        String sql = "SELECT * FROM " +data.TABELA_LISTAS;
        Cursor c = db.rawQuery(sql, null);
        while(c.moveToNext()){
            Listas l = new Listas();
            l.setId(c.getLong(0));
            l.setTitulo(c.getString(1));
            ControllerListas.add(l);
        }
        c.close();
        db.close();
    }

    public void buscarFlashcards(){
        SQLiteDatabase db = data.getReadableDatabase();
        String sql = " SELECT * FROM " +data.TABELA_FLASHCARDS;
        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext()){
            FlashCard f = new FlashCard();
            f.setCodigo(c.getLong(0));
            f.setTitulo(c.getString(1));
            f.setDescricao(c.getString(2));
            ControllerFlashCard.add(f);
        }
        c.close();
        db.close();
    }

    public void buscarTarefas(long idLista){
        SQLiteDatabase db = data.getReadableDatabase();
        String sql = " SELECT * FROM " +data.TABELA_TAREFAS +" WHERE " +data.CHAVE_ESTRANGEIRA_TAREFA
                +" = ? ";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(idLista)});
        while(c.moveToNext()){
            Tarefa t = new Tarefa();
            t.setId(c.getLong(0));
            t.setNome(c.getString(1));
            t.setDescricao(c.getString(2));
            t.setDataEntrega(c.getString(3));
            t.setConcluida(c.getInt(4)==0 ? false : true);
            ControllerTarefas.add(t);
        }
        c.close();
        db.close();
    }

    public boolean deletarFlashcard(long idFlashcard){
        SQLiteDatabase db = data.getWritableDatabase();
        int i = db.delete(data.TABELA_FLASHCARDS, data.ID_FLASHCARD +" = ? ", new String[]{String.valueOf(idFlashcard)});
        if(i != 0)
            return true;
        return false;
    }

    public boolean deletarLista(long idLista){
        SQLiteDatabase db = data.getWritableDatabase();
        int i = db.delete(data.TABELA_LISTAS, data.ID_LISTA +" = ? ", new String[]{String.valueOf(idLista)});
        if(i != 0)
            return true;
        return false;
    }

    public boolean deletarTarefa(long idLista,long idTarefa){
        SQLiteDatabase db = data.getWritableDatabase();
        String[] args = {String.valueOf(idTarefa), String.valueOf(idLista)};
        int i = db.delete(data.TABELA_TAREFAS, data.ID_TAREFA +" = ? AND "
                +data.CHAVE_ESTRANGEIRA_TAREFA +" = ? ", args);
        if(i != 0)
            return true;
        return false;
    }

    public boolean adicionarTarefa(Tarefa tarefa, long idLista){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.NOME_TAREFA, tarefa.getNome());
        values.put(data.DESCRICAO_TAREFA, tarefa.getDescricao());
        values.put(data.DATA_ENTREGA_TAREFA, tarefa.getDataEntrega());
        values.put(data.CONCLUIDA_TAREFA, tarefa.isConcluida() ? 1 : 0);
        values.put(data.CHAVE_ESTRANGEIRA_TAREFA, idLista);

        return db.insert(data.TABELA_TAREFAS, null, values) != -1 ? true : false;
    }

    public boolean adicionarLista(Listas lista){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.TITULO_LISTA, lista.getTitulo());

        return db.insert(data.TABELA_LISTAS, null, values) != -1 ? true : false;
    }

    public boolean adicionarFlashcard(FlashCard flashcard){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.TITULO_FLASHCARD, flashcard.getTitulo());
        values.put(data.DESCRICAO_FLASHCARD, flashcard.getDescricao());

        return db.insert(data.TABELA_FLASHCARDS, null, values) != -1 ? true : false;
    }

    public boolean atualizarTarefa(Tarefa tarefa, long idLista){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.NOME_TAREFA, tarefa.getNome());
        values.put(data.DESCRICAO_TAREFA, tarefa.getDescricao());
        values.put(data.DATA_ENTREGA_TAREFA, tarefa.getDataEntrega());
        values.put(data.CONCLUIDA_TAREFA, tarefa.isConcluida() ? 1 : 0);

        return db.update(data.TABELA_TAREFAS, values,data.ID_TAREFA +" = ? AND "
                +data.CHAVE_ESTRANGEIRA_TAREFA +" = ?", new String[]{String.valueOf(tarefa.getId()), String.valueOf(idLista)}) != -1 ? true : false;
    }

    public boolean atualizarLista(Listas lista){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.TITULO_LISTA, lista.getTitulo());

        return db.update(data.TABELA_LISTAS, values,data.ID_LISTA +" = ?", new String[]{String.valueOf(lista.getId())}) != -1 ? true : false;
    }

    public boolean atualizarFlashcard(FlashCard flashcard){
        SQLiteDatabase db = data.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(data.TITULO_FLASHCARD, flashcard.getTitulo());
        values.put(data.DESCRICAO_FLASHCARD, flashcard.getDescricao());

        return db.update(data.TABELA_FLASHCARDS, values,data.ID_FLASHCARD +" = ? ",
                new String[]{String.valueOf(flashcard.getCodigo())}) != -1 ? true : false;
    }
}
