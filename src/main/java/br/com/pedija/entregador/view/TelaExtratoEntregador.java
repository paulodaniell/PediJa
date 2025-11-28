package br.com.pedija.entregador.view;

import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Entregador;
import java.util.List;

public class TelaExtratoEntregador {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private Entregador entregador;

    public TelaExtratoEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void extrato() {

        List<Pedido> entregues = pedidoDAO.buscarPorStatus("ENTREGUE");

        System.out.println("\n==============================");
        System.out.println("     MEU EXTRATO   ");
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
