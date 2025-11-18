package br.com.pedija.consumidor.view;

import br.com.pedija.superadm.dao.PromocaoDAO;
import br.com.pedija.superadm.model.Promocao;
import br.com.pedija.consumidor.controller.CarrinhoController;

import java.util.List;
import java.util.Scanner;

public class TelaPromocoes {

    private final Scanner sc;
    private final PromocaoDAO promocaoDAO;
    private final CarrinhoController carrinhoController;

    public TelaPromocoes(PromocaoDAO promocaoDAO, CarrinhoController carrinhoController) {
        this.sc = new Scanner(System.in);
        this.promocaoDAO = promocaoDAO;
        this.carrinhoController = carrinhoController;
    }

    public void verPromocoes() {
        List<Promocao> promocoes = promocaoDAO.listarTodas();

        if (promocoes.isEmpty()) {
            System.out.println("Não há promoções disponíveis no momento.");
            return;
        }

        System.out.println("\n====== PROMOÇÕES PEDIJÁ ======= ");
        for (Promocao p : promocoes) {
            System.out.printf("ID: %d | Produto: %d | Preço Original: R$ %.2f | Preço Promoção: R$ %.2f | Ativa: %s%n",
                    p.getId(),
                    p.getIdProduto(),
                    p.getPrecoOriginal(),
                    p.getPrecoPromocional(),
                    p.isAtiva() ? "Sim" : "Não");
        }


        while (true) {
            System.out.print("\nDigite o ID da promoção para adicionar ao carrinho (0 para voltar): ");
            String line = sc.nextLine().trim();

            try {
                int idEscolhido = line.isEmpty() ? -1 : Integer.parseInt(line);

                if (idEscolhido == 0) {
                    return; // voltar ao menu principal
                }

                Promocao promocaoSelecionada = null;
                for (Promocao p : promocoes) {
                    if (p.getId() == idEscolhido) {
                        promocaoSelecionada = p;
                        break;
                    }
                }

                if (promocaoSelecionada != null) {
                    carrinhoController.adicionar(promocaoSelecionada);
                    System.out.println("Promoção adicionada ao carrinho!");
                    return;
                } else {
                    System.out.println("ID inválido. Escolha um ID da lista.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }
}
