package br.com.pedija.entregador.view;
import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;

import java.util.List;

public class TelaExtratoEntregador {

    PedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido = new Pedido();

    public void extrato() {

        TelaInicialEntregador tela = new TelaInicialEntregador();

        pedidoDAO.buscarPorStatus("ENTREGUE");

        List<Pedido> entregues = pedidoDAO.buscarPorStatus("ENTREGUE");

        System.out.println("\n==============================");
        System.out.println("      EXTRATO DO ENTREGADOR");
        System.out.println("==============================");

        if (entregues.isEmpty()) {
            System.out.println("Nenhum pedido entregue ainda.");
            System.out.println("==============================\n");
            return;
        }

        double totalGeral = 0;

        for (Pedido p : entregues) {
            totalGeral += p.getValorTotal();
        }

        System.out.printf("\nTOTAL GANHO: R$ %.2f%n", totalGeral);
        System.out.println("==============================\n");
    }
    }

