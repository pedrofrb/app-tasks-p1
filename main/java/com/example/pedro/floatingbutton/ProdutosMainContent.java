package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedro.floatingbutton.db.ListaDeComprasProdutoDAO;
import com.example.pedro.floatingbutton.db.ProdutoDAO;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ProdutosMainContent extends Fragment {
    ListaProdutosAdapter mAdapter;
    ProdutoDAO dao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.main_content_produtos,container,false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);



        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle_view_produtos);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id =(long) viewHolder.itemView.getTag();
                ListaDeComprasProdutoDAO daoLista = new ListaDeComprasProdutoDAO(rootView.getContext());
                daoLista.excluirProdutoDeTodasListasDeCompras(id);
                dao.excluirProduto(id);
                mAdapter.atualizaAdapter(dao.getTodosProdutos());
            }
        }).attachToRecyclerView(recyclerView);




        dao = new ProdutoDAO(rootView.getContext());


        List<Produto> produtos = dao.getTodosProdutos();

        mAdapter = new ListaProdutosAdapter(rootView.getContext(),produtos);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.VERTICAL,false);


        recyclerView.setLayoutManager(layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(rootView.getContext(),ProdutoCadastro.class);
                startActivity(it);



            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.atualizaAdapter(dao.getTodosProdutos());
    }
}
