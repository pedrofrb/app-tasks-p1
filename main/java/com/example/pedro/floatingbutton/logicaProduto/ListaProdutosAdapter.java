package com.example.pedro.floatingbutton.logicaProduto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedro.floatingbutton.ProdutoAtualizar;
import com.example.pedro.floatingbutton.R;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ListaProdutosAdapter extends RecyclerView.Adapter<ListaProdutosAdapter.ItemProdutoViewHolder> {

    private List<Produto> produtos;
    private Context mContext;


    public ListaProdutosAdapter(Context context,List<Produto> produtos) {
        this.produtos = produtos;
        this.mContext = context;
    }

    @Override
    public ItemProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_produto,parent,false);
        ItemProdutoViewHolder viewHolder = new ItemProdutoViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemProdutoViewHolder holder, int position) {
        if(produtos.isEmpty()){
            return;
        }

        Produto p = produtos.get(position);
        holder.nome.setText(p.getNomeProduto());
        holder.itemView.setTag(p.getId());
    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void atualizaAdapter(List<Produto> novaLista){
        produtos = novaLista;
        this.notifyDataSetChanged();
    }

    public class ItemProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        final TextView nome;

        public ItemProdutoViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome_produto_textview);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            Intent it = new Intent(mContext, ProdutoAtualizar.class);
            it.putExtra("id_produto", itemView.getTag().toString());
            mContext.startActivity(it);
            return false;
        }
    }

}
