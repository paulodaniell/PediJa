package br.com.pedija.consumidor.controller;


import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;


import java.util.ArrayList;
import java.util.List;


public class PedidoController {


    private static final List<Pedido> pedidos = new ArrayList<>();
    private static int nextId = 1;




    // revisão do pedido
    public Pedido revisaopedido(List<Produto> itens, int usuarioId, String nome, String endereco, String formaPagamento) {
        Pedido p = new Pedido();
        p.setId(0); // preview sem id
        p.setItens(new ArrayList<>(itens));
        double total = itens.stream().mapToDouble(Produto::getPreco).sum();
        p.setValorTotal(total);
        p.setNomeCliente(nome);
        p.setEndereco(endereco);
        p.setFormaPagamento(formaPagamento);
        p.setUsuarioId(usuarioId);
        return p;
    }




    // cria o pedido (retorna o pedido salvo com id)
    public Pedido criarPedido(List<Produto> itens, int usuarioId, String nome, String endereco, String formaPagamento) {
        Pedido p = new Pedido();
        p.setId(nextId++);
        p.setItens(new ArrayList<>(itens));
        double total = itens.stream().mapToDouble(Produto::getPreco).sum();
        p.setValorTotal(total);
        p.setNomeCliente(nome);
        p.setEndereco(endereco);
        p.setFormaPagamento(formaPagamento);
        p.setStatus("Em Andamento");
        p.setUsuarioId(usuarioId);
        pedidos.add(p);
        return p;
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
        List<Pedido> out = new ArrayList<>();
        for (Pedido p : pedidos) if (p.getUsuarioId() == usuarioId) out.add(p);
        return out;
    }


}

