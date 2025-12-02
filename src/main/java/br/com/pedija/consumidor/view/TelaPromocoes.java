package br.com.pedija.consumidor.view;

import br.com.pedija.superadm.dao.PromocaoDAO;
import br.com.pedija.superadm.dao.ProdutoDAO;
import br.com.pedija.superadm.model.Promocao;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.consumidor.controller.CarrinhoController;

import java.util.List;
import java.util.Scanner;

public class TelaPromocoes {

    private final Scanner sc;
    private final PromocaoDAO promocaoDAO;
    private final ProdutoDAO produtoDAO;
    private final CarrinhoController carrinhoController;

    public TelaPromocoes(CarrinhoController carrinhoController, Scanner sc) {
        this.sc = sc; // Scanner vindo do menu principal
        this.promocaoDAO = new PromocaoDAO();
        this.produtoDAO = new ProdutoDAO();
        this.carrinhoController = carrinhoController;
    }

    public void verPromocoes() {
        List<Promocao> promocoes = promocaoDAO.listarTodas();

        if (promocoes.isEmpty()) {
            System.out.println("\nNão há promoções disponíveis no momento.");
            return;
        }

        System.out.println("\nPROMOÇÕES PEDIJÁ\n");
        for (Promocao p : promocoes) {
            Produto produto = produtoDAO.buscarPorId(p.getIdProduto());
            String nomeProduto = (produto != null) ? produto.getNome() : "Produto não encontrado";

            System.out.printf("ID: %d | Produto: %s | Preço Original: R$ %.2f | Promoção: R$ %.2f%n",
                    p.getId(),
                    nomeProduto,
                    p.getPrecoOriginal(),
                    p.getPrecoPromocional());
        }

        while (true) {
            System.out.print("\nDigite o ID da promoção para adicionar ao carrinho (0 para voltar): ");
            String entrada = sc.nextLine().trim();

            try {
                int id = Integer.parseInt(entrada);

                if (id == 0) return;

                Promocao promocaoSelecionada = promocoes.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);

                if (promocaoSelecionada != null) {
                    Produto produto = produtoDAO.buscarPorId(promocaoSelecionada.getIdProduto());

                    if (produto != null) {
                        Produto produtoPromocional = new Produto();
                        produtoPromocional.setId(produto.getId());
                        produtoPromocional.setNome(produto.getNome());
                        produtoPromocional.setDescricao(produto.getDescricao());
                        produtoPromocional.setPreco(promocaoSelecionada.getPrecoPromocional());
                        produtoPromocional.setIdParceiro(produto.getIdParceiro());

                        carrinhoController.adicionar(produtoPromocional);
                        System.out.println("Produto da promoção adicionado ao carrinho com preço promocional!");
                        return;
                    } else {
                        System.out.println("Produto da promoção não encontrado.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }
}