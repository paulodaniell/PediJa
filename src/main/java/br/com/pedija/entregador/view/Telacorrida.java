package br.com.pedija.entregador.view;

import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Entregador;

import java.util.List;
import java.util.Scanner;

public class Telacorrida {

    private PedidoDAO pedidoDAO;
    private Entregador entregador;
    private Scanner sc;

    public Telacorrida(Entregador entregador) {
        this.pedidoDAO = new PedidoDAO();
        this.entregador = entregador;
        this.sc = new Scanner(System.in);
    }

    public void corrida(Pedido pedido) {
        if (pedido == null || pedido.getStatus() == null) {
            System.out.println("Pedido inválido ou sem status.");
            return;
        }

        System.out.println("\n=== DETALHES DA CORRIDA ===");
        exibirDetalhesPedido(pedido);

        String status = pedido.getStatus();

        switch (status) {
            case "EM ENTREGA":
                System.out.println("Status: EM ENTREGA");
                System.out.println("Aguardando o consumidor confirmar a entrega...");
                break;

            case "ENTREGUE":
                System.out.println("Status: ENTREGUE");
                System.out.println("PARABÉNS! Entrega realizada com sucesso.");
                break;

            default:
                System.out.println("Status do pedido: " + status);
                break;
        }

        System.out.print("\nDeseja voltar para o menu inicial? (S/N): ");
        String opcao = sc.nextLine().trim();
        if (opcao.equalsIgnoreCase("S")) {
            TelaInicialEntregador telaInicial = new TelaInicialEntregador(entregador);
            telaInicial.TelaInicioEntregador();
        }
    }

    private void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getId());
        System.out.println("Nome do Cliente: " + pedido.getNomeCliente());
        System.out.println("Endereço: " + pedido.getEndereco());
        System.out.println("Forma de pagamento: " + pedido.getFormaPagamento());
        System.out.printf("Valor Total: R$ %.2f%n", pedido.getValorTotal());

        List<?> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            System.out.println("Itens: Nenhum item registrado.");
        } else {
            System.out.println("Itens do pedido:");
            int i = 1;
            for (var item : itens) {
                // assumindo que item é do tipo Produto
                System.out.printf(" %d - %s (R$ %.2f)%n", i++, ((br.com.pedija.superadm.model.Produto) item).getNome(),
                        ((br.com.pedija.superadm.model.Produto) item).getPreco());
            }
        }
        System.out.println("---------------------------");
    }
}
