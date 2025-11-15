package br.com.pedija.parceiro.model;

import br.com.pedija.consumidor.model.Endereco;

import java.util.ArrayList;
import java.util.List;

public class Parceiro {
    private int id;
    private String cpf;
    private String cnpj;
    private String nome;
    private String senha;
    private String telefone;
    private String email;
    private String endereco;
    private List<String> formasPagamento = new ArrayList<>();
    private String categoria;
    private double taxaEntrega;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private int numero;
    private String horarioSemana = "10:00 ás 18:00";
    private String horarioFimSemana = "10:00 ás 14:00";

    public Parceiro() {
        this.formasPagamento = new ArrayList<>();
        this.formasPagamento.add("Dinheiro");
        this.formasPagamento.add("PIX");
        this.formasPagamento.add("Cartão de Crédito");
        this.formasPagamento.add("Cartão de Débito");

    }
    public Parceiro(int id, String nome, String categoria, double taxaEntrega, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.taxaEntrega = taxaEntrega;
        this.aberto = aberto;

        this.formasPagamento = new ArrayList<>();
        this.formasPagamento.add("Dinheiro");
        this.formasPagamento.add("PIX");
        this.formasPagamento.add("Cartão de Crédito");
        this.formasPagamento.add("Cartão de Débito");

    }

    public void setFormasPagamento(List<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<String> getFormasPagamento() {
        return formasPagamento;
    }

    public String getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(String horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public String getHorarioFimSemana() {
        return horarioFimSemana;
    }

    public void setHorarioFimSemana(String horarioFimSemana) {
        this.horarioFimSemana = horarioFimSemana;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private boolean aberto;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

