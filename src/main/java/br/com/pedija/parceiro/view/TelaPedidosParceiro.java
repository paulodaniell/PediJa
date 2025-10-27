package br.com.pedija.parceiro.view;

import java.util.Scanner;

public class TelaPedidosParceiro {

    private Scanner sc = new Scanner(System.in);

    MenuPrincipalParceiro menuParceiro = new MenuPrincipalParceiro();

    public static void exibirPedidos() {

        int opcao = -1;

        System.out.println("\n=== PEDIDOS RECEBIDOS ===");

        do {
            System.out.println("|Sushi Loko|");

            System.out.println("1 - Pedidos Prontos");
            System.out.println("2 - Pedidos Agendados");
            System.out.println("3 - Pedidos em Preparo");
            System.out.println("0 - Voltar");
            System.out.print("\nEscolha uma opção: ");

            try {
//                opcao = sc.nextInt();
//
//                resultadoOpc(opcao);
            } catch (Exception erro) {
                System.out.println("Erro  digite apenas numeros!");
//                opcao = sc.nextLine();
            }


        } while (opcao != 0);
    }
        private void resultadoOpc(int opcao) {
            switch (opcao) {
                case 1:
                    System.out.println("\nPedidos Prontos");
                    //pedidoControler
                    //falar com cliente/entregador
                    break;

                case 2:
                    System.out.println("\nPedidos Agendados");
                    //TelaEntregasParceiro.exibirEntrega();
                    break;

                case 3:
                    System.out.println("\nPedidos em preparo");

                    break;

                case 0:
                    System.out.println("Saindo..");
                    menuParceiro.exibirMenuParceiro();
                    break;

                default:System.out.println("Opção inválida!");
                    break;

            }
        }

}

