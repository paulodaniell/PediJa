package br.com.pedija.consumidor.view;

import java.util.Scanner;

public class MenuPrincipalConsumidorView {
    private Scanner sc;

    TelaEscolherParceiro escolherParceiro = new TelaEscolherParceiro();
    BuscaProdutoConsumidor buscaProdutoConsumidor = new BuscaProdutoConsumidor();
    TelaPedidos telaPedidos = new TelaPedidos();
    TelaPerfil telaPerfil = new TelaPerfil();
    TelaPromocoes telaPromocoes = new TelaPromocoes();

    public MenuPrincipalConsumidorView() {
        this.sc = new Scanner(System.in);
    }

    public void exibirMenuCliente() {
        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Fazer Pedido             ");
            System.out.println(" 2 - Buscar Produtos          ");
            System.out.println(" 3 - Promoções                ");
            System.out.println(" 4 - Ver Pedidos              ");
            System.out.println(" 5 - Ver Perfil               ");
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
                System.out.println("Fazer  Pedido");
                escolherParceiro.exibirParceiros();
                break;

            case 2:
                System.out.println("Buscar Produtos");
                buscaProdutoConsumidor.buscarProdutos();
                break;

            case 3:
                System.out.println("Promoções");
                telaPromocoes.verPromocoes();
                break;

            case 4:
                System.out.println("Ver Pedidos");
                telaPedidos.verPedidos();
                break;

            case 5:
                System.out.println("Ver Perfil");
                telaPerfil.verPerfil();
                break;

            case 0:
                System.out.println("Saindo..");
                break;

                default:System.out.println("Opção inválida!");
                    break;


        }
    }
}
