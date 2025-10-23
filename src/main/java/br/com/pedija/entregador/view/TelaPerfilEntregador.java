package br.com.pedija.entregador.view;
import br.com.pedija.App;

import java.awt.*;
import java.util.Scanner;

public class TelaPerfilEntregador {
    public void perfilview() {

    System.out.println("Perfil Entregador");
    System.out.println("Nome do Usuário");

        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo(a) ao Ifood Entregador!");

        int opcao = 0;

        do {
            System.out.println("-----------------------------------");
            System.out.println(" 1 - Ver e editar seus Dados       ");
            System.out.println(" 2 - Sair da conta                 ");
            System.out.println(" 0 - Voltar                        ");
            System.out.println("-----------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                resultadoOpcao(opcao);

            } catch (Exception error) {
                System.out.println("Opção inválida!");
                sc.nextLine();
            }

        } while (opcao != 0);

    }

    private void resultadoOpcao(int opcao) {


        TelaDadosEntregador dados = new TelaDadosEntregador();


        switch (opcao) {
            case 1:
                System.out.println("Ver e Editar seus dados\n");
                dados.dadosview();
                break;

            case 2:
                System.out.println("Excluir Conta\n");
                System.out.println("Tem certeza que quer exluir sua conta? (1 - Não) (2 - Sim)\n");
                App.main();
                break;

            case 0:
                System.out.println("Voltando..");
                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}
