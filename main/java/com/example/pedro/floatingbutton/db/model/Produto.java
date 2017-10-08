package com.example.pedro.floatingbutton.db.model;

import android.database.Cursor;

import com.example.pedro.floatingbutton.db.ListaDeComprasContract.TabelaProduto;

/**
 * Created by pedro on 07/10/17.
 */

public class Produto {
    private long id;
    private String nomeProduto;

    public Produto(Cursor cursor) {
        this.id=cursor.getLong(cursor.getColumnIndex(TabelaProduto._ID));
        this.nomeProduto=cursor.getString(cursor.getColumnIndex(TabelaProduto.COLUNA_NOME_PRODUTO));
    }

    public Produto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
