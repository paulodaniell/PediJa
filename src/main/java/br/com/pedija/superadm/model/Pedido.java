package br.com.pedija.superadm.model;

import br.com.pedija.superadm.model.Parceiro;

import java.util.ArrayList;
import java.util.List;


public class Pedido {
    private int id;
    private String nomeCliente;
    private int idClienteitens;
    private double valorTotal;
    private int idEntregador;
    private String status;
    private String endereco;
    private String formaPagamento;
    private int idParceiro;
    private int idusuario;

    private List<Produto> itens = new ArrayList<>();

    //teste erro
    // adiciona item e atualiza o total
    public void addItem(Produto item) {
        itens.add(item);
        valorTotal += item.getPreco();
    }
//teste erro
    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
//teste erro

    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idusuario = idUsuario;
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

    public int getIdClienteitens() {
        return idClienteitens;
    }

    public void setIdClienteitens(int idClienteitens) {
        this.idClienteitens = idClienteitens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valoTotal) {
        this.valorTotal = valoTotal;
    }

    public int getIdEntregador() {
        return idEntregador;
    }

    public void setIdEntregador(int idEntregador) {
        this.idEntregador = idEntregador;
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

    public int getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(int idParceiro) {
        this.idParceiro = idParceiro;
    }
}
