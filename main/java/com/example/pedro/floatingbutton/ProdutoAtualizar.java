package com.example.pedro.floatingbutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedro.floatingbutton.db.ProdutoDAO;
import com.example.pedro.floatingbutton.db.model.Produto;

public class ProdutoAtualizar extends AppCompatActivity {
    Produto p;
    ProdutoDAO dao;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_atualizar);

        Intent it = getIntent();
        long produtoId = 0;
        try{
             produtoId = Long.parseLong(it.getStringExtra("id_produto"));
        }catch (NumberFormatException e){
            Toast.makeText(this, R.string.produto_adapter_conversao, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        dao = new ProdutoDAO(this);

        p = dao.getTodosProdutos(produtoId);

        ed = (EditText) findViewById(R.id.editText_produto_atualizar);

        ed.setText(p.getNomeProduto());

    }
    public void onClickProduto(View v){
        if(ed.getText().toString().length()>0){
            p.setNomeProduto(ed.getText().toString());

            dao.atualizarProduto(p);
            finish();
        }
    }
}
