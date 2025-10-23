package br.com.pedija.parceiro.view;

import java.util.Scanner;

public class MenuPrincipalParceiro {
    private Scanner sc;

    public MenuPrincipalParceiro() {
        this.sc = new Scanner(System.in);
    }

    public void exibirMenuParceiro() {
        int opcao = -1;


        do {
            System.out.println("||Menu Sushi Loko||");
            System.out.println("------------------------------");
            System.out.println(" 1 - Gerenciar Pedidos      ");
            System.out.println(" 2 - Acompanhar Entrega      ");
            System.out.println(" 3 - Gerenciar Cardapio          ");
            System.out.println(" 4 - Criar promoção              ");
            System.out.println(" 5 - Configurações da conta            ");
            System.out.println(" 0 - Sair                     ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();

               resultadoOp(opcao);
            } catch (Exception erro) {
                System.out.println("Erro  digite apenas numeros!");
                sc.nextLine();
            }


        } while (opcao != 0);

    }
    private void resultadoOp(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Gerenciar  Pedido");
                TelaPedidosParceiro.exibirPedidos();
                break;

            case 2:
                System.out.println("Acompanhar Entrega");
                //TelaEntregasParceiro.exibirEntrega();
                break;

            case 3:
                System.out.println("Gerenciar  Cardapio");
                // CadastroPratoView().menuCardapio();
                break;

            case 4:
                System.out.println("Criar promoção");
                //PromocaoView().criarPromocao();
                break;

            case 5:
                System.out.println("Configurações da conta");
                //ConfiguracoesView().exibirConfiguracoes();
                break;

            case 0:
                System.out.println("Saindo..");
                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}

