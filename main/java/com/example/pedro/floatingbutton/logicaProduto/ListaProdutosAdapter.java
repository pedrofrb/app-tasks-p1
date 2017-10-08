package com.example.pedro.floatingbutton.logicaProduto;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedro.floatingbutton.R;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ListaProdutosAdapter extends RecyclerView.Adapter<ItemProdutoViewHolder> {

    private List<Produto> produtos;

    public ListaProdutosAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public ItemProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_produto,parent,false);
        ItemProdutoViewHolder viewHolder = new ItemProdutoViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemProdutoViewHolder holder, int position) {
        Produto p = produtos.get(position);
        holder.nome.setText(p.getNomeProduto());
    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
