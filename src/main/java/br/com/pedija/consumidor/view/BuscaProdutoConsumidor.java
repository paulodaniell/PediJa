package br.com.pedija.consumidor.view;

import java.util.Scanner;

public class BuscaProdutoConsumidor {
    private Scanner sc;


    public BuscaProdutoConsumidor() {this.sc = new Scanner(System.in);}

    public void buscarProdutos(){
        String resultado;

        System.out.println("----------------------------------------");
        System.out.println(" Escreva o produto que deseja encontrar ");
        System.out.println("----------------------------------------");

        System.out.print("\n:nome do produto: ");

        /*
        Aqui vai ter uma busca para procurar o
        produto digitado dentro das lojas/estabelecimentos
        no banco de dados/lista
         */

        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - loja 1                   ");
            System.out.println(" 2 - loja 2                   ");
            System.out.println(" 3 - loja 3                   ");
            System.out.println(" 4 - loja 4                   ");
            System.out.println(" 5 - loja 5                   ");
            System.out.println(" X - loja ......              ");
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

    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("loja 1");
                break;

            case 2:
                System.out.println("loja 2");
                break;

            case 3:
                System.out.println("loja 3");
                break;

            case 4:
                System.out.println("loja 4");

                break;

            case 5:
                System.out.println("loja 5");

                break;

            case 0:
                System.out.println("Saindo..");
                break;

            default:System.out.println("Opção inválida!");
                break;

        }
    }
}



