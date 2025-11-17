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
            p.setStatus(novoStatus); // faltava isso!
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


    public List<Pedido> listarAguardandoEntregador(int idParceiro) {
        List<Pedido> resultado = new ArrayList<>();


        for (Pedido p : resultado)


            if (p.getIdParceiro() == idParceiro &&
                    p.getStatus().equals("PRONTO") &&
                    p.getIdEntregador() == 0) {
                resultado.add(p);
            }


        return resultado;
    }


    public List<Pedido> listarEmEntrega() {

            return pedidoDAO.buscarPorStatus("Em ENTREGA");

    }


    public List<Pedido> listarProntos() {

            return pedidoDAO.buscarPorStatus("PRONTO");

    }






    public boolean atribuirEntregador(int idPedido, int idEntregador) {
        Pedido p = buscarPorId(idPedido);


        if (p != null) {
            p.setIdEntregador(idEntregador);
            p.setStatus("EM_ENTREGA");
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




    public int contarPedidosProntos() {
        int contador = 0;


        List<Pedido> prontos = listarProntos();


        for (Pedido p : prontos) {
            contador++;
        }
        return contador;
    }
    public double calcularFaturamentoTotal(int idParceiro) {


        double soma = 0.0;


        List<Pedido> prontos = listarProntos();


        for (Pedido p : prontos) {
            soma += p.getValorTotal();
        }
        return soma;
    }


}



