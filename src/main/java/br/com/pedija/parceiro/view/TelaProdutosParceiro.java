package br.com.pedija.parceiro.view;


import br.com.pedija.parceiro.controller.ProdutoController;
import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Produto;
import java.util.List;
import java.util.Scanner;


public class TelaProdutosParceiro {
    private Scanner sc;
    private Parceiro parceiro;
    private ProdutoController produtoController;


    public TelaProdutosParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.produtoController = new ProdutoController();
    }


    public void exibirProdutos() {
        int opcao = -1;


        do {


            System.out.println("------------------------------");
            System.out.println("GERENCIAR PRODUTOS - " + parceiro.getNome());
            System.out.println("------------------------------");


            List<Produto> produtos = produtoController.listarPorParceiros(parceiro.getId());


            exibirProdutos(produtos);
            exibirMenu();


            try {
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();


                Opcao(opcao);


            } catch (Exception e) {
                System.out.println(" Entrada inválida!");
                sc.nextLine();


            }


        } while (opcao != 0);
    }
    private void exibirProdutos(List<Produto> produtos) {
        System.out.println("\n-----------------------------------------------");
        System.out.println("  PRODUTOS CADASTRADOS (" + produtos.size() + ")");
        System.out.println("-------------------------------------------------");


        if (produtos.isEmpty()) {
            System.out.println("| Nenhum produto cadastrado");
        } else {
            for (Produto p : produtos) {
                String status = p.isDisponivel() ? "Disponível" : " Indisponível";


                System.out.println("|");
                System.out.println("|  ID: " + p.getId() + " | " + status);
                System.out.println("|  " + p.getNome());
                System.out.printf("|  R$ %.2f%n", p.getPreco());




                if (p.getDescricao() != null && !p.getDescricao().isEmpty()) {
                    System.out.println("|  " + p.getDescricao());
                }


                System.out.println(" ------------------------------------------");
            }
        }




    }
    private void exibirMenu() {
        System.out.println("1- Adicionar Produto");
        System.out.println("2 - Editar Produto");
        System.out.println("3 - Remover Produto");
        System.out.println("4 -Alterar Disponibilidade");
        System.out.println("0 -Voltar");
    }




    private void Opcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarProduto();
                break;
            case 2:
                editarProduto();
                break;
            case 3:
                removerProduto();
                break;
            case 4:
                alterarDisponibilidade();
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println(" Opção inválida!");


        }
    }




    private void adicionarProduto() {

        System.out.println("\n-----------------------------------");
        System.out.println("|      ADICIONAR NOVO PRODUTO     |");
        System.out.println("-----------------------------------\n");

        Produto novo = new Produto();

        try {
            System.out.print(" Nome do Produto: ");
            novo.setNome(sc.nextLine());

            System.out.print(" Descrição: ");
            novo.setDescricao(sc.nextLine());

            System.out.print(" Preço (R$): ");
            novo.setPreco(sc.nextDouble());
            sc.nextLine();

            System.out.print(" Categoria: ");
            novo.setCategoriaNome(sc.nextLine());

            System.out.print(" Tempo de preparo (min): ");
            novo.setTempoPreparo(sc.nextInt());
            sc.nextLine();

            novo.setDisponivel(true);
            novo.setIdParceiro(parceiro.getId()); // IMPORTANTE!!!

            // CHAMA APENAS UMA VEZ
            boolean sucesso = produtoController.adicionarProduto(novo);

            if (sucesso) {
                System.out.println("\n Produto adicionado com sucesso!");
                System.out.println("ID gerado: " + novo.getId());
            } else {
                System.out.println("\n Erro ao adicionar produto!");
            }

        } catch (Exception e) {
            System.out.println("\n Erro ao cadastrar: " + e.getMessage());
            sc.nextLine();
        }
    }



    private void editarProduto() {


        System.out.println("\n==================================");
        System.out.println("          EDITAR PRODUTO             ");
        System.out.println("\n==================================");


        try {
            System.out.print("ID do produto: ");


            int id = sc.nextInt();
            sc.nextLine();


            Produto produto = produtoController.buscarPorId(id);


            if (produto == null) {
                System.out.println("\n Produto não encontrado!");


                return;
            }




            if (produto.getIdParceiro() != parceiro.getId()) {
                System.out.println("\n Este produto não pertence ao seu restaurante!");


                return;
            }


            System.out.println("\nEditando: " + produto.getNome());
            System.out.println("\n[1] Editar Nome");
            System.out.println("[2] Editar Descrição");
            System.out.println("[3] Editar Preço");
            System.out.println("[4] Editar Categoria");
            System.out.println("[5] Editar Tempo de Preparo");
            System.out.println("[0] Cancelar");


            System.out.print("\nEscolha o que editar: ");
            int opcao = sc.nextInt();
            sc.nextLine();


            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    produto.setNome(sc.nextLine());
                    System.out.println(" Nome atualizado!");
                    break;
                case 2:
                    System.out.print("Nova descrição: ");
                    produto.setDescricao(sc.nextLine());
                    System.out.println(" Descrição atualizada!");
                    break;
                case 3:
                    System.out.print("Novo preço (R$): ");
                    produto.setPreco(sc.nextDouble());
                    sc.nextLine();
                    System.out.println("Preço atualizado!");
                    break;
                case 4:
                    System.out.print("Nova categoria: ");
                    produto.setCategoriaNome(sc.nextLine());
                    System.out.println(" Categoria atualizada!");
                    break;
                case 5:
                    System.out.print("Novo tempo (min): ");
                    produto.setTempoPreparo(sc.nextInt());
                    sc.nextLine();
                    System.out.println(" Tempo atualizado!");
                    break;
                case 0:
                    System.out.println(" Edição cancelada!");
                    break;
                default:
                    System.out.println(" Opção inválida!");
            }


        } catch (Exception e) {
            System.out.println("\n Erro: " + e.getMessage());
            sc.nextLine();
        }




    }




    private void removerProduto() {


        System.out.println("\n===========================================");
        System.out.println("         REMOVER PRODUTO                 ");
        System.out.println("\n===========================================\n");


        try {
            System.out.print("ID do produto: ");
            int id = sc.nextInt();
            sc.nextLine();


            Produto produto = produtoController.buscarPorId(id);


            if (produto == null) {
                System.out.println("\n Produto não encontrado!");


                return;
            }




            if (produto.getIdParceiro() != parceiro.getId()) {
                System.out.println("\n Este produto não pertence ao seu restaurante!");


                return;
            }


            System.out.println("\nRemover: " + produto.getNome());
            System.out.printf("Preço: R$ %.2f%n", produto.getPreco());
            System.out.print("\n Confirma remoção? (S/N): ");
            String confirma = sc.nextLine();


            if (confirma.equalsIgnoreCase("S")) {
                if (produtoController.remover(id)) {
                    System.out.println("\n Produto removido com sucesso!");
                } else {
                    System.out.println("\n Erro ao remover produto!");
                }
            } else {
                System.out.println("\n Remoção cancelada!");
            }


        } catch (Exception e) {
            System.out.println("\n Erro: " + e.getMessage());
            sc.nextLine();
        }




    }






    private void alterarDisponibilidade() {


        System.out.println("\n===========================================");
        System.out.println("     ALTERAR DISPONIBILIDADE             ");
        System.out.println("===========================================\n");


        try {
            System.out.print("ID do produto: ");
            int id = sc.nextInt();
            sc.nextLine();


            Produto produto = produtoController.buscarPorId(id);


            if (produto == null) {
                System.out.println("\n Produto não encontrado!");


                return;
            }


            if (produto.getIdParceiro() != parceiro.getId()) {
                System.out.println("\n Este produto não pertence ao seu restaurante!");


                return;
            }


            String statusAtual = produto.isDisponivel() ? "Disponível " : "Indisponível ";
            System.out.println("\nProduto: " + produto.getNome());
            System.out.println("Status atual: " + statusAtual);


            System.out.println("\n[1] Marcar como Disponível");
            System.out.println("[2] Marcar como Indisponível");
            System.out.println("[0] Cancelar");


            System.out.print("\nEscolha: ");
            int opcao = sc.nextInt();
            sc.nextLine();


            if (opcao == 1) {
                if (produtoController.alterarDisponibilidade(id, true)) {
                    System.out.println("\n Produto marcado como DISPONÍVEL!");
                }
            } else if (opcao == 2) {
                if (produtoController.alterarDisponibilidade(id, false)) {
                    System.out.println("\n Produto marcado como INDISPONÍVEL!");
                }
            } else if (opcao == 0) {
                System.out.println("\n Operação cancelada!");
            } else {
                System.out.println("\n Opção inválida!");
            }


        } catch (Exception e) {
            System.out.println("\n Erro: " + e.getMessage());
            sc.nextLine();
        }


    }


}



