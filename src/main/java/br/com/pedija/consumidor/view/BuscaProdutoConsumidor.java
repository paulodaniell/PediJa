package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.model.Produto;

import java.util.ArrayList;
import java.util.Scanner;

public class BuscaProdutoConsumidor {

    private Scanner sc = new Scanner(System.in);

    // Lista fake de produtos — substituir pelo BD depois
    // AQUI ENTRA O BANCO DE DADOS (FUTURO)

    private ArrayList<Produto> produtos = new ArrayList<>();

    produtos.add(new Produto(1, "Pizza", "Pizza grande de calabresa", 45.00));
    produtos.add(new Produto(2, "Coxinha", "Coxinha de frango", 8.50, 30));
    produtos.add(new Produto(3, "Hamburger", "Hamburger artesanal", 22.00, 15));

    void exibirbuscarProdutos(){

        while(true){
            System.out.println("Qual produto você gostaria de pedir? (digite 0 para voltar)");
            System.out.print("Digite: ");
            String entrada = sc.nextLine().trim();

            if(entrada.equals("0"))
            {
                return;
            }
            if(entrada.isEmpty())
            {
                System.out.println("Escreva um nome válido.\n");
                continue;
            }

            boolean encontrado = produtosDisponiveisteste.stream()
                    .anyMatch(p -> p.equalsIgnoreCase(entrada));

            if(encontrado)
            {
                // preenche um Produto com dados básicos (substituir pelo BD depois)
                Produto produto = new Produto();
                produto.setNome(entrada);

            }

            if (produto.checar()) {
                System.out.println("Produto encontrado com sucesso!\n");
                System.out.println("Produto: " + produto.checar());
                System.out.println("Informações: ");
            }
            else
            {
                // volta para pedir outro produto
                continue;
            }
        }

    }

}
