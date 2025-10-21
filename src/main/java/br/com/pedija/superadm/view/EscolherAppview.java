package br.com.pedija.superadm.view;

import br.com.pedija.consumidor.view.MenuPrincipalView;
import java.util.Scanner;

public class EscolherAppview {

    MenuPrincipalView menuconsumidor = new MenuPrincipalView();
    Scanner sc = new Scanner(System.in);
    int opcao = -1;

    public EscolherAppview() {}
    public void EscolherAppview() {
        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Parceiro                 ");
            System.out.println(" 2 - Entregador               ");
            System.out.println(" 3 - Consumidor               ");
            System.out.println(" 0 - Sair                     ");
            System.out.println("------------------------------");

            System.out.print("\n Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                resultadoOpcao(opcao);
            } catch (Exception erro) {
                System.out.println("Opção inválida!");
                sc.nextLine(); // limpa o buffer
            }

        } while (opcao != 0);
    }

    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Opção parceiro escolhida!");
                break;
            case 2:
                System.out.println("Opção entregador escolhida!");
                break;
            case 3:
                System.out.println("Opção consumidor escolhida!");
                menuconsumidor.exibirMenuCliente();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}