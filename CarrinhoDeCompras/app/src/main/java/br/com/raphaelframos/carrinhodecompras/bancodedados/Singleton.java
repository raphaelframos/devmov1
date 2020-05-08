package br.com.raphaelframos.carrinhodecompras.bancodedados;

import java.util.ArrayList;
import java.util.List;

import br.com.raphaelframos.carrinhodecompras.model.Produto;

public class Singleton {

    private static Singleton instance;

    private static ArrayList<Produto> produtos;

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }

        if(produtos == null){
            produtos = new ArrayList<>();
        }

        return instance;
    }

    public void add(Produto produto){
        produtos.add(produto);
    }

    public ArrayList<Produto> getProdutos(){
        return produtos;
    }


}
