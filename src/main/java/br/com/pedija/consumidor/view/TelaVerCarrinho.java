package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.CarrinhoController;
import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class TelaVerCarrinho {

    private final UsuarioController usuarioController;
    private final PedidoController pedidoController = new PedidoController();
    private final Usuario usuario;
    private final CarrinhoController carrinho;
    private final Scanner sc;

    public TelaVerCarrinho(CarrinhoController carrinho, Usuario usuario, UsuarioController usuarioController) {
        this.carrinho = carrinho;
        this.usuario = usuario;
        this.usuarioController = usuarioController;
        this.sc = new Scanner(System.in);
    }

    public void verCarrinho() {
        if (carrinho.Vazio()) {
            System.out.println("\n==== MEU CARRINHO ====\n");
            System.out.println("Seu carrinho está vazio.\n");
            return;
        }

        while (true) {
            System.out.println("\n==== MEU CARRINHO ====\n");

            int i = 1;
            for (Produto p : carrinho.listar()) {
                System.out.printf("%d - %s (R$ %.2f)\n", i++, p.getNome(), p.getPreco());
            }
            System.out.printf("\nTotal: R$ %.2f\n\n", carrinho.precoTotal());

            System.out.println("[1] Remover item");
            System.out.println("[2] Finalizar pedido");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");

            int op = lerInt();

            switch (op) {
                case 1 -> removerItem();
                case 2 -> finalizarPedido();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida! Digite 0, 1 ou 2.\n");
            }
        }
    }

    private void removerItem() {
        System.out.print("Digite o número do item a remover: ");
        int numero = lerInt();
        int indice = numero - 1;

        if (indice < 0 || indice >= carrinho.listar().size()) {
            System.out.println(" Número do item inválido!\n");
        } else {
            carrinho.removerProduto(indice);
            System.out.println("Item removido com sucesso!\n");
        }
    }

    private void finalizarPedido() {
        String forma = escolherFormaPagamento();
        if (forma == null) {
            System.out.println("\n Pagamento cancelado. Voltando ao carrinho...\n");
            return;
        }

        usuario.setFormadepagamento(forma);
        usuarioController.atualizarUsuario(usuario);

        Pedido revisao = pedidoController.revisaopedido(
                carrinho.listar(),
                usuario.getId(),
                usuario.getNome(),
                usuario.getEndereco(),
                forma
        );

        exibirResumoPedido(revisao);

        revisao.setStatus("PENDENTE");

        System.out.print("Escolha: ");
        int conf = lerInt();

        if (conf == 1) {
            try {
                pedidoController.criarPedido(revisao);
                carrinho.limpar();
                System.out.println("\n Pedido confirmado com sucesso!");

            } catch (Exception e) {
                System.out.println("\n Erro ao confirmar pedido: " + e.getMessage());
            }
        } else {
            System.out.println("\n Pedido cancelado. Voltando ao carrinho...\n");
        }
    }

    private void exibirResumoPedido(Pedido pedido) {
        System.out.println("\n=== RESUMO DO PEDIDO ===");

        List<Produto> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            System.out.println("O pedido está vazio!");
            return;
        }

        int j = 1;
        for (Produto it : itens) {
            System.out.printf("%d - %s (R$ %.2f)%n", j++, it.getNome(), it.getPreco());
        }

        System.out.printf("Total: R$ %.2f%n", pedido.getValorTotal());
        System.out.println("Nome: " + pedido.getNomeCliente());
        System.out.println("Endereço: " + pedido.getEndereco());
        System.out.println("Forma de pagamento: " + pedido.getFormaPagamento());
        System.out.println("\n[1] Confirmar pedido");
        System.out.println("[2] Voltar ao carrinho");
    }

    private String escolherFormaPagamento() {
        while (true) {
            System.out.println("\nQual a forma de pagamento?");
            System.out.println("[1] PIX");
            System.out.println("[2] Cartão de Crédito");
            System.out.println("[3] Cartão de Débito");
            System.out.println("[4] Dinheiro");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");

            int escolha = lerInt();

            switch (escolha) {
                case 1 -> { return "PIX"; }
                case 2 -> { return "Cartão de Crédito"; }
                case 3 -> { return "Cartão de Débito"; }
                case 4 -> { return "Dinheiro"; }
                case 0 -> { return null; }
                default -> System.out.println("Opção inválida! Digite 0 a 4.\n");
            }
        }
    }

    private int lerInt() {
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.print("Digite um número válido: ");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida! Digite apenas números: ");
            }
        }
    }
}