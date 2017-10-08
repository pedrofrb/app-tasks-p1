package com.example.pedro.floatingbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.pedro.floatingbutton.db.ProdutoDAO;

public class ProdutoCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_produto_cadastro);


    }

    public void onClickProduto(View v) {
        EditText nome = (EditText) findViewById(R.id.editText_produto_cadastro);

        ProdutoDAO dao = new ProdutoDAO(this);
        if (nome.getText().toString().length() > 0) {
            dao.inserirProduto(nome.getText().toString());
            finish();
        }
    }
}
