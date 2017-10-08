package com.example.pedro.floatingbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pedro.floatingbutton.db.model.Produto;
import com.example.pedro.floatingbutton.logicaProduto.ListaProdutosAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ProdutosMainContent extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.main_content_produtos,container,false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(),"\uD83D\uDE2C", Toast.LENGTH_SHORT).show();
                // TODO: 07/10/17 Ação do Click do Floating Button

            }
        });

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle_view_produtos);

        List<Produto> produtos = new ArrayList<Produto>();

        produtos.add(new Produto("Arroz"));
        produtos.add(new Produto("Feijão"));
        produtos.add(new Produto("Pamonha"));
        produtos.add(new Produto("Paçoca"));

        recyclerView.setAdapter(new ListaProdutosAdapter(produtos));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);

        return rootView;
    }




}
