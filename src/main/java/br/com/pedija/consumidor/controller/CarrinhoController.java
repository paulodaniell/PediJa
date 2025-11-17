package br.com.pedija.consumidor.controller;

import br.com.pedija.superadm.model.Produto;
import java.util.ArrayList;

public class CarrinhoController {

    private final ArrayList<Produto> itens = new ArrayList<>();

    public void adicionar(Produto p) {
        itens.add(p);
    }

    public ArrayList<Produto> listar() {
        return itens;
    }

    public boolean Vazio() {
        return itens.isEmpty();
    }

    public void removerProduto(int numero) {
        if (numero >= 0 && numero < itens.size()) {
            itens.remove(numero);
        }
    }

    public double precoTotal() {
        double total = 0;
        for (Produto p : itens) {
            total += p.getPreco();
        }
        return total;
    }

    public void limpar() {
        itens.clear();
    }

}