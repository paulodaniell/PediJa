package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaInicialEntregador {

    private Scanner sc = new Scanner(System.in);
    private Entregador entregador;

    public TelaInicialEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

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

    private void resultadoOpcao(int opcao) {


        TelaAjudaEntregador telaAjuda = new TelaAjudaEntregador();
        TelaExtratoEntregador telaExtrato = new TelaExtratoEntregador(entregador);
        TelaMaisEntregador telaMais = new TelaMaisEntregador(entregador);
        TelaNovaEntrega entrega =  new TelaNovaEntrega(entregador);
        switch (opcao) {

            case 1:
                System.out.println("Ficar disponível selecionado !\n");

                System.out.print("Quer ficar disponível? (1 - Sim) (0 - Não): ");
                int disponibilidade = sc.nextInt();

                if (disponibilidade == 1) {
                    entregador.setDisponivel(true);
                    entrega.novaentrega();
                }

                if (disponibilidade == 0) {
                    entregador.setDisponivel(false);
                }

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

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}
