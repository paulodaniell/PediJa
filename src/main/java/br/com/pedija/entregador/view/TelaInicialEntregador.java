package br.com.pedija.entregador.view;

import java.util.Scanner;

public class TelaInicialEntregador {

    Scanner sc = new Scanner(System.in);

    public void TelaInicioEntregador() {

        System.out.println("Bem vindo(a) ao Ifood Entregador!");

        int opcao = 0;

        do {
            System.out.println("-----------------------------------");
            System.out.println(" 1 - Ficar Disponível para entrega ");
            System.out.println(" 2 - Extrato                       ");
            System.out.println(" 3 - Ajuda                         ");
            System.out.println(" 4 - Mais                          ");
            System.out.println(" 0 - Voltar                        ");
            System.out.println("-----------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                resultadoOpcao(opcao);

            } catch (Exception error) {
                System.out.println("Erro na leitura de dados!");
                sc.nextLine();
            }


        } while (opcao != 0);

    }
    //So opção 1 com metodo
    private void resultadoOpcao(int opcao) {

        TelaAjudaEntregador telaAjuda = new TelaAjudaEntregador();
        TelaExtratoEntregador telaExtrato = new TelaExtratoEntregador();
        TelaMaisEntregador telaMais = new TelaMaisEntregador();
        TelaNovaEntrega entrega =  new TelaNovaEntrega();


        switch (opcao) {
            case 1:
                System.out.println("Ficar disponível selecionado !\n");
                entrega.novaentrega();
                break;

            case 2:
                System.out.println("Extrato selecionado");
                telaExtrato.extrato();
                break;

            case 3:
                System.out.println("Ajuda selecionado!");
                telaAjuda.ajuda();

                break;

            case 4:
                System.out.println("Opção Mais selecionada!\n");
                telaMais.mais();
                break;

            case 0:
                System.out.println("Voltando..");
                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}










