package br.com.raphaelframos.pappofurado.model;

import androidx.annotation.NonNull;

public class Canal {

    private String nome;
    private String categoria;
    private String id;

    public Canal(){}

    public Canal(String nome, String categoria) {
        setCategoria(categoria);
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome() + " - " + getCategoria();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
