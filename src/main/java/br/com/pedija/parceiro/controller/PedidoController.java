package br.com.pedija.parceiro.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.pedija.parceiro.model.ItemPedido;
import br.com.pedija.parceiro.model.Pedido;

public class PedidoController {
    private static List<Pedido> pedidos = new ArrayList<>();
    private static int proximoId = 1;

    static {
        // Pedido 1 - Pendente
        Pedido p1 = new Pedido();
        p1.setId(1);
        p1.setIdParceiro(1);
        p1.setNomeCliente("João Silva");
        p1.setEndereco("Rua das Flores, 123");
        p1.setFormaPagamento("Cartão");
        p1.setStatus("PENDENTE");
        p1.adicionarItem(new ItemPedido(1, "Combo 1 - Sashimi", 2, 55.90));
        p1.setValorTotal(111.80);
        pedidos.add(p1);

        // Pedido 2 - Em preparo
        Pedido p2 = new Pedido();
        p2.setId(2);
        p2.setIdParceiro(1);
        p2.setNomeCliente("Maria Santos");
        p2.setEndereco("Av. Principal, 456");
        p2.setFormaPagamento("PIX");
        p2.setStatus("EM_PREPARO");
        p2.adicionarItem(new ItemPedido(2, "Combo Prime", 1, 249.40));
        p2.setValorTotal(249.40);
        pedidos.add(p2);

        // Pedido 3 - Pronto
        Pedido p3 = new Pedido();
        p3.setId(3);
        p3.setIdParceiro(1);
        p3.setNomeCliente("Carlos Souza");
        p3.setEndereco("Rua do Centro, 100");
        p3.setFormaPagamento("Dinheiro");
        p3.setStatus("PRONTO");
        p3.adicionarItem(new ItemPedido(3, "Temaki Salmão", 1, 25.00));
        p3.setValorTotal(25.00);
        pedidos.add(p3);

        proximoId = 4;
    }

    public static void adicionar(Pedido pedido) {
        pedido.setId(proximoId++);
        pedidos.add(pedido);
    }

    public List<Pedido> listarPorParceiroEStatus(int idParceiro, String status) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && p.getStatus().equals(status)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public Pedido buscarPorId(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizarStaus(int id, String novoStatus) {
        Pedido p = buscarPorId(id);
        if (p != null) {
            p.setStatus(novoStatus);
            return true;
        }
        return false;
    }

    public int contarPorStatus(int idParceiro, String status) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && p.getStatus().equals(status)) {
                contador++;
            }
        }
        return contador;
    }
    public double calcularFaturamentoTotal(int idParceiro) {
        double soma = 0.0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && "ENTREGUE".equalsIgnoreCase(p.getStatus())) {
                soma += p.getValorTotal();
            }
        }
        return soma;
    }


    public int contarPedidosEntregues(int idParceiro) {
        int contador = 0;
        for (Pedido p : pedidos) {
            if (p.getIdParceiro() == idParceiro && "ENTREGUE".equalsIgnoreCase(p.getStatus())) {
                contador++;
            }
        }
        return contador;
    }


    public double calcularTicketMedio(int idParceiro) {
        int total = contarPedidosEntregues(idParceiro);
        if (total == 0) return 0.0;
        return calcularFaturamentoTotal(idParceiro) / total;
    }
}
