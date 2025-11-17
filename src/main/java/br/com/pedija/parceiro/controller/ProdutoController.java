package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.ProdutoDAO;
import br.com.pedija.superadm.model.Produto;
import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean adicionarProduto(Produto produto) {
        try {
            produtoDAO.criar(produto);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarProduto(Produto produto) {
        try {
            produtoDAO.atualizar(produto);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public List<Produto> listarPorParceiros(int idParceiro) {
        return produtoDAO.buscarPorParceiro(idParceiro);
    }

    public boolean remover(int id) {
        try {
            produtoDAO.deletar(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarDisponibilidade(int id, boolean disponivel) {
        return produtoDAO.alterarDisponibilidade(id, disponivel);
    }

    public List<Produto> listarMaisVendidos(int idParceiro) {
        return produtoDAO.listarMaisVendidos(idParceiro);
    }

    // CONTAR QUANTOS PRODUTOS TEM O PARCEIRO
    public int contarPorParceiro(int idParceiro) {
        return produtoDAO.contarPorParceiro(idParceiro);
    }
}
