package br.com.pedija.consumidor.controller;

import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.dao.PedidoDAO;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();

    public Pedido revisaopedido(List<Produto> produtos, int usuarioId, String nome, String endereco, String formaPagamento) {

        Pedido p = new Pedido();

        List<Produto> itens = new ArrayList<>();

        for (Produto prod : produtos) {
            Produto item = new Produto();
            item.setId(prod.getId());
            item.setNome(prod.getNome());
            item.setPreco(prod.getPreco());
            item.setQuantidade(1);
            itens.add(item);
        }

        p.setItens(itens);

        double total = itens.stream().mapToDouble(produto -> produto.getPreco() * produto.getQuantidade()).sum();
        p.setValorTotal(total);

        p.setNomeCliente(nome);
        p.setEndereco(endereco);
        p.setFormaPagamento(formaPagamento);
        p.setIdUsuario(usuarioId);

        if (!produtos.isEmpty()) {
            p.setIdParceiro(produtos.get(0).getIdParceiro());
        }

        p.setStatus("PENDENTE");

        return p;
    }

    public void criarPedido(Pedido pedido) {
        pedidoDAO.criar(pedido);
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

    public List<Pedido> listarEmEntrega() {
        return pedidoDAO.buscarPorStatus("EM ENTREGA");
    }

    public List<Pedido> listarProntos() {
        return pedidoDAO.buscarPorStatus("ENTREGUE");
    }

    public int contarPedidosPendentes() {
        return listarPendentes().size();
    }

    public int contarPedidosEmPreparo() {
        return listarEmPreparo().size();
    }

    public int contarPedidosProntos() {
        return listarProntos().size();
    }

    public double calcularFaturamentoTotal(int idParceiro) {
        double soma = 0.0;
        List<Pedido> prontos = listarProntos();
        for (Pedido p : prontos) {
            if (p.getIdParceiro() == idParceiro) {
                soma += p.getValorTotal();
            }
        }
        return soma;
    }
}
