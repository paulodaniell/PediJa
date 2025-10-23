package br.com.pedija.entregador.model;

public class Banco {

    private String nomebanco;
    private int agencia;

    public Banco(String nome, int agencia) {
        this.nomebanco = nomebanco;
        this.agencia = agencia;
    }

    public Banco() {

    }

    public String getNomeBanco() {
        return nomebanco;
    }

    public void setNomeBanco(String id) {
        this.nomebanco = nomebanco;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int id) {
        this.agencia = id;
    }
}
