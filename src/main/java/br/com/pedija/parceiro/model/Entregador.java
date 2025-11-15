package br.com.pedija.parceiro.model;

public class Entregador {
    private int id;
    private String nomeEntregador;;
    private String telefone;
    private String cpf;
    private String veiculo;
    private boolean disponivel;


    public Entregador() {
        this.disponivel = true;

    }

    public Entregador(int id, String nomeEntregador, String telefone, String veiculo) {
        this.id = id;
        this.nomeEntregador = nomeEntregador;
        this.telefone = telefone;
        this.veiculo = veiculo;
        this.disponivel = true;

    }


    public int getId() {
        return id;
    }

    public int getIdEntregador() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEntregador() {
        return nomeEntregador;
    }

    public void setNomeEntregador(String nomeEntregador) {
        this.nomeEntregador = nomeEntregador;
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


}