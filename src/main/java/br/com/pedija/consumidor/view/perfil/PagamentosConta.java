package br.com.pedija.consumidor.view.perfil;

import br.com.pedija.superadm.model.Usuario;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.consumidor.controller.PedidoController;
import java.util.List;
import java.util.Scanner;

public class PagamentosConta {

    private final Usuario usuarioLogado;
    private final Scanner sc;
    private final PedidoController pedidoController;

    public PagamentosConta(Usuario usuarioLogado, Scanner sc, PedidoController pedidoController) {
        this.usuarioLogado = usuarioLogado;
        this.sc = sc;
        this.pedidoController = pedidoController;
    }

    public void exibir() {

        System.out.println("\nCUSTO TOTAL DE PEDIDOS");

        List<Pedido> pedidos = pedidoController.listarProntos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para listar os custos.");
        } else {
            double totalGasto = 0;

            for (Pedido p : pedidos) {
                System.out.printf("Pedido #%d |  Custo: R$ %.2f%n",
                        p.getId(), p.getValorTotal());
                totalGasto += p.getValorTotal();
            }
            System.out.println("----------------------------------------");
            System.out.printf("TOTAL GASTO EM PEDIDOS: R$ %.2f%n", totalGasto);
        }

        while (true) {
            System.out.print("\nDigite 0 para voltar: ");
            try {
                String line = sc.nextLine().trim();
                if (line.equals("0")) {
                    return;
                }
                System.out.println("Opção inválida.");
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
            }
        }
    }
}