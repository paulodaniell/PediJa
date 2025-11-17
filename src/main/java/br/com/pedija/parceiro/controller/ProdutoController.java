package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.ProdutoDAO;
import br.com.pedija.superadm.model.Produto;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public List<Produto> listarPorParceiro(int idParceiro) {
        return produtoDAO.buscarPorParceiro(idParceiro);
    }


    public boolean adicionar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return false;
        }
        if (produto.getPreco() <= 0) {
            System.out.println("Preço deve ser maior que zero!");
            return false;
        }

        if (produto.getIdParceiro() <= 0) {
            System.out.println("ID do Parceiro é obrigatório!");
            return false;
        }

        produtoDAO.criar(produto);
        System.out.println("Produto salvo no banco!");
        return true;
    }


    public Produto buscarPorId(int id) {
        return produtoDAO.buscarPorId(id);
    }


    public boolean remover(int id) {
        try {
            produtoDAO.deletar(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarDisponibilidade(int id, boolean disponivel) {
        // (Lembre-se de adicionar a coluna 'disponivel' no seu DatabaseConnection)
        return produtoDAO.alterarDisponibilidade(id, disponivel);
    }

    public List<Produto> listarMaisVendidos(int idParceiro) {
        return produtoDAO.listarMaisVendidos(idParceiro);
    }
    public int contarPorParceiro(int idParceiro) {
        return produtoDAO.contarPorParceiro(idParceiro);
    }
}