package br.com.pedija.parceiro.view;
import br.com.pedija.entregador.controller.EntregadorController;
import br.com.pedija.parceiro.controller.PedidoController;
import br.com.pedija.superadm.model.Entregador;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Pedido;
import java.util.List;
import java.util.Scanner;

public class TelaEntregasParceiro {


    private Scanner sc;
    private Parceiro parceiro;
    private PedidoController pedidoController;
    private EntregadorController entregadorController;

    public TelaEntregasParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.pedidoController = new PedidoController();
        this.entregadorController = new EntregadorController();
    }

    public void menuEntregasParceiro() {
        int opcao = -1;

        do {
            System.out.println("\n GERENCIAR ENTREGAS - " + parceiro.getNome());
            System.out.println("------------------------------");
            System.out.println("1 - Pedidos Aguardando Entregador");
            System.out.println("2 - Pedidos em Rota");
            System.out.println("3 - Ver Entregadores Disponíveis");
            System.out.println("0 - Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOp(opcao);
            } catch (Exception erro) {
                System.out.println(" Erro: digite apenas números!");
                sc.nextLine();
            }

        } while (opcao != 0);
    }

    private void resultadoOp(int opcao) {
        switch (opcao) {
            case 1:
                exibirAguardandoEntregador();
                break;
            case 2:
                exibirEmRota();
                break;
            case 3:
                exibirEntregadoresDisponiveis();
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println(" Opção inválida!");
                break;
        }
    }

    private void exibirAguardandoEntregador() {
        System.out.println("\n PEDIDOS AGUARDANDO ENTREGADOR");
        System.out.println("------------------------------");

        List<Pedido> pedidos = pedidoController.listarAguardandoEntregador();

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido aguardando entregador");

            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("\n PEDIDO #" + p.getId());
            System.out.println("Cliente: " + p.getNomeCliente());
            System.out.println("Endereço: " + p.getEndereco());
            System.out.println("Valor: R$ " + String.format("%.2f", p.getValorTotal()));
            System.out.println("─────────────────────────────");
        }

        System.out.println("\n[1] Atribuir Entregador");
        System.out.println("[0] Voltar");
        System.out.print("Escolha: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            atribuirEntregador();
        }
    }

    private void atribuirEntregador() {

        List<Entregador> disponiveis = entregadorController.listarDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("\n Nenhum entregador disponível no momento!");

            return;
        }

        System.out.println("\nENTREGADORES DISPONÍVEIS:");
        for (Entregador e : disponiveis) {
            System.out.println("ID: " + e.getId() + " - " + e.getNome() +
                    " (" + e.getVeiculo() + ")");
        }

        try {
            System.out.print("\nID do Pedido: ");
            int idPedido = sc.nextInt();
            sc.nextLine();

            System.out.print("ID do Entregador: ");
            int idEntregador = sc.nextInt();
            sc.nextLine();


            if (pedidoController.atribuirEntregador(idPedido, idEntregador)) {

                entregadorController.atribuirPedido(idEntregador);

                System.out.println("\n Entregador atribuído com sucesso!");
                System.out.println("Pedido #" + idPedido + " agora está EM ROTA!");
            } else {
                System.out.println("\n Erro ao atribuir entregador!");
            }

        } catch (Exception e) {
            System.out.println("\n Erro: " + e.getMessage());
            sc.nextLine();
        }


    }

    private void exibirEmRota() {
        System.out.println("\n PEDIDOS EM ROTA");
        System.out.println("------------------------------");

        List<Pedido> pedidos = pedidoController.listarEmEntrega();

        if (pedidos.isEmpty()) {
            System.out.println("\n Nenhum pedido em entrega");

            return;
        }

        for (Pedido p : pedidos) {

            System.out.println("\n PEDIDO #" + p.getId());
            System.out.println("Cliente: " + p.getNomeCliente());
            System.out.println("Endereço: " + p.getEndereco());
            //System.out.println("Valor: R$ " + String.format("%.2f", p.getValorTotal()));

            System.out.println("─────────────────────────────");
        }
    }

    private void exibirEntregadoresDisponiveis() {
        System.out.println("\n ENTREGADORES DISPONÍVEIS");
        System.out.println("------------------------------");

        List<Entregador> disponiveis = entregadorController.listarDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("\n Nenhum entregador disponível no momento");
        } else {
            for (Entregador e : disponiveis) {
                System.out.println("\nID: " + e.getId());
                System.out.println("Nome: " + e.getNome());
                System.out.println("Veículo: " + e.getVeiculo());
                System.out.println("Telefone: " + e.getTelefone());
                System.out.println("----------------------------");
            }
        }


    }


}