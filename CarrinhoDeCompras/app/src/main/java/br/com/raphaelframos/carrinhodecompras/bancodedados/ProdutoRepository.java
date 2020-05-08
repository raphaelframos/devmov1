package br.com.raphaelframos.carrinhodecompras.bancodedados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.raphaelframos.carrinhodecompras.model.Produto;

public class ProdutoRepository {

    public static ArrayList<Produto> getProdutos() {
        ArrayList<Produto> produtos = Singleton.getInstance().getProdutos();
        return produtos;
    }

    public static void add(Produto produto) {
        Singleton.getInstance().add(produto);
    }
}
