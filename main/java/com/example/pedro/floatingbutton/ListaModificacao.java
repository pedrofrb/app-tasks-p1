package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedro.floatingbutton.db.ListaDeComprasDAO;
import com.example.pedro.floatingbutton.db.ListaDeComprasProdutoDAO;
import com.example.pedro.floatingbutton.db.ProdutoDAO;
import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListaModificacao extends AppCompatActivity {
    ProdutoDAO daoProduto = new ProdutoDAO(this);
    ListaDeComprasDAO daoLista = new ListaDeComprasDAO(this);
    ListaDeComprasProdutoDAO daoListaDeProdutos = new ListaDeComprasProdutoDAO(this);
    ListaDeCompras lista;
    ProdutosInnerListaDeComprasAdapter mAdapter;
    EditText editTextNomeLista;
    ConstraintLayout constraintLista;
    List<String> lines;
    Iterator<String>iterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_modificacao);

        Intent it = getIntent();

        lines= Arrays.asList(getResources().getStringArray(R.array.opcoes_cor_values));

        iterator=lines.iterator();

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete_produto_lista_add);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.auto_complete_list_item, daoProduto.getTodosProdutosString());
        autoCompleteTextView.setAdapter(stringArrayAdapter);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_listas);

        editTextNomeLista = (EditText) findViewById(R.id.editTextNomeLista);
        constraintLista = (ConstraintLayout) findViewById(R.id.constraint_lista_produtos);
        List<Produto> produtos;


        //Verifica se é uma alteração ou criação
        if (it.hasExtra("id_lista")) {
            long listaId = 0;
            try {
                listaId = Long.parseLong(it.getStringExtra("id_lista"));
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.lista_atualizar_erro, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            lista = daoLista.getTodasListasDeCompras(listaId);

            //TODO Ver como vai fazer com as cores
            ListaDeComprasProdutoDAO listaDeComprasProdutoDAO = new ListaDeComprasProdutoDAO(this);

            produtos = listaDeComprasProdutoDAO.getTodosProdutosFromLista(listaId);

        } else {
            long idDaNovaLista = daoLista.inserirListaDeCompras("Minha Lista", "default");
            lista = daoLista.getTodasListasDeCompras(idDaNovaLista);
            produtos = new ArrayList<Produto>();
        }

        editTextNomeLista.setText(lista.getNome());

        if (lista.getCor().equals("default")) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

            String cor = sharedPreferences.getString("COR_PADRAO", "FFF59D");
            constraintLista.setBackgroundColor(Color.parseColor("#" + cor));
        } else {
            constraintLista.setBackgroundColor(Color.parseColor("#" + lista.getCor()));
        }


        //Atualiza o nome da lista no BD logo após o campo do nome perder foco
        editTextNomeLista.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    lista.setNome(editTextNomeLista.getText().toString());
                    daoLista.atualizarListaDeCompras(lista);
                }
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                daoListaDeProdutos.excluirProdutoDaListaDeCompras(id, lista);
                mAdapter.atualizaAdapter(daoListaDeProdutos.getTodosProdutosFromLista(lista.getId()));
            }
        }).attachToRecyclerView(recyclerView);


        mAdapter = new ProdutosInnerListaDeComprasAdapter(this, produtos);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        recyclerView.setLayoutManager(layout);


    }

    public void OnClickAddProduto(View v) {

        AutoCompleteTextView autoCompleteTextViewProduto = (AutoCompleteTextView) findViewById(R.id.autoComplete_produto_lista_add);

        if (autoCompleteTextViewProduto.getText().toString().length() > 0) {
            Produto p = daoProduto.getTodosProdutos(autoCompleteTextViewProduto.getText().toString());
            if (p == null) {
                long id = daoProduto.inserirProduto(autoCompleteTextViewProduto.getText().toString());
                p = daoProduto.getTodosProdutos(id);
            }
            //Limpa a caixa de texto
            autoCompleteTextViewProduto.setText("");
            daoListaDeProdutos.inserirProdutoNaListaDeCompras(p, lista);
            mAdapter.atualizaAdapter(daoListaDeProdutos.getTodosProdutosFromLista(lista.getId()));
        }
    }

    public void OnClickChangeColor(View v){
        List<String> lines = Arrays.asList(getResources().getStringArray(R.array.opcoes_cor_values));
        final Iterator<String>it =lines.iterator();
        if(!iterator.hasNext()){
            iterator=lines.iterator();
            if(lista.getCor().equals("FFF59D")){
                it.next();
            }

        }
        lista.setCor(iterator.next());


        daoLista.atualizarListaDeCompras(lista);
        constraintLista.setBackgroundColor(Color.parseColor("#" + lista.getCor()));

    }

    public void onBackPressed() {
        //Em caso de o usuário diigtar o nome da lista e apertar back diretamente, o foco não será executado, portanto
        lista.setNome(editTextNomeLista.getText().toString());
        daoLista.atualizarListaDeCompras(lista);
        super.onBackPressed();
    }
}