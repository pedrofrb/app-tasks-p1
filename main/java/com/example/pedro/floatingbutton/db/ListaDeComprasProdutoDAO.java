package com.example.pedro.floatingbutton.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.pedro.floatingbutton.R;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.ListaDeComprasContract.*;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.ArrayList;

/**
 * Created by pedro on 09/10/17.
 */

public class ListaDeComprasProdutoDAO {
    private SQLiteDatabase mdb;
    private ListaDeComprasDBHelper dbHelper;
    private Context context;
    private ListaDeComprasDAO listaDao;
    private ProdutoDAO produtoDao;


    public ListaDeComprasProdutoDAO(Context context) {
        this.dbHelper = new ListaDeComprasDBHelper(context);
        this.context = context;
        listaDao = new ListaDeComprasDAO(context);
        produtoDao = new ProdutoDAO(context);
    }

    public boolean isProdutoMarcado(long idProduto, long idListaDeCompras) {

        String selection = TabelaListaDeComprasProduto.COLUNA_ID_LISTA + "=" + idListaDeCompras
                        + " AND " + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + "=" + idProduto;

        try {




            String[] campos = {ListaDeComprasContract.TabelaListaDeComprasProduto.COLUNA_MARCADO};
            this.mdb = dbHelper.getReadableDatabase();

            //Cursor cursor = mdb.rawQuery("SELECT "+TabelaListaDeComprasProduto.COLUNA_MARCADO+" FROM "+TabelaListaDeComprasProduto.NOME_TABELA+
                 //   " WHERE "+TabelaListaDeComprasProduto.COLUNA_ID_LISTA + " = ?"
                 //   + " AND " + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " = ?",new String[]{String.valueOf(idProduto),String.valueOf(idProduto)});
          // Cursor cursor = mdb.query(ListaDeComprasContract.TabelaListaDeComprasProduto.NOME_TABELA, campos
            //        , selection, null, null, null, null);

            Cursor cursor = mdb.query(TabelaListaDeComprasProduto.NOME_TABELA, campos, selection, null, null, null, null);


            int estaMarcado=0;
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                estaMarcado = cursor.getInt(cursor.getColumnIndex(TabelaListaDeComprasProduto.COLUNA_MARCADO));

            }
            mdb.close();
            if (estaMarcado == 1) {

                return false;
            } else if(estaMarcado==2){
                return true;
            }

        }catch (SQLException|IllegalArgumentException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.lista_produto_is_marcado_error, Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean marcarProduto(long idProduto, long idListaDeCompras){
        boolean alterado = false;

        try {
            String selection =
                    TabelaListaDeComprasProduto.COLUNA_ID_LISTA + " = " + idListaDeCompras
                            + " AND " + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " = " + idProduto;


            String[] campos = {ListaDeComprasContract.TabelaListaDeComprasProduto.COLUNA_MARCADO};



            ContentValues valores = new ContentValues();

            valores.put(TabelaListaDeComprasProduto.COLUNA_MARCADO, 1);

            mdb = dbHelper.getWritableDatabase();
            alterado = mdb.update(TabelaListaDeComprasProduto.NOME_TABELA, valores, selection, null) > 0;
            mdb.close();

            Toast.makeText(context, R.string.lista_produto_marcar_success, Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.listaDecompras_marcar_error, Toast.LENGTH_SHORT).show();
        }
        return alterado;
    }

    public boolean desmarcarProduto(long idProduto, long idListaDeCompras){
        boolean alterado = false;

        try {
            String selection =
                    TabelaListaDeComprasProduto.COLUNA_ID_LISTA + " = " + idListaDeCompras
                            + " AND " + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " = " + idProduto;


            ContentValues valores = new ContentValues();

            valores.put(TabelaListaDeComprasProduto.COLUNA_MARCADO, 0);

            mdb = dbHelper.getWritableDatabase();
            alterado = mdb.update(TabelaListaDeComprasProduto.NOME_TABELA, valores, selection, null) > 0;
            mdb.close();

            Toast.makeText(context, R.string.lista_produto_desmarcar_success, Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.lista_produto_desmarcar_error, Toast.LENGTH_SHORT).show();
        }
        return alterado;
    }

    public long inserirProdutoNaListaDeCompras(Produto p,ListaDeCompras l) {

        long resultado = 0;
        try {
            this.mdb = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(TabelaListaDeComprasProduto.COLUNA_ID_LISTA, l.getId());
            valores.put(TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO, p.getId());

            resultado = mdb.insert(TabelaListaDeComprasProduto.NOME_TABELA, null, valores);
            mdb.close();

            Toast.makeText(context, R.string.dao_produto_inserir, Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_inserir_erro, Toast.LENGTH_SHORT).show();
        }
        return resultado;
    }

    public boolean excluirProdutoDaListaDeCompras(long idProduto,ListaDeCompras l) {
        String selection =
                TabelaListaDeComprasProduto.COLUNA_ID_LISTA + " = " + l.getId()
                        + " AND " + TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " = " + idProduto;
        boolean excluido = false;
        try {
            this.mdb = dbHelper.getWritableDatabase();


            excluido = mdb.delete(TabelaListaDeComprasProduto.NOME_TABELA, selection, null) > 0;
            mdb.close();
            Toast.makeText(context, R.string.dao_produto_excluir, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_excluir_erro, Toast.LENGTH_SHORT).show();
        }
        return excluido;
    }

    public ArrayList<Produto> getTodosProdutosFromLista(long id) {

        ArrayList<Produto> lista = new ArrayList<Produto>();

        try {
            Cursor cursor;

            String[] campos = {TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO};

            this.mdb = dbHelper.getReadableDatabase();

            cursor = mdb.query(TabelaListaDeComprasProduto.NOME_TABELA, campos, TabelaListaDeComprasProduto.COLUNA_ID_LISTA + "=" + id, null, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                while (!cursor.isLast()) {
                    long idProduto =cursor.getLong(cursor.getColumnIndex(TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO));
                    lista.add(produtoDao.getTodosProdutos(idProduto));
                    cursor.moveToNext();
                }
                long idProduto =cursor.getLong(cursor.getColumnIndex(TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO));
                lista.add(produtoDao.getTodosProdutos(idProduto));
            }
            mdb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean excluirProdutoDeTodasListasDeCompras(long idProduto) {

        boolean excluido = false;
        try {
            this.mdb = dbHelper.getWritableDatabase();


            excluido = mdb.delete(TabelaListaDeComprasProduto.NOME_TABELA, TabelaListaDeComprasProduto.COLUNA_ID_PRODUTO + " = " + idProduto, null) > 0;
            mdb.close();
            Toast.makeText(context, R.string.dao_produto_excluir, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.dao_produto_excluir_erro, Toast.LENGTH_SHORT).show();
        }
        return excluido;
    }

}