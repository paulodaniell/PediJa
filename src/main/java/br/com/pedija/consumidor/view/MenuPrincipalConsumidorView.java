package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.consumidor.view.perfil.TelaPerfil;
import br.com.pedija.superadm.model.Usuario;
import br.com.pedija.superadm.dao.PromocaoDAO;
import java.util.Scanner;

public class MenuPrincipalConsumidorView {

    private final Scanner sc;
    private final UsuarioController usuarioController;
    private final CarrinhoController carrinho;
    private final PedidoController pedidoController;
    private final BuscaProdutoConsumidor buscaProdutoConsumidor;
    private final TelaPromocoes telaPromocoes;
    PromocaoDAO promocaoDAO = new PromocaoDAO();

    private Usuario usuarioLogado;

    public MenuPrincipalConsumidorView(Usuario usuarioLogado) {
        this.sc = new Scanner(System.in);
        this.usuarioController = new UsuarioController();
        this.carrinho = new CarrinhoController();
        this.pedidoController = new PedidoController();
        this.usuarioLogado = usuarioLogado;
        this.buscaProdutoConsumidor = new BuscaProdutoConsumidor(carrinho);
        this.telaPromocoes = new TelaPromocoes(carrinho, sc);
    }

    public MenuPrincipalConsumidorView(Usuario usuarioLogado, Scanner sc) {
        this.sc = sc;
        this.usuarioController = new UsuarioController();
        this.carrinho = new CarrinhoController();
        this.pedidoController = new PedidoController();
        this.usuarioLogado = usuarioLogado;
        this.buscaProdutoConsumidor = new BuscaProdutoConsumidor(carrinho);
        this.telaPromocoes = new TelaPromocoes(carrinho, sc);
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void exibirMenuCliente() {
        int opcao = -1;

        do {
            System.out.println("\n1 - Buscar Produtos");
            System.out.println("2 - Ver Carrinho");
            System.out.println("3 - Promoções");
            System.out.println("4 - Ver Pedidos");
            System.out.println("5 - Ver Perfil");
            System.out.println("0 - Sair");

            System.out.print("\nEscolha uma opção: ");

            try {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                opcao = Integer.parseInt(line);
                resultadoOpcao(opcao);
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }

        } while (opcao != 0);
    }

    private void resultadoOpcao(int opcao) {
        switch (opcao) {

            case 1 -> {
                System.out.println("Buscar Produtos");
                buscaProdutoConsumidor.exibirbuscarProdutos();
            }

            case 2 -> {
                System.out.println("Ver Carrinho");
                if (this.usuarioLogado == null) {
                    System.out.println("Erro: usuário não logado!");
                    return;
                }
                TelaVerCarrinho telaCarrinho = new TelaVerCarrinho(carrinho, this.usuarioLogado, usuarioController);
                telaCarrinho.verCarrinho();
            }

            case 3 -> {
                telaPromocoes.verPromocoes();
            }

            case 4 -> {
                TelaPedidos telaPedidos = new TelaPedidos(this.pedidoController, this.usuarioLogado);
                telaPedidos.verPedidos();
            }

            case 5 -> {
                System.out.println("Ver Perfil");
                TelaPerfil telaPerfil = new TelaPerfil(this.usuarioLogado, this.usuarioController, this.pedidoController);
                telaPerfil.verPerfil();

            }
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida!");
        }
    }
}