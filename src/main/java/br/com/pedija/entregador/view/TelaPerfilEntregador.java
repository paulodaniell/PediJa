package br.com.pedija.entregador.view;

import br.com.pedija.entregador.controller.EntregadorController;
import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaPerfilEntregador {

    private final Scanner sc;
    private final Entregador entregadorLogado;
    private final EntregadorController controller;

    public TelaPerfilEntregador(Entregador entregadorLogado, EntregadorController controller) {
        this.sc = new Scanner(System.in);
        this.entregadorLogado = entregadorLogado;
        this.controller = controller;
    }

    public void verPerfil() {

        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Dados da Conta           ");
            System.out.println(" 0 - Voltar                   ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                String line = sc.nextLine().trim();
                opcao = line.isEmpty() ? -1 : Integer.parseInt(line);
                if (opcao != -1) {
                    resultadoOpcao(opcao);
                }
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }

        } while (opcao != 0);
    }

    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Dados da Conta");
                DadosContaEntregador dadosContaView =
                        new DadosContaEntregador(this.entregadorLogado, this.sc, this.controller);
                dadosContaView.exibirDados();
                break;

            case 0:
                System.out.println("Voltando ao menu anterior...");
                return;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}