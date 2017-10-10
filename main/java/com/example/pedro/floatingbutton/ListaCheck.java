package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pedro.floatingbutton.db.ListaDeComprasDAO;
import com.example.pedro.floatingbutton.db.ListaDeComprasProdutoDAO;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

public class ListaCheck extends AppCompatActivity {
    long listaId;
    ListaDeComprasProdutoDAO listaDeComprasProdutoDAO;
    ItemProdutoCheckAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_check);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_check);



        Intent it = getIntent();

        try {
            listaId = Long.parseLong(it.getStringExtra("id_lista"));
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.lista_atualizar_erro, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        listaDeComprasProdutoDAO = new ListaDeComprasProdutoDAO(this);
        List<Produto> lista = listaDeComprasProdutoDAO.getTodosProdutosFromLista(listaId);

        mAdapter = new ItemProdutoCheckAdapter(this,lista,listaId);

        CardView card=  (CardView) findViewById(R.id.constraint_lista_checked);

        ListaDeComprasDAO daoListaDeCompras = new ListaDeComprasDAO(this);
        ListaDeCompras listaDeCompras = daoListaDeCompras.getTodasListasDeCompras(listaId);

        setTitle(listaDeCompras.getNome());

        if(listaDeCompras.getCor().equals("default")){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            String cor= sharedPreferences.getString("COR_PADRAO","FFF59D");
            card.setBackgroundColor(Color.parseColor("#"+cor));
        }else{
            card.setBackgroundColor(Color.parseColor("#"+listaDeCompras.getCor()));
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layout);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.atualizaAdapter(listaDeComprasProdutoDAO.getTodosProdutosFromLista(listaId));
    }
}
