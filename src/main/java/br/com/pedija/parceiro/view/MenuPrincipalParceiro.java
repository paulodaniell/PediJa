package br.com.pedija.parceiro.view;

import br.com.pedija.superadm.model.Parceiro;

import java.util.Scanner;

public class MenuPrincipalParceiro {
    private Scanner sc;
    private Parceiro parceiro;

    public MenuPrincipalParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
    }

    public void exibirMenuParceiro() {
        int opcao = -1;


        do {
            System.out.println("|Menu Principal |");
            System.out.println("------------------------------");
            System.out.println(" 1 - Gerenciar Pedidos        ");
            System.out.println(" 2 - Acompanhar Entrega       ");
            System.out.println(" 3 - Gerenciar Cardapio       ");
            System.out.println(" 4 - Criar promoção           ");
            System.out.println(" 5 - Relatório e Estatisticas ");
            System.out.println(" 6 - Configurações da conta   ");
            System.out.println(" 0 - Sair                     ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOp(opcao);
            } catch (Exception erro) {
                System.out.println("Erro  digite apenas numeros!");
                sc.nextLine();
            }


        } while (opcao != 0);

    }
    private void resultadoOp(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Gerenciar  Pedido");
                TelaPedidosParceiro telaPedidos = new TelaPedidosParceiro(parceiro);
                telaPedidos.exibirPedidos();
                break;

            case 2:
                System.out.println("Acompanhar Entrega");
                TelaEntregasParceiro telaEntregas = new TelaEntregasParceiro(parceiro);
                telaEntregas.menuEntregasParceiro();
                break;

            case 3:
                System.out.println("Gerenciar  Cardapio");
                TelaProdutosParceiro telaProdutos = new TelaProdutosParceiro(parceiro);
                telaProdutos.exibirProdutos();
                break;


            case 4:
                System.out.println("Criar promoção");
                TelaPromocaoParceiro telaPromocao = new TelaPromocaoParceiro(parceiro);
                telaPromocao.menuPromocaoParceiro();

                break;

            case 5:
                TelaRelatorioParceiro telaRelatorio = new TelaRelatorioParceiro(parceiro);
                telaRelatorio.menuRelatorio();
                break;
            case 6:
                System.out.println("Configurações da conta");
                TelaPerfilParceiro telaPerfilParceiro = new TelaPerfilParceiro(parceiro);
                telaPerfilParceiro.exibirPerfilParceiro();
                break;
            case 0:
                System.out.println("Saindo..");

                break;

            default:System.out.println("Opção inválida!");
                break;


        }
    }
}
