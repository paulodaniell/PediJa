package br.com.pedija.parceiro.model;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido {
    private int idProduto;
    private String nomeProduto;
    private int quantidade;
    private double precoUnidade;
    private List<ItemPedido> itens = new ArrayList<>();

    public ItemPedido(int idProduto, String nomeProduto, int quantidade, double precoUnidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnidade = precoUnidade;
    }

    public double getSubTotal() {
        return precoUnidade * quantidade;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(double precoUnidade) {
        this.precoUnidade = precoUnidade;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
}
