package br.com.pedija.consumidor.controller;

import br.com.pedija.consumidor.model.Produto;
import java.util.ArrayList;

public class ProdutoController {


    // "Banco de dados" fake em memória
    private static ArrayList<Produto> produtosteste = new ArrayList<>();
    private static int id = 1;

    static {
        produtosteste.add(new Produto(id++, "Pizza Calabresa", "Pizza grande com calabresa", 45.00));
        produtosteste.add(new Produto(id++, "Hambúrguer", "Hambúrguer artesanal com cheddar", 25.00));
        produtosteste.add(new Produto(id++, "Coca-Cola 2L", "Refrigerante Coca-Cola 2 litros", 10.00));
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtosteste) {
            if (p.getNome().equalsIgnoreCase(nome.trim())) {
                return p;
            }
        }
        return null;
    }

}
