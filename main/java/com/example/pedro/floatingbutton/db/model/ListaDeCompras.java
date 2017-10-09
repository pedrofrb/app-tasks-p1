package com.example.pedro.floatingbutton.db.model;

/**
 * Created by pedro on 08/10/17.
 */

public class ListaDeCompras {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
}
