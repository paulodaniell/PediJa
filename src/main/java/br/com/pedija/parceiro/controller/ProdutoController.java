package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.ProdutoDAO;
import br.com.pedija.superadm.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean adicionarProduto(Produto produto) {
        try {
            produtoDAO.criar(produto);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // <<< adiciona isto para ver o erro
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



    public List<Produto> listarProdutos() {
        try {
            return produtoDAO.buscarTodos();
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Produto> listarPorParceiros(int idParceiro) {
        return produtoDAO.buscarPorParceiro(idParceiro);
    }

    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }

    public boolean remover(int id) {
        try {
            produtoDAO.deletar(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean alterarDisponibilidade(int id, boolean disponivel) {
        return produtoDAO.alterarDisponibilidade(id, disponivel);
    }

    // TOP 5 MAIS VENDIDOS
    public List<Produto> listarMaisVendidos(int idParceiro) {
        return produtoDAO.listarMaisVendidos(idParceiro);
    }

    // CONTAR QUANTOS PRODUTOS TEM O PARCEIRO
    public int contarPorParceiro(int idParceiro) {
        return produtoDAO.contarPorParceiro(idParceiro);
    }
}
