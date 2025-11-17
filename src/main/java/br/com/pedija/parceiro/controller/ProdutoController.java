package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {


    private static List<Produto> produtos = new ArrayList<>();
    private static int proximoId = 1;

    //Produtos para teste sem banco de dados
    static {
        produtos.add(new Produto(
                1,
                "Combo 1",
                "Sashimi de peixe branco - 24 peças",
                55.90,
                "Sushi Company"
        ));

        produtos.add(new Produto(
                2,
                "Combo Prime",
                "Combo completo com 108 peças",
                249.40,
                "Sushi Company"
        ));

        produtos.add(new Produto(
                3,
                "Combo Show",
                "Combo variado com 100 peças",
                199.90,
                "Sushi Company"
        ));
    }
    public List<Produto> listarPorParceiros(int idParceiro){
        List<Produto> resultado = new ArrayList<>();
        for(Produto p : produtos){
            if(p.getIdParceiro() == idParceiro){
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean adicionar(Produto produto){
        if(produto.getNome() == null){
            return false;
        }
        if(produto.getPreco() <= 0){
            return false;
        }
        produto.setId(proximoId++);
        return produtos.add(produto);
    }
    public Produto buscarPorId(int id){
        for(Produto p : produtos){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    public boolean remover(int id){
        return produtos.removeIf(p -> p.getId() == id);

    }

    public boolean alterarDisponibilidade(int id,boolean disponivel){
        Produto p = buscarPorId(id);
        if(p != null){
            p.setDisponivel(disponivel);
            return true;
        }
        return false;
    }
    public void listarMaisVendidos(int idParceiro) {
        List<Produto> produtosDoParceiro = listarPorParceiros(idParceiro);

        if (produtosDoParceiro.isEmpty()) {
            System.out.println("Nenhum produto cadastrado para este parceiro.");
            return;
        }

        System.out.println("\n TOP 5 PRODUTOS MAIS VENDIDOS ");
        System.out.println("-----------------------------------");

        int limite = Math.min(5, produtosDoParceiro.size());

        for (int i = 0; i < limite; i++) {
            Produto p = produtosDoParceiro.get(i);
            System.out.printf("%dº - %s%n", i + 1, p.getNome());
            System.out.printf("Preço: R$ %.2f%n", p.getPreco());
            System.out.println("Vendas: (colocar contador!!!!!!!!!!!!)");
            System.out.println();
        }
    }
    public int contarPorParceiro(int idParceiro) {
        int contador = 0;
        for (Produto p : produtos) {
            if (p.getIdParceiro() == idParceiro) {
                contador++;
            }
        }
        return contador;
    }
}