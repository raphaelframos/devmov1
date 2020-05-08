package br.com.raphaelframos.carrinhodecompras.model;

import androidx.annotation.NonNull;

public class Produto {

    private String nome;
    private double quantidade;
    private String unidade;

    public Produto addNome(String nome){
        setNome(nome);
        return this;
    }

    public Produto addUnidade(String unidade){
        setUnidade(unidade);
        return this;
    }

    public Produto addQuantidade(String quantidade){
        setQuantidade(quantidade);
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @NonNull
    @Override
    public String toString() {
        return getQuantidadeDemonstracao() + getUnidade() + " " + getNome();
    }

    private String getQuantidadeDemonstracao() {
        if ((getQuantidade() == Math.floor(getQuantidade())) && !Double.isInfinite(getQuantidade())) {
            return Math.round(getQuantidade()) + "";
        }
        return getQuantidade() + "";
    }

    public void setQuantidade(String quantidadeDigitada) {
        if(quantidadeDigitada.isEmpty()){
            setQuantidade(0);
        }else{
            setQuantidade(Double.valueOf(quantidadeDigitada));
        }
    }
}
