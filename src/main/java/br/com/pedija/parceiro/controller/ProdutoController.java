package br.com.pedija.parceiro.controller;

import br.com.pedija.parceiro.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private static List<Produto> produtos = new ArrayList<>();
    private static int proximoId = 1;

    //Produtos para teste sem banco de dados
    static{
        produtos.add(new Produto(1,"Combo 1 - sashimi de peixe branco - 24 peças",55.90,1));
        produtos.add(new Produto(2,"Combo Prime - 108 peças",249.40,1));
        produtos.add(new Produto(3,"Combo Show - 100 peças",199.90,1));
    }
    //Listar produtos de um parceiro
    public List<Produto> listarPorParceiros(int idParceiro){
        List<Produto> resultado = new ArrayList<>();
        for(Produto p : produtos){
            if(p.getIdParceiro() == idParceiro){
                resultado.add(p);
            }
        }
        return resultado;
    }
    //Adicionar produto
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
        // Remove o produto com o ID especificado e retorna true se remover algum
    }

    public boolean alterarDisponibilidade(int id,boolean disponivel){
        Produto p = buscarPorId(id);
        if(p != null){
            p.setDisponivel(disponivel);
            return true;
        }
        return false;
    }
}
