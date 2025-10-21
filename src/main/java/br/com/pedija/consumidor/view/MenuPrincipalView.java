package br.com.pedija.consumidor.view;

import java.util.Scanner;

public class MenuPrincipalView {
    private Scanner sc;

    TelaEscolherParceiro restaurante=new TelaEscolherParceiro();

    public MenuPrincipalView() {
        this.sc = new Scanner(System.in);
    }

    public void exibirMenuCliente() {
        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Fazer Pedido                  ");
            System.out.println(" 2 - Ver Meus Pedidos              ");
            System.out.println(" 3 - Acompanhar Entrega            ");
            System.out.println(" 4 - Meu Perfil                    ");
            System.out.println(" 0 - Sair                          ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
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
                System.out.println("Fazer  Pedido");
                restaurante.exibirParceiros();
                break;
            case 2:
                System.out.println("Meus Pedidos");
                break;

            case 3:
                System.out.println("Acompanhar Entrega");
                break;
            case 4:
                System.out.println("Meu Perfil");
                break;
            case 0:
                System.out.println("Saindo..");
                break;
                default:System.out.println("Opção inválida! Escolha entre 0 e 4.");
                    break;


        }
    }
}
