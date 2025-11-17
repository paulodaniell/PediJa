package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.controller.PagamentoController;
import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Usuario;

import java.util.Scanner;


public class TelaVerCarrinho {

    private final PagamentoController pedirpagamento = new PagamentoController();
    private final UsuarioController usuarioController;
    private PedidoController pedidoController = new PedidoController();
    private Usuario usuario;
    private final CarrinhoController carrinho;
    private final Scanner sc;


    public TelaVerCarrinho(CarrinhoController carrinho,  Usuario usuario, UsuarioController usuarioController) {

        this.carrinho = carrinho;
        this.usuario = usuario;
        this.usuarioController = usuarioController;
        this.sc = new Scanner(System.in);
    }


    public void vercarrinho(){

        if (carrinho.Vazio()) {
            System.out.println("\n====MEU CARRINHO====\n ");
            System.out.println("Seu carrinho está vazio.\n");
            return;
        }

        while (true)
        {
            System.out.println("\n====MEU CARRINHO====\n ");

            int i = 1;
            for (Produto p : carrinho.listar()) {
                System.out.printf("%d - %s (R$ %.2f)\n", i++, p.getNome(), p.getPreco());
            }
            System.out.printf("\nTotal: R$ %.2f\n\n", carrinho.precoTotal());

            int op;
            System.out.println("[1] Remover item");
            System.out.println("[2] Finalizar pedido");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");


            try {
                String line = sc.nextLine().trim();
                op = line.isEmpty() ? -1 : Integer.parseInt(line);
            } catch (Exception erro) {
                System.out.println("Opção inválida! Digite um número.\n");
                continue;
            }

            switch (op) {

                case 1:
                    System.out.print("Digite o número do item a remover: ");
                    int numero;
                    try
                    {
                        String line = sc.nextLine().trim();
                        numero = line.isEmpty() ? -1 : Integer.parseInt(line);
                    }
                    catch (Exception erro)
                    {
                        System.out.println("Número inválido!\n");
                        break;
                    }

                    int indice = numero - 1;

                    if (indice < 0 || indice >= carrinho.listar().size())
                    {
                        System.out.println("Número do item inválido!\n");
                    }
                    else
                    {
                        carrinho.removerProduto(indice);
                        System.out.println("Item removido com sucesso!\n");
                    }
                    break;

                case 2:
                    String formapagamento = pedirpagamento.pagamento(sc);
                    if (formapagamento == null) {
                        break;
                    }

                    usuario.setFormadepagamento(formapagamento);
                    usuarioController.atualizarUsuario(usuario);;

                    // cria preview do pedido
                    Pedido revisaopedido = pedidoController.revisaopedido(carrinho.listar(), usuario.getId(), usuario.getNome(), usuario.getEndereco(), formapagamento);

                    System.out.println("\n=== RESUMO DO PEDIDO ===");
                    i = 1;
                    //for (Produto it : revisaopedido.getItens())
                    {
                      //  System.out.printf("%d - %s (R$ %.2f)%n", i++, it.getNome(), it.getPreco());
                    }
                    System.out.printf("Total: R$ %.2f%n", revisaopedido.getValorTotal());
                    System.out.println("Nome: " + revisaopedido.getNomeCliente());
                    System.out.println("Endereço: " + revisaopedido.getEndereco());
                    System.out.println("Forma de pagamento: " + revisaopedido.getFormaPagamento());
                    System.out.println("\n[1] Confirmar pedido");
                    System.out.println("[2] Voltar ao carrinho");
                    System.out.print("Escolha: ");
                    int conf;

                    try {
                        String line = sc.nextLine().trim();
                        conf = line.isEmpty() ? -1 : Integer.parseInt(line);
                    } catch (Exception e) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    if (conf == 1) {
                        // criar o pedido real, limpar carrinho e informar usuário
                        pedidoController.criarPedido(carrinho.listar(), usuario.getId(), usuario.getNome(), usuario.getEndereco(), formapagamento);
                        carrinho.limpar();
                        System.out.println("Pedido confirmado!");
                        return;
                    }
                    else
                    {
                        break;
                    }

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!\n");
                    break;

            }
        }
    }

}