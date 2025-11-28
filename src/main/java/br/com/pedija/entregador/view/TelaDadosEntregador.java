package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaDadosEntregador {

    private Entregador entregador;

    public TelaDadosEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void exibirDados() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nDados Pessoais:");
        System.out.println("1 - CPF: " + entregador.getCpf());
        System.out.println("2 - Contato de Emergência: " + entregador.getContatoDeEmergencia());
        System.out.println("3 - Nome do Contato de Emergência: " + entregador.getNomeEmergencia());
        System.out.println();

        System.out.println("Deseja mudar algum dado? (1 - Sim / 2 - Não)");
        int mudar = sc.nextInt();
        sc.nextLine();

        if (mudar == 1) {

            System.out.println("Qual dado deseja mudar?");
            int numdado = sc.nextInt();
            sc.nextLine();

            switch (numdado) {

                case 1:
                    System.out.print("Novo CPF: ");
                    entregador.setCpf(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Novo contato de emergência: ");
                    entregador.setContatoDeEmergencia(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Novo nome do contato: ");
                    entregador.setNomeEmergencia(sc.nextLine());
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        System.out.println("Voltando ao perfil...");
    }
}
