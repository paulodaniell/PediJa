package br.com.pedija.consumidor.view.perfil;

import java.util.Scanner;

public class TelaPerfil {

    CodigoEntregaConta codigoEntregaConta = new CodigoEntregaConta();

    Scanner sc = new Scanner(System.in);


    public void verPerfil() {

        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Notificações             ");
            System.out.println(" 2 - Dados da Conta           ");
            System.out.println(" 3 - Pagamentos               ");
            System.out.println(" 4 - Código de entrega        ");
            System.out.println(" 5 - Favoritos                ");
            System.out.println(" 6 - Endereços                ");
            System.out.println(" 7 - Ajuda                    ");
            System.out.println(" 8 - Configurações            ");
            System.out.println(" 0 - Sair                     ");
            System.out.println("------------------------------");

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
    //So opção 1 com metodo
    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Notificações");
                break;

            case 2:
                System.out.println("Dados da Conta");
                break;

            case 3:
                System.out.println("Pagamentos da Conta");
                break;

            case 4:
                System.out.println("Código de entrega \n");
                codigoEntregaConta.alterarCodigo();
                break;

            case 5:
                System.out.println("Favoritos");
                break;

            case 6:
                System.out.println("Endereços");
                break;

            case 7:
                System.out.println("Ajuda");
                break;

            case 8:
                System.out.println("Configurações");
                break;

            case 0:
                System.out.println("Saindo..");
                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}