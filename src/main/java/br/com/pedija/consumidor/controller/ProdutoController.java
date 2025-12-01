package br.com.pedija.consumidor.controller;


import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.dao.ProdutoDAO;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public Produto buscarPorNome(String nome) {
        return produtoDAO.buscarPorNomeComParceiro(nome);
    }
}