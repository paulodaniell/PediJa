package br.com.pedija.parceiro.model;

public class Parceiro {
    private int id;
    private String nome;
    private String categoria;
    private double taxaEntrega;
    private boolean aberto;

    public Parceiro() {
    }

    public Parceiro(int id, String nome, String categoria, double taxaEntrega, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.taxaEntrega = taxaEntrega;
        this.aberto = aberto;
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

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }
}

