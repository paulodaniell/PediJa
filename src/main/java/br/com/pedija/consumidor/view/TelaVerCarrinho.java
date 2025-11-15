package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.model.Produto;
import java.util.Scanner;


public class TelaVerCarrinho {

    private final CarrinhoController carrinho;
    private Scanner sc;

    public TelaVerCarrinho(CarrinhoController carrinho) {

        this.carrinho = carrinho;
        this.sc = new Scanner(System.in);
    }


    public void vercarrinho(){

        if (carrinho.Vazio()) {
            System.out.println("\n====MEU CARRINHO====\n ");
            System.out.println("Seu carrinho está vazio.\n");
            return;
        }

        int op = -1;
        while (true)
        {
            System.out.println("\n====MEU CARRINHO====\n ");

            int i = 1;
            for (Produto p : carrinho.listar()) {
                System.out.printf("%d - %s (R$ %.2f)\n", i++, p.getNome(), p.getPreco());
            }

            System.out.printf("\nTotal: R$ %.2f\n\n", carrinho.precoTotal());

            System.out.println("[1] Remover item");
            System.out.println("[2] Finalizar pedido");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");


            try {
                op = sc.nextInt();
                sc.nextLine();
            } catch (Exception erro) {
                System.out.println("Opção inválida! Digite um número.\n");
                sc.nextLine();
                continue;
            }

            switch (op) {

                case 1:
                    System.out.print("Digite o número do item a remover: ");
                    int numero;
                    try
                    {
                        numero = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Item removido.\n");
                    }
                    catch (Exception erro)
                    {
                        System.out.println("Número inválido!\n");
                        sc.nextLine();
                        break;
                    }

                    int indice = numero - 1;

                    if (indice < 0 || indice >= carrinho.listar().size())
                    {
                        System.out.println("Número inválido!\n");
                    }
                    else
                    {
                        carrinho.removerProduto(indice);
                        System.out.println("Item removido com sucesso!\n");
                        return;
                    }
                    break;

                case 2:
                    System.out.println("Pedido finalizado! Acompanhe em PEDIDOS!");
                    // Aqui mais tarde você chama TelaPedidos.verPedidos();
                    // sai do carrinho e volta para o menu
                    return;

                case 0:
                    return;

                default:
                    System.out.println("Opção inválida!\n");
                    break;

            }
        }
    }

}