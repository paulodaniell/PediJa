package br.com.pedija.consumidor.view;

import java.util.Scanner;

public class TelaEscolherParceiro {
    private Scanner sc;

    public TelaEscolherParceiro() {
        this.sc = new Scanner(System.in);
    }

    public void exibirParceiros() {

        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println("Escolha um restaurante");
            System.out.println("Restaurantes Disponiveis");
            System.out.println(" 1 - Giraffas");
            System.out.println(" 2 - Burger King");
            System.out.println(" 3 - Mcdonald's");
            System.out.println(" 4 - Sushi Loko");
            System.out.println(" 0 - Sair");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoParceiros(opcao);
            } catch (Exception erro) {
                System.out.println("Opção inválida!");
                sc.nextLine();
            }


        } while (opcao != 0);

    }
    //Fluxo incompleto

    private void resultadoParceiros(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Giraffas");
                break;
            case 2:
                System.out.println("Burger King");
                break;

            case 3:
                System.out.println("Mcdonald's");
                break;
            case 4:
                System.out.println("Sushi Loko");
                break;
            case 0:
                System.out.println("Saindo..");
                break;
            default:System.out.println("Opção inválida! Escolha entre 0 e 4.");
                break;

        }
    }
}
