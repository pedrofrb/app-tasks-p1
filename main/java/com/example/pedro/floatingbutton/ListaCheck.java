package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pedro.floatingbutton.db.ListaDeComprasProdutoDAO;
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


        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


        recyclerView.setLayoutManager(layout);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.atualizaAdapter(listaDeComprasProdutoDAO.getTodosProdutosFromLista(listaId));
    }
}
