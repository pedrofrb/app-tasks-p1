package com.example.pedro.floatingbutton.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.example.pedro.floatingbutton.R;
import com.example.pedro.floatingbutton.db.ListaDeComprasContract.TabelaProduto;


import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.ArrayList;

/**
 * Created by pedro on 07/10/17.
 */

public class ProdutoDAO {
    private SQLiteDatabase mdb;
    private ListaDeComprasDBHelper dbHelper;
    private Context context;


    public ProdutoDAO(Context context) {
        this.dbHelper = new ListaDeComprasDBHelper(context);
        this.context = context;
    }


    public ArrayList<Produto> getTodosProdutos() {

        ArrayList<Produto> lista = new ArrayList<Produto>();

        try {
            Cursor cursor;

            String[] campos = {TabelaProduto._ID, TabelaProduto.COLUNA_NOME_PRODUTO};

            this.mdb = dbHelper.getReadableDatabase();

            cursor = mdb.query(TabelaProduto.NOME_TABELA, campos, null, null, null, null, TabelaProduto.COLUNA_NOME_PRODUTO);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                while (!cursor.isLast()) {
                    lista.add(new Produto(cursor));
                    cursor.moveToNext();
                }
                lista.add(new Produto(cursor));
            }
            mdb.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public  Produto getTodosProdutos(long id) {
        Produto produto = null;
        try {
            Cursor cursor;

            String[] campos = {TabelaProduto._ID, TabelaProduto.COLUNA_NOME_PRODUTO};

            mdb = dbHelper.getReadableDatabase();

            cursor = mdb.query(TabelaProduto.NOME_TABELA, campos, TabelaProduto._ID + "=" + id, null, null, null, TabelaProduto.COLUNA_NOME_PRODUTO);


            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                produto = new Produto(cursor);
            }
            mdb.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return produto;
    }

    public long inserirProduto(String nome) {

        long resultado = 0;
        try {
            this.mdb = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(TabelaProduto.COLUNA_NOME_PRODUTO, nome);

            resultado = mdb.insert(TabelaProduto.NOME_TABELA, null, valores);
            mdb.close();

            Toast.makeText(context, R.string.dao_produto_inserir, Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_inserir_erro, Toast.LENGTH_SHORT).show();
        }
        return resultado;
    }

    public boolean atualizarProduto(Produto produto) {
        boolean alterado = false;
        try {
            ContentValues valores = new ContentValues();
            valores.put(TabelaProduto.COLUNA_NOME_PRODUTO, produto.getNomeProduto());

            mdb = dbHelper.getWritableDatabase();
            alterado = mdb.update(TabelaProduto.NOME_TABELA, valores, TabelaProduto._ID + "=" + produto.getId(), null) > 0;
            mdb.close();

            Toast.makeText(context, R.string.dao_produto_atualizar, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_atualizar_erro, Toast.LENGTH_SHORT).show();
        }
        return alterado;
    }

    public boolean excluirProduto(long id) {
        boolean excluido = false;
        try {
            this.mdb = dbHelper.getWritableDatabase();
            excluido = mdb.delete(TabelaProduto.NOME_TABELA, TabelaProduto._ID + "=" + id, null) > 0;
            mdb.close();
            Toast.makeText(context, R.string.dao_produto_excluir, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_excluir_erro, Toast.LENGTH_SHORT).show();
        }
        return excluido;
    }




}
