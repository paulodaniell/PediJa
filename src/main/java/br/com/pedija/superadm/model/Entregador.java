package br.com.pedija.superadm.model;

import java.util.List;

public class Entregador {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private int telefone;
    private String cpf;
    private String veiculo;
    private boolean disponivel;
    private List<String> formasPagamento;
    private int contatoDeEmergencia;
    private String nomeEmergencia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public List<String> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String s) {
    }

    public int getContatoDeEmergencia() {
        return contatoDeEmergencia;
    }

    public void setContatoDeEmergencia(int contatoDeEmergencia) {
        this.contatoDeEmergencia = contatoDeEmergencia;
    }

    public String getNomeEmergencia() {
        return nomeEmergencia;
    }

    public void setNomeEmergencia(String nomeEmergencia) {
        this.nomeEmergencia = nomeEmergencia;
    }
}
