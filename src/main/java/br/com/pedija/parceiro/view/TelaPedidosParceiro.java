package br.com.pedija.parceiro.view;

import java.util.Scanner;

public class TelaPedidosParceiro {

    private Scanner sc = new Scanner(System.in);

    MenuPrincipalParceiro menuParceiro = new MenuPrincipalParceiro();

    public void exibirPedidos() {

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
                opcao = sc.nextInt();

                resultadoOpc(opcao);
            } catch (Exception erro) {
                System.out.println("Erro  digite apenas numeros!");
                sc.nextLine();
            }


        } while (opcao != 0);
    }
        private void resultadoOpc(int opcao) {
            switch (opcao) {
                case 1:
                    System.out.println("Pedidos Prontos");
                    System.out.println("#0001 Daniel S");
                    System.out.println("#0002 Pedro P");
                    //falar com cliente/entregador
                    break;

                case 2:
                    System.out.println("Pedidos Agendados");
                    //TelaEntregasParceiro.exibirEntrega();
                    break;

                case 3:
                    System.out.println("Pedidos em preparo");
                    System.out.println("Pedido:0003");
                    System.out.println("Pedido:0004");
                    System.out.println("Pedido:0005");

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

