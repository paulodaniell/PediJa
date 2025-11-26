package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.PedidoController;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Pedido;

import java.util.List;
import java.util.Scanner;

public class TelaPedidosParceiro {

    private Scanner sc;
    private Parceiro parceiro;
    private PedidoController pedidoController;

    public TelaPedidosParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.pedidoController = new PedidoController();
    }

    public void exibirPedidos() {
        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println("\nPedidos " + parceiro.getNome());
            System.out.println("------------------------------");

            System.out.println("1- Pedidos Pendentes: (" + pedidoController.contarPedidosPendentes() + ")");
            System.out.println("2- Pedidos em Preparo: (" + pedidoController.contarPedidosEmPreparo() + ")");
            System.out.println("3- Pedidos Esperando Entregador: (" + pedidoController.contarPedidosEsperandoEntregador() + ")");
            System.out.println("4- Pedidos em Entrega: (" + pedidoController.contarPedidosEmEntrega() + ")");
            System.out.println("5 - Pedidos Entregues: (" + pedidoController.contarPedidosEntregues());
            System.out.println("0- Voltar");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOpc(opcao);
            } catch (Exception erro) {
                System.out.println("Erro! Digite apenas número!");
                sc.nextLine();
            }

        } while (opcao != 0);
    }

    private void resultadoOpc(int opcao) {
        switch (opcao) {
            case 1: exibirPendentes(); break;
            case 2: exibirEmPreparo(); break;
            case 3: exibirEsperandoEntregador(); break;
            case 4: exibirEmEntrega(); break;
            case 5: exibirEntregues(); break;
            case 0: System.out.println("Voltando.."); break;
            default: System.out.println("Opção inválida!"); break;
        }
    }

    private void exibirPendentes() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS PENDENTES:");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarPendentes();

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido pendente");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.printf("%nPEDIDO #%d%n", p.getId());
            System.out.printf("Cliente: %s%n", p.getNomeCliente());
            System.out.printf("Valor: R$ %.2f%n", p.getValorTotal());
            System.out.printf("Pagamento: %s%n", p.getFormaPagamento());
            System.out.println("Itens:");

            System.out.println("\n[1] Aceitar Pedido");
            System.out.println("[2] Rejeitar Pedido");
            System.out.println("[3] Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("ID do Pedido: ");
                int id = sc.nextInt();
                sc.nextLine();
                aceitarPedido(id);
            } else if (opcao == 2) {
                System.out.print("ID do Pedido: ");
                int id = sc.nextInt();
                sc.nextLine();
                rejeitarPedido(id);
            } else {
                return;
            }
        }
    }

    public void exibirEmPreparo() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS EM PREPARO");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarEmPreparo();

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido em preparo");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.printf("%nPedido #%d%n", p.getId());
            System.out.printf("Cliente: %s%n", p.getNomeCliente());
            System.out.printf("Valor: R$ %.2f%n", p.getValorTotal());
        }

        System.out.println("\n[1] Marcar como Pronto");
        System.out.println("[0] Voltar");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            System.out.print("ID do Pedido: ");
            int id = sc.nextInt();
            sc.nextLine();
            marcarComoPronto(id);
        }
    }

    private void exibirEsperandoEntregador() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS ESPERANDO ENTREGADOR");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarAguardandoEntregador();

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido pronto no momento");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.printf("%nPEDIDO #%d%n", p.getId());
            System.out.printf("Cliente: %s%n", p.getNomeCliente());
            System.out.printf("Valor: R$ %.2f%n", p.getValorTotal());
            System.out.println("Aguardando entregador...");
        }
    }

    private void exibirEmEntrega() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS EM ENTREGA");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarEmEntrega();

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido em entrega");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.printf("%nPEDIDO #%d%n", p.getId());
            System.out.printf("Cliente: %s%n", p.getNomeCliente());
            System.out.printf("Valor: R$ %.2f%n", p.getValorTotal());
        }

        System.out.println("\n[0] Voltar");
        sc.nextLine();
    }

    private void exibirEntregues() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS ENTREGUES");
        System.out.println("---------------------");

        List<Pedido> entregues = pedidoController.listarPedidosEntregues();

        System.out.println("\nTotal de pedidos entregues: " + entregues.size());

        if (!entregues.isEmpty()) {
            int limite = Math.min(10, entregues.size());
            for (int i = 0; i < limite; i++) {
                Pedido p = entregues.get(i);
                System.out.printf("#%d - %s - R$ %.2f%n",
                        p.getId(),
                        p.getNomeCliente(),
                        p.getValorTotal());
            }
        }
    }

    private void aceitarPedido(int id) {
        System.out.print("\nTempo de preparo (minutos): ");
        int tempo = sc.nextInt();
        sc.nextLine();

        if (pedidoController.atualizarStatus(id, "EM PREPARO")) {
            System.out.println("\nPedido #" + id + " aceito!");
            System.out.println("Tempo de preparo: " + tempo + " minutos");
        } else {
            System.out.println("\nErro ao aceitar pedido!");
        }
    }

    private void rejeitarPedido(int id) {
        System.out.println("\nMotivo da rejeição:");
        System.out.println("[1] Fora da área de entrega");
        System.out.println("[2] Produto indisponível");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        String motivo;

        if (opcao == 1) {
            motivo = "Fora da área de entrega";
        } else if (opcao == 2) {
            motivo = "Produto indisponível";
        } else {
            System.out.println("Opção inválida! Cancelando operação.");
            return;
        }

        System.out.println("\nMotivo selecionado: " + motivo);
        if (pedidoController.atualizarStatus(id, "REJEITADO")) {
            System.out.println("\nPedido #" + id + " rejeitado!");
        }
    }

    private void marcarComoPronto(int id) {
        if (pedidoController.atualizarStatus(id, "EM ENTREGA")) {
            System.out.println("\nPedido #" + id + " está à caminho");
        } else {
            System.out.println("\nErro ao atualizar pedido!");
        }
    }

    public void exibirMensagemSucesso(String s) {
    }

    public void exibirErro(String message) {
    }
}
