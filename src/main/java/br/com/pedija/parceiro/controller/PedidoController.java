package br.com.pedija.parceiro.controller;


import java.util.ArrayList;
import java.util.List;


import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.dao.PedidoDAO;

public class PedidoController {


    PedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido =  new Pedido();


    public List<Pedido> listarPedidos() {

            List<Pedido> pedidos = pedidoDAO.listarTodos();

    return pedidos;
    }


    public Pedido buscarPorId(int id) {

        return pedidoDAO.buscarPorId(id);

    }


    public boolean atualizarStatus(int id, String novoStatus) {
        Pedido p = buscarPorId(id);
        if (p != null) {
            p.setStatus(novoStatus);
            pedidoDAO.atualizar(p);
            return true;
        }
        return false;
    }


    public List<Pedido> listarPendentes() {

            return pedidoDAO.buscarPorStatus("PENDENTE");


    }


    public List<Pedido> listarEmPreparo() {

            return pedidoDAO.buscarPorStatus("EM PREPARO");


    }


    public List<Pedido> listarAguardandoEntregador() {

        return pedidoDAO.buscarPorStatus("PRONTO");
    }


    public List<Pedido> listarEmEntrega() {

            return pedidoDAO.buscarPorStatus("EM ENTREGA");

    }

    public List<Pedido> listarPedidosEntregues() {

        return  pedidoDAO.buscarPorStatus("ENTREGUE");

    }


    public boolean atribuirEntregador(int idPedido, int idEntregador) {

        Pedido p = buscarPorId(idPedido);

        if (p != null) {
            p.setStatus("EM ENTREGA");
            pedidoDAO.atualizar(p);
            return true;
        }


        return false;
    }



    public int contarPedidosPendentes() {
        int contador = 0;


        List<Pedido> prontos = listarPendentes();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }


    public int contarPedidosEmPreparo() {
        int contador = 0;


        List<Pedido> prontos = listarEmPreparo();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }

    public int contarPedidosEsperandoEntregador() {
        int contador = 0;

        List<Pedido> prontos = listarAguardandoEntregador();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }

    public int contarPedidosEmEntrega() {
        int contador = 0;


        List<Pedido> prontos = listarEmEntrega();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }

    public int contarPedidosEntregues() {
        int contador = 0;


        List<Pedido> prontos = listarPedidosEntregues();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }

    public double calcularFaturamentoTotal(int idParceiro) {


        double soma = 0.0;


        List<Pedido> prontos = listarPedidosEntregues();


        for (Pedido p : prontos) {
            soma += p.getValorTotal();
        }
        return soma;
    }


}



