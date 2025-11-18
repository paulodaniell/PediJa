package br.com.pedija.consumidor.view;


import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.consumidor.controller.ProdutoController;
import br.com.pedija.superadm.dao.ProdutoDAO;



import java.util.Scanner;


public class BuscaProdutoConsumidor {


    private Scanner sc = new Scanner(System.in);;
    private final ProdutoController produtoController = new ProdutoController();
    private CarrinhoController carrinhoController;
    ProdutoDAO produtoDAO = new ProdutoDAO();


    public BuscaProdutoConsumidor(CarrinhoController carrinhoController) {
        this.carrinhoController = carrinhoController;
    }


    void exibirbuscarProdutos() {

        while (true) {


            System.out.println("\nQual produto você gostaria de pedir? (digite 0 para voltar)");
            System.out.print("Digite: ");
            String entrada = sc.nextLine().trim();


            if (entrada.equals("0")) {
                return;
            }
            if (entrada.isEmpty()) {
                System.out.println("Escreva um nome válido.\n");
                continue;
            }

            produtoController.buscarPorNome(entrada);

            Produto produto = produtoController.buscarPorNome(entrada);


            if (produto != null) {
                System.out.println("\nProduto encontrado com sucesso!");
                System.out.println("Produto: " + produto.getNome());
                System.out.println("Descrição: " + produto.getDescricao());
                System.out.printf("Preço: R$ %.2f%n", produto.getPreco());
                System.out.println("Loja: " + produto.getNomeParceiro());
                System.out.println();


                System.out.println("[1] Adicionar ao carrinho");
                System.out.println("[2] Voltar à busca");
                System.out.print("Escolha: ");
                String op = sc.nextLine().trim();


                if (op.equals("1")) {
                    carrinhoController.adicionar(produto);
                    System.out.println("\nProduto adicionado ao carrinho! Voltando ao menu...");
                    return;
                }

            } else {
                System.out.println("\nProduto não encontrado! Tente outro nome.");
            }


        }
    }
}



