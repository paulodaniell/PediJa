package br.com.pedija.entregador.view;

import br.com.pedija.entregador.controller.EntregadorController;
import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaPerfilEntregador {

    private Scanner sc = new Scanner(System.in);
    private Entregador entregador;
    private EntregadorController controller = new EntregadorController();

    public TelaPerfilEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void verPerfil() {

        int opcao;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Dados da Conta           ");
            System.out.println(" 2 - Dados Pessoais           ");
            System.out.println(" 0 - Voltar                   ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    new DadosContaEntregador(entregador, sc, controller).exibirDados();
                    break;

                case 2:
                    new TelaDadosEntregador(entregador).exibirDados();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    return;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
