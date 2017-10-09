package com.example.pedro.floatingbutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedro.floatingbutton.db.model.ListaDeCompras;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

/**
 * Created by pedro on 09/10/17.
 */

public class ProdutosInnerListaDeComprasAdapter extends RecyclerView.Adapter<ProdutosInnerListaDeComprasAdapter.ItemProdutoInnerListaViewHolder> {
    private List<Produto> produtos;
    private Context mContext;

    public ProdutosInnerListaDeComprasAdapter(Context context, List<Produto> produtos) {
        this.produtos = produtos;
        this.mContext = context;
    }

    @Override
    public ProdutosInnerListaDeComprasAdapter.ItemProdutoInnerListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_produto_inner_lista,parent,false);
        ProdutosInnerListaDeComprasAdapter.ItemProdutoInnerListaViewHolder viewHolder = new ProdutosInnerListaDeComprasAdapter.ItemProdutoInnerListaViewHolder(view);
        return viewHolder;

    }

    public void onBindViewHolder(ProdutosInnerListaDeComprasAdapter.ItemProdutoInnerListaViewHolder holder, int position) {
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


    public class ItemProdutoInnerListaViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        //TODO Modificar ViewHolder para novo cardview
        final TextView nome;

        public ItemProdutoInnerListaViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome_produto_da_lista_textview);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            //TODO Logica de tirar produto da lista
            //  Intent it = new Intent(mContext, ProdutoAtualizar.class);
            // it.putExtra("id_produto", itemView.getTag().toString());
            //mContext.startActivity(it);
            return false;
        }
    }

}
