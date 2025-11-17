package br.com.pedija.consumidor.controller;


import br.com.pedija.superadm.model.ItemPedido;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.dao.PedidoDAO;


import java.util.ArrayList;
import java.util.List;


public class PedidoController {

    PedidoDAO pedidoDAO = new PedidoDAO();

    private static final List<Pedido> pedidos = new ArrayList<>();
    private static int nextId = 1;


    // revisão do pedido
    public Pedido revisaopedido(List<Produto> produtos, int usuarioId, String nome, String endereco, String formaPagamento) {
        Pedido p = new Pedido();

        List<ItemPedido> itens = new ArrayList<>();

        for (Produto prod : produtos) {

            ItemPedido item = new ItemPedido();
            item.setProdutoId(prod.getId());
            item.setNomeProduto(prod.getNome());
            item.setPrecoUnitario(prod.getPreco());
            item.setQuantidade(1); // ou a quantidade real do carrinho
            item.setSubTotal(prod.getPreco() * item.getQuantidade());
            itens.add(item);
        }

        p.setItens(itens);

        // Calcula total
        double total = itens.stream().mapToDouble(ItemPedido::getTotal).sum();
        p.setValorTotal(total);

        p.setNomeCliente(nome);
        p.setEndereco(endereco);
        p.setFormaPagamento(formaPagamento);
        p.setIdUsuario(usuarioId);

        return p;
    }


    // cria o pedido (retorna o pedido salvo com id)
    public void criarPedido(Pedido revisaopedido) {

        pedidoDAO.criar(revisaopedido);

    }


    public boolean concluirPedido(int idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                p.setStatus("Concluído");
                return true;
            }
        }
        return false;
    }


    // listar pedidos por usuário (para TelaPedidos)
    public List<Pedido> listarPorUsuario(int usuarioId) {



    }

    public List<Pedido> listarEmAndamentoPorUsuario(int usuarioId) {


        List<Pedido> todosPedidos = listarPorUsuario(usuarioId);


        List<Pedido> emAndamento = new ArrayList<>();


        for (Pedido p : todosPedidos) {
            if (p.getStatus() != null && p.getStatus().equalsIgnoreCase("Em Andamento")) {
                emAndamento.add(p);
            }
        }
        return emAndamento;
    }

}


