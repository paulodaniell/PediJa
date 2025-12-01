package br.com.pedija.parceiro.view;

import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Usuario;
import java.util.Scanner;

public class MenuPrincipalParceiro {

    private final Scanner sc;
    private final Parceiro parceiro;

    // Construtor padrão quando já vem um Parceiro
    public MenuPrincipalParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
    }

    // Construtor usado quando o login retorna um Usuario
    public MenuPrincipalParceiro(Usuario usuariologado) {
        this.sc = new Scanner(System.in);
        this.parceiro = new Parceiro();
        this.parceiro.setId(usuariologado.getId());
        this.parceiro.setNome(usuariologado.getNome());
    }

    public void exibirMenuParceiro() {
        int opcao = -1;

        do {
            System.out.println("\n===== MENU PRINCIPAL PARCEIRO =====");
            System.out.println(" 1 - Gerenciar Pedidos");
            System.out.println(" 2 - Gerenciar Cardápio");
            System.out.println(" 3 - Criar Promoção");
            System.out.println(" 4 - Relatório e Estatísticas");
            System.out.println(" 5 - Configurações da Conta");
            System.out.println(" 0 - Sair");
            System.out.println("-----------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
                executarOpcao(opcao);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }

        } while (opcao != 0);
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> {
                System.out.println("➡ Gerenciar Pedidos");
                TelaPedidosParceiro telaPedidos = new TelaPedidosParceiro(parceiro);
                telaPedidos.exibirPedidos();
            }

            case 2-> {
                System.out.println("➡ Gerenciar Cardápio");
                TelaProdutosParceiro telaProdutos = new TelaProdutosParceiro(parceiro, sc);
                telaProdutos.exibirProdutos();
            }
            case 3-> {
                System.out.println("➡ Criar Promoção");
                TelaPromocaoParceiro telaPromocao = new TelaPromocaoParceiro(parceiro);
                telaPromocao.menuPromocaoParceiro();
            }
            case 4 -> {
                System.out.println("➡ Relatório e Estatísticas");
                TelaRelatorioParceiro telaRelatorio = new TelaRelatorioParceiro(parceiro);
                telaRelatorio.menuRelatorio();
            }
            case 5 -> {
                System.out.println("➡ Configurações da Conta");
                TelaPerfilParceiro telaPerfil = new TelaPerfilParceiro(parceiro);
                telaPerfil.exibirPerfilParceiro();
            }
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }
}