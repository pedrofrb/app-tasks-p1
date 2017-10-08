package com.example.pedro.floatingbutton.logicaProduto;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pedro.floatingbutton.R;

/**
 * Created by pedro on 08/10/17.
 */

public class ItemProdutoViewHolder extends RecyclerView.ViewHolder {

    final TextView nome;

    public ItemProdutoViewHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.nome_produto_textview);
    }
}
