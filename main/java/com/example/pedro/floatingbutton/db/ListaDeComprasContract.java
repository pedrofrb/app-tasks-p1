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

}
