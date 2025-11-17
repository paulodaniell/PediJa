package br.com.pedija.consumidor.controller;

import br.com.pedija.superadm.model.Produto;
import java.util.ArrayList;

public class ProdutoController {


    // "Banco de dados" fake em memória
    private static final ArrayList<Produto> produtosteste = new ArrayList<>();
    private static int id = 1;

    static {
        produtosteste.add(new Produto(id++, "Pizza Calabresa", "Pizza grande com calabresa", 45.00, "Moema"));
        produtosteste.add(new Produto(id++, "Hamburguer", "Hambúrguer artesanal com cheddar", 25.00, "Giraffas"));
        produtosteste.add(new Produto(id++, "Coca Cola", "Refrigerante Coca-Cola 2 litros", 10.00, "Seu Zé"));
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

