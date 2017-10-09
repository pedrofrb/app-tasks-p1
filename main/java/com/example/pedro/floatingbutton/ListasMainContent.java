package com.example.pedro.floatingbutton;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedro.floatingbutton.db.model.ListaDeCompras;

import java.util.ArrayList;
import java.util.List;


public class ListasMainContent extends Fragment {
    ListaDeComprasAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_listas_main_content,container,false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.floating_button_lista);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle_view_listas);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id =(long) viewHolder.itemView.getTag();
                // TODO Lógica de apagar Lista
                //dao.excluirProduto(id);
                //mAdapter.atualizaAdapter(dao.getTodosProdutos());
            }
        }).attachToRecyclerView(recyclerView);

        //Será substituido pelo DAO

        List<ListaDeCompras> listas = new ArrayList<ListaDeCompras>();

        ListaDeCompras l = new ListaDeCompras();

        l.setNome("Lista marota!");
        l.setId(123);
        listas.add(l);

        mAdapter = new ListaDeComprasAdapter(rootView.getContext(),listas);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Criar logica de adicionar lista
                //Intent it = new Intent(rootView.getContext(),ProdutoCadastro.class);
                //startActivity(it);



            }
        });

        return rootView;

    }




}
