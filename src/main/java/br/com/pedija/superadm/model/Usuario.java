package br.com.pedija.superadm.model;

public class Usuario {
    private int id;
    private String email;
    private String telefone;
    private String cpf;
    private String nome;
    private String endereco;
    private String formadepagamento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFormadepagamento() {
        return formadepagamento;
    }

    public void setFormadepagamento(String formadepagamento) {
        this.formadepagamento = formadepagamento;
    }
}
