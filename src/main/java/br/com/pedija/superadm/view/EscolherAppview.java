package br.com.pedija.superadm.view;

import br.com.pedija.consumidor.view.TelaLoginConsumidor;
import br.com.pedija.parceiro.view.TelaLoginParceiro;
import br.com.pedija.entregador.view.TelaLoginEntregador;

import java.util.Scanner;

public class EscolherAppview {

    TelaLoginConsumidor menuconsumidor = new TelaLoginConsumidor();
    TelaLoginParceiro telaLoginParceiro = new TelaLoginParceiro();
    TelaLoginEntregador telaLoginEntregador = new TelaLoginEntregador();

    Scanner sc = new Scanner(System.in);
    int opcao = -1;

    public EscolherAppview() {}
    public void EscolherAppview() {
        do {

            System.out.println(" 1 - Parceiro                 ");
            System.out.println(" 2 - Consumidor               ");
            System.out.println(" 3 - Entregador               ");
            System.out.println(" 0 - Sair                     ");

            System.out.print("\nEscolha uma opção: ");

            try {

                opcao = sc.nextInt();
                resultadoOpcao(opcao);

            } catch (Exception erro) {
                System.out.println("Opção inválida!");
                sc.nextLine();
            }

        } while (opcao != 0);
    }

    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Opção parceiro escolhida!");
                telaLoginParceiro.exibir();
                break;

            case 2:
                System.out.println("Opção consumidor escolhida!");
                menuconsumidor.exibirLogin();
                break;

            case 3:
                System.out.println("Opção entregador escolhida!");
                telaLoginEntregador.exibir();

            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}