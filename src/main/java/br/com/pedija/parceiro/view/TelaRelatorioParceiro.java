package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.PedidoController;
import br.com.pedija.parceiro.controller.ProdutoController;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Produto;

import java.util.List;
import java.util.Scanner;

public class TelaRelatorioParceiro {
    private Scanner sc;
    private Parceiro parceiro;
    private PedidoController pedidoController;
    private ProdutoController produtoController;

    public TelaRelatorioParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.pedidoController = new PedidoController();
        this.produtoController = new ProdutoController();
    }

    public void menuRelatorio() {
        int opcao = -1;

        do {
            System.out.println("\nRELATÓRIO E ESTATÍSTICAS  " + parceiro.getNome());

            System.out.println("\n1 - Relatório Vendas");
            System.out.println("2 - Resumo Geral");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOp(opcao);
            } catch (Exception erro) {
                System.out.println("Erro: digite apenas números!");
                sc.nextLine();
            }

        } while (opcao != 0);
    }

    private void resultadoOp(int opcao) {
        switch (opcao) {

            case 1:
                relatorioVendas();
                break;

            case 2:
                resumoGeral();
                break;

            case 0:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }


    private void relatorioVendas() {
        System.out.println("\nRELATÓRIO - VENDAS\n");

        int totalPedidos = pedidoController.contarPedidosEntregues();
        double faturamento = pedidoController.calcularFaturamentoTotal(parceiro.getId());

        System.out.println("Total de Pedidos Entregues: " + totalPedidos);
        System.out.printf("Faturamento Total:          R$ %.2f%n", faturamento);

    }


    private void resumoGeral() {
        System.out.println("\nRESUMO GERAL\n");

        System.out.println("Total de Produtos:   " + produtoController.contarPorParceiro(parceiro.getId()));
        System.out.println("Pedidos Pendentes:   " + pedidoController.contarPedidosPendentes());
        System.out.println("Pedidos Entregues:   " + pedidoController.contarPedidosEntregues());

        double faturamento = pedidoController.calcularFaturamentoTotal(parceiro.getId());
        System.out.printf("Faturamento Total:   R$ %.2f%n", faturamento);

    }
}