package br.com.pedija.parceiro.controller;
import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;

import java.util.List;

public class PedidoController {

    private PedidoDAO pedidoDAO = new PedidoDAO();


    public boolean criarPedido(Pedido pedido) {

        try {
            int idGerado = pedidoDAO.adicionar(pedido);

            if (idGerado > 0) {
                System.out.println("Pedido criado com sucesso! ID: " + idGerado);
                return true;
            } else {
                throw new Exception("ID do pedido não foi gerado.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> listarPorParceiroEStatus(int idParceiro, String status) {
        return pedidoDAO.listarPorParceiroEStatus(idParceiro, status);
    }

    public Pedido buscarPorId(int id) {

        return pedidoDAO.buscarPorId(id);
    }

    public int contarPorStatus(int idParceiro, String status) {
        return pedidoDAO.contarPorStatus(idParceiro, status);
    }

    public boolean atualizarStatus(int idPedido, String novoStatus) {
        try {
            pedidoDAO.atualizarStatus(idPedido, novoStatus);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    public boolean atribuirEntregador(int idPedido, int idEntregador) {

        Pedido p = pedidoDAO.buscarPorId(idPedido);
        if (p == null) {
            System.out.println("Pedido não encontrado!");
            return false;
        }

        try {
            pedidoDAO.atribuirEntregador(idPedido, idEntregador);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atribuir entregador: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> listarAguardandoEntregador(int idParceiro) {
        return pedidoDAO.listarAguardandoEntregador(idParceiro);
    }

    public double calcularFaturamentoTotal(int idParceiro) {
        return pedidoDAO.calcularFaturamentoTotal(idParceiro);
    }

    public int contarPedidosEntregues(int idParceiro) {
        return pedidoDAO.contarPedidosEntregues(idParceiro);
    }
}