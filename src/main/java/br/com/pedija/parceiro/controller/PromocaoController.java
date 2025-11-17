package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.PromocaoDAO;
import br.com.pedija.superadm.model.Promocao;
import java.util.List;

public class PromocaoController {

    private PromocaoDAO promocaoDAO = new PromocaoDAO();

    public boolean adicionar(Promocao promocao) {
        try {
            promocao.setAtiva(true);
            promocaoDAO.criar(promocao);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao adicionar promoção: " + e.getMessage());
            return false;
        }
    }


    public List<Promocao> listarTodasPorParceiro(int idParceiro) {
        return promocaoDAO.listarPorParceiro(idParceiro);
    }


    public List<Promocao> listarAtivasPorParceiro(int idParceiro) {
        return promocaoDAO.listarAtivasPorParceiro(idParceiro);
    }

    public boolean remover(int id) {
        try {
            promocaoDAO.deletar(id);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao remover promoção: " + e.getMessage());
            return false;
        }
    }

    public boolean desativar(int idPromocao) {
        try {
            promocaoDAO.atualizarStatus(idPromocao, false);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao desativar promoção: " + e.getMessage());
            return false;
        }
    }

    public boolean reativar(int idPromocao) {
        try {
            promocaoDAO.atualizarStatus(idPromocao, true);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao reativar promoção: " + e.getMessage());
            return false;
        }
    }
}