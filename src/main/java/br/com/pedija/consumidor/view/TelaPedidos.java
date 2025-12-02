package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Usuario;

import java.util.List;
import java.util.Scanner;

public class TelaPedidos {

    private PedidoController pedidoController;
    private final Scanner sc;
    private final Usuario usuarioLogado;

    public TelaPedidos(PedidoController pedidoController, Usuario usuarioLogado) {
        this.pedidoController = pedidoController;
        this.usuarioLogado = usuarioLogado;
        this.sc = new Scanner(System.in);
    }

    public void verPedidos() {
        int op = -1;
        do {
            System.out.println("\nPEDIDOS\n");
            System.out.println("1 - PEDIDOS PENDENTES");
            System.out.println("2 - PEDIDOS EM PREPARO");
            System.out.println("3 - PEDIDOS À CAMINHO");
            System.out.println("4 - PEDIDOS CONCLUÍDOS");
            System.out.println("0 - Voltar");
            System.out.print("\nEscolha uma opção: ");

            try {
                String line = sc.nextLine().trim();
                op = line.isEmpty() ? -1 : Integer.parseInt(line);
                if (op != -1) {
                    resultadoEscolha(op);
                }
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }

        } while (op != 0);
    }

    private void resultadoEscolha(int op) {
        switch (op) {
            case 1 -> pedidospendentes();
            case 2 -> pedidospreparo();
            case 3 -> pedidoscaminho();
            case 4 -> pedidosfinalizados();
            case 0 -> { return; }
            default -> System.out.println("Opção inválida!");
        }
    }

    private void exibirDetalhes(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getId());
        int i = 1;
        for (Produto it : pedido.getItens()) {
            System.out.printf("%d - %s (R$ %.2f)%n", i++, it.getNome(), it.getPreco());
        }
        System.out.printf("Total: R$ %.2f%n", pedido.getValorTotal());
        System.out.println("Nome: " + pedido.getNomeCliente());
        System.out.println("Endereço: " + pedido.getEndereco());
        System.out.println("Forma de pagamento: " + pedido.getFormaPagamento());
        System.out.println("Status: " + pedido.getStatus());
    }

    private void confirmarRecebimento(Pedido pedido) {
        System.out.println("Caso tenha recebido seu pedido, confirme:");
        System.out.println("[S] - Sim");
        System.out.println("[N] - Não");

        while (true) {
            System.out.print("Escolha: ");
            String resposta = sc.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                pedido.setStatus("ENTREGUE");
                pedidoController.atualizarStatus(pedido.getId(), "ENTREGUE");
                System.out.println("\nPedido recebido e CONCLUÍDO!");
                return;
            } else if (resposta.equals("N")) {
                System.out.println("\nVoltando à lista de pedidos...");
                return;
            } else {
                System.out.println("Opção inválida. Digite 'S' ou 'N'.");
            }
        }
    }

    private void exibirDetalhesEConfirmar(Pedido pedido) {
        exibirDetalhes(pedido);
        confirmarRecebimento(pedido);
    }

    private void pedidospendentes() {
        List<Pedido> pendentes = pedidoController.listarPendentes();
        System.out.println("\nPEDIDOS PENDENTES\n");

        if (pendentes.isEmpty()) {
            System.out.println("Nenhum pedido pendente.\n");
            return;
        }

        for (Pedido p : pendentes) {
            Pedido completo = pedidoController.buscarPorId(p.getId());
            exibirDetalhes(completo);
            System.out.println();
        }

        selecionarPedido(pendentes, false);
    }

    private void pedidospreparo() {
        List<Pedido> preparo = pedidoController.listarEmPreparo();
        System.out.println("\nPEDIDOS EM PREPARO\n");

        if (preparo.isEmpty()) {
            System.out.println("Nenhum pedido em preparo.\n");
            return;
        }

        for (Pedido p : preparo) {
            Pedido completo = pedidoController.buscarPorId(p.getId());
            exibirDetalhes(completo);
            System.out.println();
        }

        selecionarPedido(preparo, false);
    }

    private void pedidoscaminho() {
        List<Pedido> emAndamento = pedidoController.listarEmEntrega();
        System.out.println("\nPEDIDOS À CAMINHO\n");

        if (emAndamento.isEmpty()) {
            System.out.println("Nenhum pedido em andamento.\n");
            return;
        }

        for (Pedido p : emAndamento) {
            Pedido completo = pedidoController.buscarPorId(p.getId());
            exibirDetalhes(completo);
            System.out.println();
        }

        selecionarPedido(emAndamento, true);
    }

    private void pedidosfinalizados() {
        List<Pedido> concluido = pedidoController.listarProntos();
        System.out.println("\nPEDIDOS CONCLUÍDOS\n");

        if (concluido.isEmpty()) {
            System.out.println("Nenhum pedido concluído.\n");
            return;
        }

        for (Pedido p : concluido) {
            Pedido completo = pedidoController.buscarPorId(p.getId());
            exibirDetalhes(completo);
            System.out.println();
        }

        System.out.print("Digite 0 para voltar: ");
        while (true) {
            try {
                String line = sc.nextLine().trim();
                int resposta = line.isEmpty() ? -1 : Integer.parseInt(line);
                if (resposta == 0) return;
                System.out.println("Opção inválida.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas 0.");
            }
        }
    }

    private void selecionarPedido(List<Pedido> lista, boolean permitirConfirmar) {
        if (lista.isEmpty()) return;

        while (true) {
            System.out.println("Digite o NÚMERO do pedido para ver detalhes.");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");

            try {
                String line = sc.nextLine().trim();
                int escolha = line.isEmpty() ? -1 : Integer.parseInt(line);

                if (escolha == 0) return;

                if (escolha > 0 && escolha <= lista.size()) {
                    Pedido pedidoSelecionado = pedidoController.buscarPorId(lista.get(escolha - 1).getId());
                    if (permitirConfirmar) {
                        exibirDetalhesEConfirmar(pedidoSelecionado);
                    } else {
                        exibirDetalhes(pedidoSelecionado);
                    }
                    return;
                } else if (escolha != -1) {
                    System.out.println("Opção inválida. Digite um número da lista ou 0 para voltar.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }
}
