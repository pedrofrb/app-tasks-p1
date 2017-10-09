package com.example.pedro.floatingbutton.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.pedro.floatingbutton.db.ListaDeComprasContract.*;

/**
 * Created by pedro on 07/10/17.
 */

public class ListaDeComprasDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="listaDecompras.db";
    private static final int DATABASE_VERSION=2;

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

        final String SQL_CREATE_TABLE_LISTA_DE_COMPRAS = "CREATE TABLE " + TabelaListaDeCompras.NOME_TABELA + "("
                + TabelaListaDeCompras._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TabelaListaDeCompras.COLUNA_NOME_LISTA + " TEXT NOT NULL,"
                + TabelaListaDeCompras.COLUNA_COR_LISTA + " TEXT NOT NULL,"
                +TabelaListaDeCompras.COLUNA_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ");";

        db.execSQL(SQL_CREATE_TABLE_LISTA_DE_COMPRAS);



        final String SQL_CREATE_TABLE_LISTA_DE_COMPRAS_PRODUTO = "CREATE TABLE " + TabelaListaDeComprasProduto.NOME_TABELA + "("  //FOREIGN KEY(trackartist) REFERENCES artist(artistid
                + TabelaListaDeComprasProduto._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TabelaListaDeComprasProduto.COLUNA_ID_LISTA + " INTEGER NOT NULL,"
                + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " INTEGER NOT NULL"
                +TabelaListaDeCompras.COLUNA_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                +"FOREIGN KEY("+TabelaListaDeComprasProduto.COLUNA_ID_LISTA+") REFERENCES "+TabelaListaDeCompras.NOME_TABELA+"("+TabelaListaDeCompras._ID+"),"
                +"FOREIGN KEY("+TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO+") REFERENCES "+TabelaProduto.NOME_TABELA+"("+TabelaProduto._ID+")"
                + ");";

        db.execSQL(SQL_CREATE_TABLE_LISTA_DE_COMPRAS_PRODUTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TabelaProduto.NOME_TABELA);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        } else {
            db.execSQL("PRAGMA foreign_keys=ON");
        }

    }
}
