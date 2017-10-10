package com.example.pedro.floatingbutton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedro.floatingbutton.db.ListaDeComprasDAO;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;

import java.util.ArrayList;
import java.util.List;


public class ListasMainContent extends Fragment {
    ListaDeComprasAdapter mAdapter;
    ListaDeComprasDAO listaDeComprasDAO;

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
                listaDeComprasDAO.excluirListaDeCompras(id);
                mAdapter.atualizaAdapter(listaDeComprasDAO.getTodasListaDeCompras());
                // TODO Lógica de apagar Lista
                //dao.excluirProduto(id);
                //mAdapter.atualizaAdapter(dao.getTodosProdutos());
            }
        }).attachToRecyclerView(recyclerView);

        //Será substituido pelo DAO

        listaDeComprasDAO = new ListaDeComprasDAO(rootView.getContext());
        listaDeComprasDAO.inserirListaDeCompras("Lista listosa","FFEB3B");
        listaDeComprasDAO.inserirListaDeCompras("Lista listosa verde","C5E1A5");



        List<ListaDeCompras> listas = listaDeComprasDAO.getTodasListaDeCompras();





        mAdapter = new ListaDeComprasAdapter(rootView.getContext(),listas);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(rootView.getContext(),ListaModificacao.class);
                startActivity(it);



            }
        });

        return rootView;

    }




}
