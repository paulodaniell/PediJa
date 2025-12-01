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
            System.out.println("\n RELATÓRIO E ESTATÍSTICAS  " + parceiro.getNome() + "!");
            System.out.println("------------------------------");
            System.out.println(" 1 - Relatório do Dia");
            System.out.println(" 2 - Relatório do Mês");
            System.out.println(" 3 - Produtos Mais Vendidos");
            System.out.println(" 4 - Resumo Geral");
            System.out.println(" 0 - Voltar");
            System.out.println("------------------------------");
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
                relatorioDia();
                break;

            case 2:
                relatorioMes();
                break;

            case 3:
                produtosMaisVendidos();
                break;

            case 4:
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


    private void relatorioDia() {
        System.out.println("\n RELATÓRIO - VENDAS DO DIA\n");

        int totalPedidos = pedidoController.contarPedidosEntregues();
        double faturamento = pedidoController.calcularFaturamentoTotal(parceiro.getId());


        System.out.println("Total de Pedidos Entregues: " + totalPedidos);
        System.out.printf("Faturamento Total:          R$ %.2f%n", faturamento);
        System.out.println("Horário de Pico:            12h - 18h");
    }

    private void relatorioMes() {
        System.out.println("\n RELATÓRIO - VENDAS DO MÊS\n");

        int totalPedidos = pedidoController.contarPedidosEntregues();
        double faturamento = pedidoController.calcularFaturamentoTotal(parceiro.getId());

        System.out.println("Total de Pedidos Entregues: " + totalPedidos);
        System.out.printf("Faturamento Total:          R$ %.2f%n", faturamento);

    }


    private void produtosMaisVendidos() {
        System.out.println("\n PRODUTOS MAIS VENDIDOS\n");
        List<Produto> maisVendidos = produtoController.listarMaisVendidos(parceiro.getId());

        if (maisVendidos.isEmpty()) {
            System.out.println("Nenhum produto foi vendido ainda.");
            return;
        }
        for (int i = 0; i < maisVendidos.size(); i++) {
            Produto p = maisVendidos.get(i);
            System.out.printf("%dº - %s (ID: %d)\n", (i + 1), p.getNome(), p.getId());
        }
    }


    private void resumoGeral() {
        System.out.println("\n RESUMO GERAL\n");

        System.out.println("Total de Produtos:   " + produtoController.contarPorParceiro(parceiro.getId()));
        System.out.println("Pedidos Pendentes:   " + pedidoController.contarPedidosPendentes());
        System.out.println("Pedidos Entregues:   " + pedidoController.contarPedidosEntregues());

        double faturamento = pedidoController.calcularFaturamentoTotal(parceiro.getId());
        System.out.printf("Faturamento Total:   R$ %.2f%n", faturamento);

    }
}