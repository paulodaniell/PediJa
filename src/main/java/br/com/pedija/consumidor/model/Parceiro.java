package br.com.pedija.consumidor.model;

import java.util.ArrayList;
import java.util.List;

public class Parceiro {
    private int id;
    private String nome;
    private String categoria;
    private List<Produto> produtos;

    public Parceiro(int id, String nome, String categoria, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void mostrarInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Tipo de comida: " + categoria);
        System.out.println("Produtos disponíveis:");
        for (Produto p : produtos) {
            System.out.println(" - " + p.getNome() + " | R$ " + p.getPreco());
        }
    }
}
