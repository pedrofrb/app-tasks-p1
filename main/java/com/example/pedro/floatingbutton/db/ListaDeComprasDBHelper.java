package com.example.pedro.floatingbutton.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pedro.floatingbutton.db.ListaDeComprasContract.*;

/**
 * Created by pedro on 07/10/17.
 */

public class ListaDeComprasDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="listaDecompras.db";
    private static final int DATABASE_VERSION=1;

    public ListaDeComprasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE_PRODUTO = "CREATE TABLE " + TabelaProduto.NOME_TABELA + "("
                + TabelaProduto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TabelaProduto.COLUNA_NOME_PRODUTO + " TEXT NOT NULL"
                + ");";

        db.execSQL(SQL_CREATE_TABLE_PRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TabelaProduto.NOME_TABELA);
        onCreate(db);
    }
}
