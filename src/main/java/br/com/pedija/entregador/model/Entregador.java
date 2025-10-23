package br.com.pedija.entregador.model;

public class Entregador {

    private int id;
    private int cpf;
    private String nome;
    private String email;
    private int telefone;
    private int contato_de_emergencia;
    private String nome_emergencia;
    private String formaEntrega;
    private String formaPagamento;

    public Entregador () {}

    public Entregador (int id, int cpf, String nome, String email, int telefone, int contato_emergencia,  String nome_emergencia) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.contato_de_emergencia = contato_emergencia;
        this.nome_emergencia = nome_emergencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCpf() { return cpf; }
    public void setCpf(int cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getTelefone() { return telefone; }
    public void setTelefone(int telefone) { this.telefone = telefone; }

    public int getContatoDeEmergencia() { return contato_de_emergencia; }
    public void setContatoDeEmergencia(int contatoDeEmergencia) { this.contato_de_emergencia = contatoDeEmergencia; }

    public String getNomeEmergencia() { return nome_emergencia; }
    public void setNomeEmergencia(String nomeEmergencia) { this.nome_emergencia = nomeEmergencia; }


    public String getFormaEntrega() { return formaEntrega; }
    public void setFormaEntrega(String formaEntrega) { this.formaEntrega = formaEntrega; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento;}

    }

