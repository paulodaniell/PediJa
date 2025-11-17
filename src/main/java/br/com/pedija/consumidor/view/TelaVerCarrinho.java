package br.com.pedija.consumidor.view;


import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Usuario;


import java.util.List;
import java.util.Scanner;




public class TelaVerCarrinho {


    private final UsuarioController usuarioController;
    private final PedidoController pedidoController = new PedidoController();
    private final Usuario usuario;
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


            int op = -1;
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


                    String forma = null;
                    while (true) {
                        System.out.println("\nQual a forma de pagamento?");
                        System.out.println("[1] PIX");
                        System.out.println("[2] CARTÃO DE CRÉDITO");
                        System.out.println("[3] CARTÃO DE DÉBITO");
                        System.out.println("[4] DINHEIRO");
                        System.out.println("[0] Voltar");
                        System.out.print("\nEscolha: ");


                        String line = sc.nextLine().trim();
                        int escolha;


                        // CORREÇÃO: Lógica de parsing robusta
                        try {
                            escolha = Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            System.out.println("Opção inválida! Digite apenas o número.\n");
                            continue;
                        }
                        // Fim da CORREÇÃO


                        switch (escolha) {
                            case 1: forma = "PIX"; break;
                            case 2: forma = "Cartão de Crédito"; break;
                            case 3: forma = "Cartão de Débito"; break;
                            case 4: forma = "Dinheiro"; break;
                            case 0: forma = null; break;
                            default:
                                System.out.println("Opção inválida!\n");
                                forma = null;
                        }


                        // Se escolheu '0' (Voltar) ou uma forma válida (1-4), sai do loop de pagamento
                        if (escolha == 0 || forma != null) {
                            break;
                        }
                        // Senão, a opção foi inválida (default), o loop continua.
                    }


                    if (forma == null) {
                        // usuário cancelou/voltou -> volta ao carrinho
                        break;
                    }


                    usuario.setFormadepagamento(forma);
                    usuarioController.atualizarUsuario(usuario);;

                    // cria preview do pedido
                    Pedido revisaopedido = pedidoController.revisaopedido(carrinho.listar(), usuario.getId(), usuario.getNome(), usuario.getEndereco(), forma);


                    System.out.println("\n=== RESUMO DO PEDIDO ===");

                    int j = 1;

                    List<Produto> itensPedido = revisaopedido.getItens();

                    if (itensPedido == null || itensPedido.isEmpty()) {
                        System.out.println("O pedido está vazio!");
                    } else {
                        for (Produto it : itensPedido) {
                            System.out.printf("%d - %s (R$ %.2f)%n", j++, it.getNome(), it.getPreco());
                        }
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
                        pedidoController.criarPedido(revisaopedido);
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

