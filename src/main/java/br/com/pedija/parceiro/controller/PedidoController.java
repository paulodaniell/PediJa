package br.com.pedija.parceiro.controller;

import java.util.ArrayList;
import java.util.List;

//import br.com.pedija.superadm.model.ItemPedido;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.parceiro.view.TelaPedidosParceiro;

public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido =  new Pedido();
    TelaPedidosParceiro view;

//Para o conumidor
    public void cadastrarPedido() {
        try {
            pedidoDAO.criar(pedido);
            view.exibirMensagemSucesso("Pedido Cadastrado com sucesso!");
        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
    }


    public List<Pedido> listarPedidos() {
        try {
            List<Pedido> pedidos = pedidoDAO.buscarTodos();

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        return null;
    }

    public Pedido buscarPorId(int id) {

        return pedidoDAO.buscarPorId(id);

    }

    public boolean atualizarStaus(int id, String novoStatus) {
        Pedido p = buscarPorId(id);

        if (p != null) {

           pedidoDAO.atualizar(p);

           view.exibirMensagemSucesso("Pedido Atualizado com sucesso!");
           return true;

        }
        view.exibirErro("Pedido Vazio!");
        return false;
    }


    public List<Pedido> listarPendentes() {
        try {
            return pedidoDAO.buscarPorStatus("PENDENTE");

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        return null;
    }

    public List<Pedido> listarEmPreparo() {
        try {
            return pedidoDAO.buscarPorStatus("EM PREPARO");

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        return null;
    }

    public List<Pedido> listarProntos() {
        try {
            return pedidoDAO.buscarPorStatus("PRONTO");

        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        return null;
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


    public boolean atribuirEntregador(int idPedido, int idEntregador) {
        Pedido p = buscarPorId(idPedido);

        if (p != null) {
            p.setIdEntregador(idEntregador);
            p.setStatus("EM_ENTREGA");
            return true;
        }

        return false;
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


}

