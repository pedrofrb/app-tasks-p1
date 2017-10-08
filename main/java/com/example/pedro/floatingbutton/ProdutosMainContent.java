package com.example.pedro.floatingbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pedro.floatingbutton.db.model.Produto;
import com.example.pedro.floatingbutton.logicaProduto.ListaProdutosAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ProdutosMainContent extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content_produtos);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_produtos);

        List<Produto> produtos = new ArrayList<Produto>();

        produtos.add(new Produto("Arroz"));
        produtos.add(new Produto("Feijão"));
        produtos.add(new Produto("Pamonha"));
        produtos.add(new Produto("Paçoca"));

        recyclerView.setAdapter(new ListaProdutosAdapter(produtos));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);



    }

}
