package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.superadm.model.Produto;
import br.com.pedija.superadm.model.Usuario;
import br.com.pedija.superadm.model.Pedido;

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
            System.out.println("\n=====PEDIDOS=====\n");
            System.out.println("[1] Pedidos Em PREPARO");
            System.out.println("[2] PEDIDOS À CAMINHO");
            System.out.println("[3] PEDIDOS CONCLUÍDOS");
            System.out.println("[4] PEDIDOS PENDENTES");
            System.out.println("[0] Voltar");

            System.out.print("\nEscolha uma opção: ");

            try {
                String line = sc.nextLine().trim();
                op = line.isEmpty() ? -1 : Integer.parseInt(line);

                if (op != -1) {
                    resultadoescolha(op);
                }
            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }

        } while (op != 0);
    }

    private void resultadoescolha(int op) {
        switch (op) {
            case 1:
                pedidospreparo();
                break;
            case 2:
                pedidoscaminho();
                break;
            case 3:
                pedidosfinalizados();
                break;
            case 4:
                pedidospendentes();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void exibirDetalhes(Pedido pedidoSelecionado) {

        System.out.println("\n=== DETALHES DO PEDIDO #" + pedidoSelecionado.getId() + " ===");

        int i = 1;
        for (Produto it : pedidoSelecionado.getItens()) {
            System.out.printf("%d - %s (R$ %.2f)%n", i++, it.getNome(), it.getPreco());
        }

        System.out.printf("Total: R$ %.2f%n", pedidoSelecionado.getValorTotal());
        System.out.println("Nome: " + pedidoSelecionado.getNomeCliente());
        System.out.println("Endereço: " + pedidoSelecionado.getEndereco());
        System.out.println("Forma de pagamento: " + pedidoSelecionado.getFormaPagamento());
        System.out.println("------------------------------------");

    }

    private void Confirmar(Pedido pedidoconfirmado) {

        System.out.println("Caso tenha recebido seu pedido, confirme:");
        System.out.println("[S]");
        System.out.println("[N]");

        while (true) {
            System.out.print("Escolha: ");
            String resposta = sc.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                pedidoconfirmado.setStatus("ENTREGUE");
                pedidoController.atualizarStatus(pedidoconfirmado.getId(), "ENTREGUE");
                System.out.printf("\nPedido recebido e CONCLUÍDO!");
                return;

            } else if (resposta.equals("N")) {
                System.out.println("\nVoltando à lista de pedidos...");
                return;

            } else {
                System.out.println("Opção inválida. Digite 'S' ou 'N'.");
            }
        }
    }

    private void exibirDetalhesEConfirmar(Pedido pedidoSelecionado) {
        exibirDetalhes(pedidoSelecionado);
        Confirmar(pedidoSelecionado);
    }

    private void pedidospreparo() {

        List<Pedido> preparo = pedidoController.listarEmPreparo();

        System.out.println("\n===PEDIDOS Em PREPARO===\n");

        int indiceExibicao = 0;

        for (Pedido p : preparo) {

            indiceExibicao++;

            System.out.printf(" [%d] Pedido #%d | Total: R$ %.2f%n",
                    indiceExibicao, p.getId(), p.getValorTotal());

            if (!p.getItens().isEmpty()) {

                System.out.printf("Itens: %s e mais...\n", p.getItens().get(0));
            } else {
                System.out.println("Itens: Sem itens registrados.");
            }
        }

        if (preparo.isEmpty()) {
            System.out.println("Nenhum pedido em Preparo.\n");
        }

        selecionarPedido(preparo, false);
    }

    private void pedidoscaminho() {

        List<Pedido> emAndamento = pedidoController.listarEmEntrega();

        System.out.println("\n===PEDIDOS À CAMINHO===\n");

        int indiceExibicao = 0;

        for (Pedido p : emAndamento) {

            indiceExibicao++;

            System.out.printf(" [%d] Pedido #%d | Total: R$ %.2f%n",
                    indiceExibicao, p.getId(), p.getValorTotal());

            if (!p.getItens().isEmpty()) {
                System.out.printf("Itens: %s e mais...\n", p.getItens().get(0));
            } else {
                System.out.println("Itens: Sem itens registrados.");
            }
        }

        if (emAndamento.isEmpty()) {
            System.out.println("Nenhum pedido em andamento.\n");
        }

        selecionarPedido(emAndamento, true); // habilita confirmação
    }


    private void pedidospendentes() {

        List<Pedido> pendentes = pedidoController.listarPendentes();

        System.out.println("\n=== PEDIDOS PENDENTES ===\n");

        int indiceExibicao = 0;

        for (Pedido p : pendentes) {

            indiceExibicao++;

            System.out.printf(" [%d] Pedido #%d | Total: R$ %.2f%n",
                    indiceExibicao, p.getId(), p.getValorTotal());

            if (!p.getItens().isEmpty()) {
                System.out.printf("Itens: %s e mais...\n", p.getItens().get(0));
            } else {
                System.out.println("Itens: Sem itens registrados.");
            }
        }

        if (pendentes.isEmpty()) {
            System.out.println("Nenhum pedido pendente.\n");
        }

        selecionarPedido(pendentes, false);
    }

    private void selecionarPedido(List<Pedido> lista, boolean permitirConfirmar) {

        if (lista.isEmpty()) return;

        while (true) {

            System.out.println("\n------------------------------------");
            System.out.println("Digite o NÚMERO do pedido para ver detalhes.");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");

            try {
                String line = sc.nextLine().trim();
                int escolha = line.isEmpty() ? -1 : Integer.parseInt(line);

                if (escolha == 0) return;

                if (escolha > 0 && escolha <= lista.size()) {
                    Pedido pedidoSelecionado = lista.get(escolha - 1);

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


    private void pedidosfinalizados() {

        System.out.println("\n===PEDIDOS CONCLUÍDOS===\n");

        List<Pedido> pedidosprontos = pedidoController.listarProntos();

        while (true) {

            for (Pedido p : pedidosprontos) {
                System.out.println(p);
            }

            System.out.print("\n\nDigite 0 para voltar: ");

            try {

                String line = sc.nextLine().trim();
                int resposta = line.isEmpty() ? -1 : Integer.parseInt(line);

                if (resposta == 0) return;

                System.out.println("Opção inválida.");

            }

            catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas 0.");
            }
        }
    }
}