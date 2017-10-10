package com.example.pedro.floatingbutton.db;

import android.provider.BaseColumns;

/**
 * Created by pedro on 07/10/17.
 */

public class ListaDeComprasContract {
    private ListaDeComprasContract() {}


    public static final class TabelaProduto implements BaseColumns{
        public static final String NOME_TABELA = "produto";
        public static final String COLUNA_NOME_PRODUTO = "nomeProduto";
    }

    public static final class TabelaListaDeCompras implements BaseColumns{
        public static final String NOME_TABELA = "listaDeCompras";
        public static final String COLUNA_NOME_LISTA = "nomeLista";
        public static final String COLUNA_COR_LISTA = "corLista";
        public static final String COLUNA_TIMESTAMP = "timestamp";
    }

    public static final class TabelaListaDeComprasProduto implements BaseColumns{
        public static final String NOME_TABELA = "listaDeComprasProduto";
        public static final String COLUNA_ID_LISTA = "idLista";
        public static final String COLUNA_ID_PRODUTO = "idProduto";
        public static final String COLUNA_MARCADO = "isMarcado";
    }

}
