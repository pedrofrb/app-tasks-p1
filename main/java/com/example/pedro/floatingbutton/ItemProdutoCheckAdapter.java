package com.example.pedro.floatingbutton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.pedro.floatingbutton.db.ListaDeComprasProdutoDAO;
import com.example.pedro.floatingbutton.db.model.Produto;

import java.util.List;

/**
 * Created by pedro on 10/10/17.
 */

public class ItemProdutoCheckAdapter extends RecyclerView.Adapter<ItemProdutoCheckAdapter.ItemProdutoCheckViewHolder> {

    private List<Produto> produtos;
    private Context mContext;
    ListaDeComprasProdutoDAO dao;
    long idLista;


    public ItemProdutoCheckAdapter(Context context, List<Produto> produtos, long id) {
        this.idLista = id;
        this.produtos = produtos;
        this.mContext = context;
        dao = new ListaDeComprasProdutoDAO(context);
    }

    @Override
    public ItemProdutoCheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_produto_check, parent, false);
        ItemProdutoCheckViewHolder viewHolder = new ItemProdutoCheckViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemProdutoCheckViewHolder holder, int position) {
        if (produtos.isEmpty()) {
            return;
        }

        Produto p = produtos.get(position);
        holder.nome.setText(p.getNomeProduto());
        if (dao.isProdutoMarcado(p.getId(), idLista)) {
            holder.checkBoxProduto.setChecked(true);
        }

        holder.itemView.setTag(p.getId());

    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void atualizaAdapter(List<Produto> novaLista) {
        produtos = novaLista;
        this.notifyDataSetChanged();
    }

    public class ItemProdutoCheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TODO Modificar ViewHolder para novo cardview
        final TextView nome;
        final CheckBox checkBoxProduto;

        public ItemProdutoCheckViewHolder(final View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.nome_produto_textview_check);
            checkBoxProduto = (CheckBox) itemView.findViewById(R.id.produto_checkBox);
            itemView.setOnClickListener(this);
            checkBoxProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long idDoProduto = 0;

                    try {
                        idDoProduto = Long.valueOf(itemView.getTag().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    Log.i("hue",String.valueOf(idDoProduto));
                    if (checkBoxProduto.isChecked()) {

                        dao.marcarProduto(idDoProduto, idLista);
                    } else {
                        dao.desmarcarProduto(idDoProduto, idLista);
                    }
                    atualizaAdapter(dao.getTodosProdutosFromLista(idLista));
                }


            });

        }

        @Override
        public void onClick(View v) {
            long idDoProduto = 0;
            try {
                idDoProduto = Long.valueOf(itemView.getTag().toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (checkBoxProduto.isChecked()) {

                dao.desmarcarProduto(idDoProduto, idLista);
                checkBoxProduto.setChecked(false);
            } else {
                dao.marcarProduto(idDoProduto, idLista);
                checkBoxProduto.setChecked(true);
            }
            atualizaAdapter(dao.getTodosProdutosFromLista(idLista));
        }
    }

}

