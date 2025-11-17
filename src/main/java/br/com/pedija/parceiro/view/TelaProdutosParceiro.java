package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.ProdutoController;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Produto;

import java.util.List;
import java.util.Scanner;

public class TelaProdutosParceiro {

    private final Scanner sc;
    private final Parceiro parceiro;
    private final ProdutoController produtoController;

    public TelaProdutosParceiro(Parceiro parceiro, Scanner sc) {
        this.sc = sc;
        this.parceiro = parceiro;
        this.produtoController = new ProdutoController();
    }

    public void exibirProdutos() {
        int opcao = -1;
        do {
            System.out.println("\n===== GERENCIAR PRODUTOS - " + parceiro.getNome() + " =====");
            List<Produto> produtos = produtoController.listarPorParceiros(parceiro.getId());
            listarProdutos(produtos);

            exibirMenu();

            try {
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());
                executarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("⚠️  Entrada inválida! Digite apenas números.");
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage());
                e.printStackTrace();
            }
        } while (opcao != 0);
    }

    private void listarProdutos(List<Produto> produtos) {
        System.out.println("\n-----------------------------------------------");
        System.out.println("  PRODUTOS CADASTRADOS (" + produtos.size() + ")");
        System.out.println("-----------------------------------------------");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) {
            String status = p.isDisponivel() ? "Disponível" : "Indisponível";
            System.out.printf("ID: %d | %s | R$ %.2f | %s%n", p.getId(), p.getNome(), p.getPreco(), status);
            if (p.getDescricao() != null && !p.getDescricao().isBlank()) {
                System.out.println("   → " + p.getDescricao());
            }
        }
        System.out.println("-----------------------------------------------");
    }

    private void exibirMenu() {
        System.out.println("1 - Adicionar Produto");
        System.out.println("2 - Editar Produto");
        System.out.println("3 - Remover Produto");
        System.out.println("4 - Alterar Disponibilidade");
        System.out.println("0 - Voltar");
    }

    private void executarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> adicionarProduto();
            case 2 -> editarProduto();
            case 3 -> removerProduto();
            case 4 -> alterarDisponibilidade();
            case 0 -> System.out.println("Voltando...");
            default -> System.out.println("Opção inválida!");
        }
    }

    private void adicionarProduto() {
        try {
            Produto novo = new Produto();
            System.out.print("Nome: ");
            novo.setNome(sc.nextLine());
            System.out.print("Descrição: ");
            novo.setDescricao(sc.nextLine());
            System.out.print("Preço (R$): ");
            novo.setPreco(Double.parseDouble(sc.nextLine()));
            System.out.print("Quantidade: ");
            novo.setQuantidade(Integer.parseInt(sc.nextLine()));
            novo.setCategoria_id(1);
            novo.setDisponivel(true);
            novo.setIdParceiro(parceiro.getId());

            if (produtoController.adicionarProduto(novo)) {
                System.out.println("✅ Produto adicionado com sucesso!");
            } else {
                System.out.println("❌ Falha ao adicionar produto.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private void editarProduto() {
        try {
            System.out.print("ID do produto: ");
            int id = Integer.parseInt(sc.nextLine());
            Produto produto = produtoController.buscarPorId(id);
            if (produto == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            System.out.println("Editando: " + produto.getNome());
            System.out.println("1 - Nome | 2 - Descrição | 3 - Preço | 4 - Quantidade | 0 - Cancelar");
            System.out.print("Escolha: ");
            int opc = Integer.parseInt(sc.nextLine());

            switch (opc) {
                case 1 -> { System.out.print("Novo nome: "); produto.setNome(sc.nextLine()); }
                case 2 -> { System.out.print("Nova descrição: "); produto.setDescricao(sc.nextLine()); }
                case 3 -> { System.out.print("Novo preço: "); produto.setPreco(Double.parseDouble(sc.nextLine())); }
                case 4 -> { System.out.print("Nova quantidade: "); produto.setQuantidade(Integer.parseInt(sc.nextLine())); }
                case 0 -> { System.out.println("Edição cancelada."); return; }
                default -> { System.out.println("Opção inválida!"); return; }
            }

            if (produtoController.atualizarProduto(produto)) {
                System.out.println("✅ Produto atualizado com sucesso!");
            } else {
                System.out.println("❌ Erro ao atualizar produto!");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void removerProduto() {
        try {
            System.out.print("ID do produto: ");
            int id = Integer.parseInt(sc.nextLine());
            Produto produto = produtoController.buscarPorId(id);

            if (produto == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            System.out.print("Remover " + produto.getNome() + "? (S/N): ");
            String conf = sc.nextLine();

            if (conf.equalsIgnoreCase("S")) {
                if (produtoController.remover(id)) {
                    System.out.println("✅ Produto removido com sucesso!");
                } else {
                    System.out.println("❌ Erro ao remover produto!");
                }
            } else {
                System.out.println("Remoção cancelada.");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterarDisponibilidade() {
        try {
            System.out.print("ID do produto: ");
            int id = Integer.parseInt(sc.nextLine());
            Produto produto = produtoController.buscarPorId(id);
            if (produto == null) {
                System.out.println("Produto não encontrado!");
                return;
            }

            boolean novoStatus = !produto.isDisponivel();
            if (produtoController.alterarDisponibilidade(id, novoStatus)) {
                System.out.println("✅ Produto agora está " + (novoStatus ? "DISPONÍVEL" : "INDISPONÍVEL"));
            } else {
                System.out.println("❌ Erro ao alterar disponibilidade!");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
