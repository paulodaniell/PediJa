package br.com.pedija.parceiro.model;


import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String nomeCliente;
    private int idCliente;
    private List<ItemPedido> itens;
    private double valoTotal;
    private int idEntregador;
    private String status;
    private String endereco;
    private String formaPagamento;
    private int idParceiro;


    public Pedido() {
        this.itens = new ArrayList<>();
        this.status = "PENDENTE";
        this.idEntregador = 0;
    }

    public double getValoTotal() {
        return valoTotal;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(int idEntregador) {
        this.idEntregador = idEntregador;
    }

    public void setValoTotal(double valoTotal) {
        this.valoTotal = valoTotal;
    }


    public int getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(int idParceiro) {
        this.idParceiro = idParceiro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valoTotal;
    }

    public void setValorTotal(double valoTotal) {
        this.valoTotal = valoTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);

    }
}