package com.example.pedro.floatingbutton.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.pedro.floatingbutton.R;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.ListaDeComprasContract.*;
import java.util.ArrayList;

/**
 * Created by pedro on 09/10/17.
 */

public class ListaDeComprasDAO {
    private SQLiteDatabase mdb;
    private ListaDeComprasDBHelper dbHelper;
    private Context context;


    public ListaDeComprasDAO(Context context) {
        this.dbHelper = new ListaDeComprasDBHelper(context);
        this.context = context;
    }


    public ArrayList<ListaDeCompras> getTodasListaDeCompras() {

        ArrayList<ListaDeCompras> lista = new ArrayList<ListaDeCompras>();

        try {
            Cursor cursor=null;

            String[] campos = {TabelaListaDeCompras._ID, TabelaListaDeCompras.COLUNA_NOME_LISTA,TabelaListaDeCompras.COLUNA_COR_LISTA,TabelaListaDeCompras.COLUNA_TIMESTAMP};

            this.mdb = dbHelper.getReadableDatabase();

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            String tipoOrdenacao= sharedPreferences.getString("ORDEM_LISTA","asc");

            if(tipoOrdenacao.equals("asc")) {

                cursor = mdb.query(TabelaListaDeCompras.NOME_TABELA, campos, null, null, null, null, TabelaListaDeCompras.COLUNA_NOME_LISTA);

            }else if(tipoOrdenacao.equals("date")){

                cursor = mdb.query(TabelaListaDeCompras.NOME_TABELA, campos, null, null, null, null, TabelaListaDeCompras.COLUNA_TIMESTAMP);

            }

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                while (!cursor.isLast()) {
                    lista.add(new ListaDeCompras(cursor));
                    cursor.moveToNext();
                }
                lista.add(new ListaDeCompras(cursor));
            }
            mdb.close();
        } catch (SQLiteException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return lista;
    }




    public  ListaDeCompras getTodasListasDeCompras(long id) {
        ListaDeCompras listaDeCompras = null;
        try {
            Cursor cursor;

            String[] campos = {TabelaListaDeCompras._ID, TabelaListaDeCompras.COLUNA_NOME_LISTA,TabelaListaDeCompras.COLUNA_COR_LISTA,TabelaListaDeCompras.COLUNA_TIMESTAMP};

            mdb = dbHelper.getReadableDatabase();

            cursor = mdb.query(TabelaListaDeCompras.NOME_TABELA, campos, TabelaListaDeCompras._ID + "=" + id, null, null, null, TabelaListaDeCompras.COLUNA_TIMESTAMP);


            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                listaDeCompras = new ListaDeCompras(cursor);
            }
            mdb.close();
        } catch (SQLiteException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return listaDeCompras;
    }

    public long inserirListaDeCompras(String nome,String cor) {

        long resultado = 0;
        try {
            this.mdb = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(TabelaListaDeCompras.COLUNA_NOME_LISTA, nome);
            valores.put(TabelaListaDeCompras.COLUNA_COR_LISTA, cor);

            resultado = mdb.insert(TabelaListaDeCompras.NOME_TABELA, null, valores);
            mdb.close();

            Toast.makeText(context, R.string.dao_produto_inserir, Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_inserir_erro, Toast.LENGTH_SHORT).show();
        }
        return resultado;
    }

    public boolean atualizarListaDeCompras(ListaDeCompras listaDeCompras) {
        boolean alterado = false;
        try {
            ContentValues valores = new ContentValues();
            valores.put(TabelaListaDeCompras.COLUNA_NOME_LISTA, listaDeCompras.getNome());
            valores.put(TabelaListaDeCompras.COLUNA_COR_LISTA, listaDeCompras.getCor());

            mdb = dbHelper.getWritableDatabase();
            alterado = mdb.update(TabelaListaDeCompras.NOME_TABELA, valores, TabelaListaDeCompras._ID + "=" + listaDeCompras.getId(), null) > 0;
            mdb.close();

            Toast.makeText(context, R.string.dao_produto_atualizar, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_atualizar_erro, Toast.LENGTH_SHORT).show();
        }
        return alterado;
    }

    public boolean excluirListaDeCompras(long id) {
        boolean excluido = false;
        try {
            this.mdb = dbHelper.getWritableDatabase();

            excluido = mdb.delete(TabelaListaDeComprasProduto.NOME_TABELA, TabelaListaDeComprasProduto.COLUNA_ID_LISTA + "=" + id, null) > 0;

            excluido = mdb.delete(TabelaListaDeCompras.NOME_TABELA, TabelaListaDeCompras._ID + "=" + id, null) > 0;
            mdb.close();
            Toast.makeText(context, R.string.dao_produto_excluir, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_excluir_erro, Toast.LENGTH_SHORT).show();
        }
        return excluido;
    }


}
