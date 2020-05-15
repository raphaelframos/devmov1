package br.com.raphaelframos.carrinhodecompras.model;

import androidx.annotation.NonNull;

public class ItemDoPedido {

    private String nome;
    private double preco;
    private int quantidade;

    public ItemDoPedido(){
        setNome("Coca");
        setPreco(10);
        setQuantidade(6);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }
}
