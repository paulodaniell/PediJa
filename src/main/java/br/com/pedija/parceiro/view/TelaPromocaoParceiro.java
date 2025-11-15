package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.ProdutoController;
import br.com.pedija.parceiro.controller.PromocaoController;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Promocao;
import java.util.List;
import java.util.Scanner;


public class TelaPromocaoParceiro {

    private Scanner sc;
    private Parceiro parceiro;
    private ProdutoController produtoController;
    private PromocaoController promocaoController;


    public TelaPromocaoParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.produtoController = new ProdutoController();
        this.promocaoController = new PromocaoController();
    }

    public void menuPromocaoParceiro() {
        int opcao = -1;

        do {
            System.out.println("\n GERENCIAR PROMOÇÕES - " + parceiro.getNome());
            System.out.println("------------------------------");
            System.out.println(" 1 - Criar Promoção");
            System.out.println(" 2 - Remover Promoção");
            System.out.println(" 3 - Listar Promoções Ativas");
            System.out.println(" 0 - Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOp(opcao);
            } catch (Exception erro) {
                System.out.println(" Erro: digite apenas números!");
                sc.nextLine();
            }

        } while (opcao != 0);
    }

    private void resultadoOp(int opcao) {
        switch (opcao) {
            case 1:
                criarPromocao();
                break;
            case 2:
                removerPromocao();
                break;
            case 3:
                listarPromocoesAtivas();
                break;

            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println(" Opção inválida!");
                break;
        }
    }

    private void criarPromocao() {
        System.out.println("\n================================\n");
        System.out.println("     CRIAR NOVA PROMOÇÃO            ");
        System.out.println("================================\n");

        List<Produto> produtos = produtoController.listarPorParceiros(parceiro.getId());

        if (produtos.isEmpty()) {
            System.out.println(" Você não tem produtos cadastrados!");

            return;
        }
        System.out.println("SEUS PRODUTOS:");
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + " - " + p.getNome() +
                    " (R$ " + String.format("%.2f", p.getPreco()) + ")");
        }

        try {
            System.out.print("\nDigite o ID do produto: ");
            int idProduto = sc.nextInt();
            sc.nextLine();

            Produto produto = produtoController.buscarPorId(idProduto);

            if (produto == null) {
                System.out.println(" Produto não encontrado!");

                return;
            }

            if (produto.getIdParceiro() != parceiro.getId()) {
                System.out.println(" Este produto não é seu!");

                return;
            }
            System.out.println("\nProduto: " + produto.getNome());
            System.out.println("Preço Original: R$ " + String.format("%.2f", produto.getPreco()));

            System.out.print("\nPreço Promocional (R$): ");
            double precoPromocional = sc.nextDouble();
            sc.nextLine();

            if (precoPromocional >= produto.getPreco()) {
                System.out.println(" Preço promocional deve ser menor que o original!");

                return;
            }
            double desconto = ((produto.getPreco() - precoPromocional) / produto.getPreco()) * 100;

            System.out.println("\nDesconto: " + String.format("%.0f", desconto) + "%");
            System.out.print("Confirmar promoção? (S/N): ");
            String confirma = sc.nextLine();

            if (confirma.equalsIgnoreCase("S")) {
                Promocao promocao = new Promocao();
                promocao.setIdProduto(produto.getId());
                promocao.setIdParceiro(parceiro.getId());
                promocao.setPrecoOriginal(produto.getPreco());
                promocao.setPrecoPromocional(precoPromocional);
                promocao.setPercentualDesconto(desconto);
                promocao.setAtiva(true);

                if (promocaoController.adicionar(promocao)) {
                    System.out.println("\n Promoção criada com sucesso!");
                    System.out.println("Código da promoção: " + promocao.getId());
                }
            } else {
                System.out.println(" Promoção cancelada!");
            }

        } catch (Exception e) {
            System.out.println(" Erro: " + e.getMessage());
            sc.nextLine();
        }


    }
    private void removerPromocao() {
        System.out.println("\n================================\n");
        System.out.println("     REMOVER PROMOÇÃO               ");
        System.out.println("================================\n\n");

        List<Promocao> promocoes = promocaoController.listarPorParceiro(parceiro.getId());

        if (promocoes.isEmpty()) {
            System.out.println(" Você não tem promoções cadastradas!");

            return;
        }


        for (Promocao p : promocoes) {
            System.out.println("ID: " + p.getId() + " | Produto ID: " + p.getIdProduto() +
                    " | De: R$ " + String.format("%.2f", p.getPrecoOriginal()) +
                    " Por: R$ " + String.format("%.2f", p.getPrecoPromocional()) +
                    " | " + (p.isAtiva() ? " Ativa" : " Inativa"));
        }

        try {
            System.out.print("\nID da promoção para remover: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Confirmar remoção? (S/N): ");
            String confirma = sc.nextLine();

            if (confirma.equalsIgnoreCase("S")) {
                if (promocaoController.remover(id)) {
                    System.out.println(" Promoção removida!");
                } else {
                    System.out.println(" Erro ao remover!");
                }
            }

        } catch (Exception e) {
            System.out.println(" Erro: " + e.getMessage());
            sc.nextLine();
        }
    }
    private void listarPromocoesAtivas() {
        System.out.println("\n================================\n");
        System.out.println("     PROMOÇÕES ATIVAS               ");
        System.out.println("================================\n");

        List<Promocao> promocoes = promocaoController.listarPorParceiro(parceiro.getId());

        if (promocoes.isEmpty()) {
            System.out.println("Nenhuma promoção cadastrada.");
        } else {
            for (Promocao p : promocoes) {
                if (p.isAtiva()) {
                    System.out.println("─────────────────────────────");
                    System.out.println("ID: " + p.getId());
                    System.out.println("Produto ID: " + p.getIdProduto());
                    System.out.println("De: R$ " + String.format("%.2f", p.getPrecoOriginal()));
                    System.out.println("Por: R$ " + String.format("%.2f", p.getPrecoPromocional()));
                    System.out.println("Desconto: " + String.format("%.0f", p.getPercentualDesconto()) + "%");
                }
            }
        }

    }
}