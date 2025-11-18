package br.com.pedija.consumidor.controller;


import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.dao.PedidoDAO;


import java.util.ArrayList;
import java.util.List;


public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();

    // revisão do pedido
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

        double total = itens.stream().mapToDouble(Produto::getPreco).sum();
        p.setValorTotal(total);

        p.setNomeCliente(nome);
        p.setEndereco(endereco);
        p.setFormaPagamento(formaPagamento);
        p.setIdUsuario(usuarioId);

        // Pega o idParceiro do primeiro produto (todos os produtos do carrinho são do mesmo parceiro)
        if (!produtos.isEmpty()) {
            p.setIdParceiro(produtos.get(0).getIdParceiro());
        }

        p.setStatus("Em Andamento");

        return p;
    }


    public void criarPedido(Pedido revisaopedido) {

        pedidoDAO.criar(revisaopedido);

    }

    public Pedido buscarPorId(int id) {

        return pedidoDAO.buscarPorId(id);

    }

    public boolean atualizarStaus(int id, String novoStatus) {
        Pedido p = buscarPorId(id);

        if (p != null) {

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

        return pedidoDAO.buscarPorStatus("Em ENTREGA");

    }


    public List<Pedido> listarProntos() {

        return pedidoDAO.buscarPorStatus("PRONTO");

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


