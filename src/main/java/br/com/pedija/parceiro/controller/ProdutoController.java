package br.com.pedija.parceiro.controller;


import br.com.pedija.parceiro.view.TelaPedidosParceiro;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.dao.ProdutoDAO;
import br.com.pedija.parceiro.view.TelaProdutosParceiro;


import java.util.ArrayList;
import java.util.List;


public class ProdutoController {


    ProdutoDAO produtoDAO = new ProdutoDAO();
    TelaPedidosParceiro view;


    private static List<Produto> produtos = new ArrayList<>();
    private static int proximoId = 1;



    public void adicionarProduto(Produto produto) {
        try {
            produtoDAO.criar(produto);
            view.exibirMensagemSucesso("Pedido Cadastrado com sucesso!");


        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
    }


    public List<Produto> listarProdutos() {
        try {
            List<Produto> produto = produtoDAO.buscarTodos();


        } catch (Exception e) {
            view.exibirErro(e.getMessage());
        }
        return null;
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

