package br.com.pedija.entregador.view;
import br.com.pedija.superadm.model.Entregador;

import java.util.Scanner;

public class TelaInicialEntregador {

    Scanner sc = new Scanner(System.in);
    Entregador entregador = new Entregador();

    public void TelaInicioEntregador() {

        System.out.println("Bem vindo(a) ao Ifood Entregador!");

        int opcao = 0;

        do {
            System.out.println("-----------------------------------");
            System.out.println(" 1 - Ficar Disponível para entrega ");
            System.out.println(" 2 - Extrato                       ");
            System.out.println(" 3 - Perfil");
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

        TelaExtratoEntregador telaExtrato = new TelaExtratoEntregador();
        TelaNovaEntrega entrega =  new TelaNovaEntrega();
        TelaDadosEntregador perfil = new TelaDadosEntregador();


        switch (opcao) {
            case 1:
                System.out.println("Ficar disponível selecionado !\n");

                System.out.print("Quer ficar disponível? (1 - Sim) (0 - Não): ");
                int disponibilidade = sc.nextInt();

                if  (disponibilidade == 1) {

                    entregador.setDisponivel(true);
                    entrega.novaentrega();
                }

                if  (disponibilidade == 0) {
                    entregador.setDisponivel(false);
                    break;
                }

            case 2:
                System.out.println("Extrato selecionado");
                telaExtrato.extrato();
                break;

            case 3:
                System.out.println("Perfil selecionado!");
                perfil.dadosview();
                break;

            case 0:
                System.out.println("Voltando..");
                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}









