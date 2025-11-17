package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.EntregadorDAO;
import br.com.pedija.superadm.model.Entregador;

import java.util.List;

public class EntregadorController {

    private EntregadorDAO entregadorDAO = new EntregadorDAO();

    public List<Entregador> listarTodos() {
        return entregadorDAO.buscarTodos();
    }

    public List<Entregador> listarDisponiveis() {
        return entregadorDAO.listarDisponiveis();
    }

    public Entregador buscarPorId(int id) {
        return entregadorDAO.buscarPorId(id);
    }


    public boolean atribuirPedido(int idEntregador) {
        Entregador e = entregadorDAO.buscarPorId(idEntregador);

        if (e != null && e.isDisponivel()) {

            e.setDisponivel(false);


            entregadorDAO.atualizar(e);

            return true;
        }
        return false;
    }
}