package com.example.pedro.floatingbutton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedro.floatingbutton.db.model.ListaDeCompras;


import java.util.List;

/**
 * Created by pedro on 08/10/17.
 */

public class ListaDeComprasAdapter extends RecyclerView.Adapter<ListaDeComprasAdapter.ItemListaViewHolder>{

    private List<ListaDeCompras> listas;
    private Context mContext;

    public ListaDeComprasAdapter(Context context, List<ListaDeCompras> listas) {
        this.listas = listas;
        this.mContext = context;
    }

    @Override
    public ItemListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_lista,parent,false);
        ItemListaViewHolder viewHolder = new ItemListaViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemListaViewHolder holder, int position) {
        if(listas.isEmpty()){
            return;
        }

        ListaDeCompras l = listas.get(position);
        holder.nome.setText(l.getNome());
        holder.itemView.setTag(l.getId());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public void atualizaAdapter(List<ListaDeCompras> novaLista){
        listas = novaLista;
        this.notifyDataSetChanged();
    }

    public class ItemListaViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        final TextView nome;

        public ItemListaViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nome_lista_textview);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            //TODO Logica de modificar lista
            //  Intent it = new Intent(mContext, ProdutoAtualizar.class);
           // it.putExtra("id_produto", itemView.getTag().toString());
            //mContext.startActivity(it);
            return false;
        }
    }

}
