package com.example.pedro.floatingbutton.db.model;

import android.database.Cursor;
import android.util.Log;

import com.example.pedro.floatingbutton.db.ListaDeComprasContract.*;

import java.sql.Timestamp;
import java.util.IllegalFormatException;

/**
 * Created by pedro on 08/10/17.
 */

public class ListaDeCompras {
    private long id;
    private String nome;
    private String cor;
    private Timestamp timestamp;

    public ListaDeCompras(String nome) {
        this.nome = nome;
    }

    public ListaDeCompras(Cursor cursor)throws IllegalArgumentException {
        this.id=cursor.getLong(cursor.getColumnIndex(TabelaListaDeCompras._ID));
        this.nome=cursor.getString(cursor.getColumnIndex(TabelaListaDeCompras.COLUNA_NOME_LISTA));
        this.cor=cursor.getString(cursor.getColumnIndex(TabelaListaDeCompras.COLUNA_COR_LISTA));
        this.timestamp=Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(TabelaListaDeCompras.COLUNA_TIMESTAMP)));
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
