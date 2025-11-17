package br.com.pedija.consumidor.view;


import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.consumidor.view.perfil.TelaPerfil;
import br.com.pedija.superadm.model.Usuario;


import java.util.Scanner;


public class MenuPrincipalConsumidorView {
    private final Scanner sc;


    private final UsuarioController usuarioController = new UsuarioController();
    private final CarrinhoController carrinho = new CarrinhoController();
    private final PedidoController pedidoController = new PedidoController();
    private Usuario usuarioLogado;
    BuscaProdutoConsumidor buscaProdutoConsumidor = new BuscaProdutoConsumidor(carrinho);
    TelaPromocoes telaPromocoes = new TelaPromocoes();


    public MenuPrincipalConsumidorView() {
        this.sc = new Scanner(System.in);
    }


    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }


    public void exibirMenuCliente() {
        int opcao = -1;


        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Buscar Produtos            ");
            System.out.println(" 2 - Ver Carrinho          ");
            System.out.println(" 3 - Promoções                ");
            System.out.println(" 4 - Ver Pedidos              ");
            System.out.println(" 5 - Ver Perfil               ");
            System.out.println(" 0 - Sair                     ");
            System.out.println("------------------------------");


            System.out.print("\nEscolha uma opção: ");


            try {
                String line = sc.nextLine().trim();
                opcao = line.isEmpty() ? -1 : Integer.parseInt(line);


                if (opcao != -1) {
                    resultadoOpcao(opcao);
                }
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }


        } while (opcao != 0);


    }


    private void resultadoOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("Buscar Produtos");
                buscaProdutoConsumidor.exibirbuscarProdutos();
                break;


            case 2:
                System.out.println("Ver Carrinho");


                if (this.usuarioLogado == null) {
                    System.out.println("Erro: Não foi possível acessar o carrinho. Usuário não logado ou erro de fluxo.");
                    break;
                }


                TelaVerCarrinho telaCarrinho = new TelaVerCarrinho(carrinho, this.usuarioLogado, usuarioController);
                telaCarrinho.vercarrinho();
                break;


            case 3:
                System.out.println("Promoções");
                telaPromocoes.verPromocoes();
                break;


            case 4:
                System.out.println("Ver Pedidos");
                TelaPedidos telaPedidos = new TelaPedidos(this.pedidoController, this.usuarioLogado);
                telaPedidos.verPedidos();
                break;


            case 5:

                // CORREÇÃO: Instancie a TelaPerfil aqui, passando as dependências
                TelaPerfil telaPerfil = new TelaPerfil(this.usuarioLogado, this.usuarioController);
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

