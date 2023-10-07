package com.example.helpstudy.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataLocal extends SQLiteOpenHelper {

    private static final String NAME_DATA_LOCAL = "HelpStudy";
    private static final int VERSION = 1;
    public final String TABELA_FLASHCARDS = "Flashcards";
    public final String TITULO_FLASHCARD = "tituloFlashcard", DESCRICAO_FLASHCARD = "descricaoFlashcard", ID_FLASHCARD = "idFlashcard";
    public final String TABELA_LISTAS ="Listas";
    public final String TITULO_LISTA = "tituloLista", ID_LISTA = "idLista";
    public final String TABELA_TAREFAS = "Tarefas";
    public final String ID_TAREFA = "idTarefa", NOME_TAREFA = "tituloTarefa", DESCRICAO_TAREFA = "descricaoTarefa", CHAVE_ESTRANGEIRA_TAREFA = "lista",
    DATA_ENTREGA_TAREFA = "dataEntrega", CONCLUIDA_TAREFA = "concluida";


    public DataLocal(Context context) {
        super(context, NAME_DATA_LOCAL, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_FLASHCARDS = " CREATE TABLE " +TABELA_FLASHCARDS +"(" +
                ID_FLASHCARD +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITULO_FLASHCARD +" TEXT NOT NULL," +
                DESCRICAO_FLASHCARD +" TEXT );";

        String SQL_LISTAS = " CREATE TABLE " +TABELA_LISTAS +"(" +
                ID_LISTA +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITULO_LISTA +" TEXT NOT NULL);";

        String SQL_TAREFAS = " CREATE TABLE " +TABELA_TAREFAS +"(" +
                ID_TAREFA +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME_TAREFA +" TEXT NOT NULL," +
                DESCRICAO_TAREFA +" TEXT," +
                DATA_ENTREGA_TAREFA +" TEXT, " +
                CONCLUIDA_TAREFA +" INTEGER NOT NULL CHECK(" +CONCLUIDA_TAREFA +" = 0 OR " +CONCLUIDA_TAREFA +" = 1)," +
                CHAVE_ESTRANGEIRA_TAREFA +" INTEGER," +
                " FOREIGN KEY(" +CHAVE_ESTRANGEIRA_TAREFA +") REFERENCES " +TABELA_LISTAS+"(" +ID_LISTA +"));";


        sqLiteDatabase.execSQL(SQL_FLASHCARDS);
        sqLiteDatabase.execSQL(SQL_LISTAS);
        sqLiteDatabase.execSQL(SQL_TAREFAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
