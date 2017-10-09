package com.example.pedro.floatingbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.pedro.floatingbutton.db.ProdutoDAO;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

public class ListaModificacao extends AppCompatActivity {
    ProdutoDAO daoProduto= new ProdutoDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_modificacao);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoComplete_produto_lista_add);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.auto_complete_list_item, daoProduto.getTodosProdutosString());
        autoCompleteTextView.setAdapter(stringArrayAdapter);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_listas);

        ProdutoDAO dao = new ProdutoDAO(this);


        List<Produto> produtos = dao.getTodosProdutos();

        ProdutosInnerListaDeComprasAdapter mAdapter = new ProdutosInnerListaDeComprasAdapter(this,produtos);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };


        recyclerView.setLayoutManager(layout);




    }
}
