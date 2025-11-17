package br.com.pedija.consumidor.view;


import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.superadm.model.Usuario;
import br.com.pedija.superadm.model.Pedido;
import java.util.List;
import java.util.Scanner;


public class TelaPedidos {


    private final PedidoController pedidoController;
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
            System.out.println("[1] PEDIDOS Á CAMINHO:");
            System.out.println("[2] PEDIDOS CONCLUIDOS:");
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
                pedidoscaminho();
                break;
            case 2:
                pedidosfinalizados();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }


    private void pedidoscaminho() {


        char resposta;
        System.out.println("\n===PEDIDOS Á CAMINHO===\n");


       /*Aqui é para aparecer os pedidos listados por ordem
       aparecendo o valor total do pedido e o nome dos produtos pedidos inicialmente.
       Depois que clicar no pedido. É para a aparecer as mesmas informações que a apareceram
       lá no carrinho (do mesmo jeito). Daí vai aparecer essa mensagem abaixo
        */


        System.out.println("Caso tenha recebido seu pedido, confirme:");
        System.out.println("[S]");
        System.out.println("[N]");
    }


    private void listarPedidosPorStatus(List<Pedido> pedidos, String statusFiltro) {
        int contador = 0;


        for (Pedido p : pedidos) {
            // Verifica o status
            if (p.getStatus() != null && p.getStatus().equalsIgnoreCase(statusFiltro)) {
                contador++;
                System.out.printf(" [%d] Pedido #%d | Total: R$ %.2f%n", contador, p.getId(), p.getValorTotal());


                // Exibe o nome do primeiro produto (resumo)
                if (!p.getItens().isEmpty()) {
                    System.out.printf("     Itens: %s e mais...%n", p.getItens().get(0).getNome());
                } else {
                    System.out.println("     Itens: Sem itens registrados.");
                }
            }
        }


        if (contador == 0) {
            System.out.println("Nenhum pedido encontrado neste status.");
        }
    }




    private void pedidosfinalizados() {


        System.out.println("\n===PEDIDOS CONCLUIDOS===\n");




        List<Pedido> pedidosDoUsuario = pedidoController.listarPorUsuario(usuarioLogado.getId());
       /*Aqui é para aparecer os pedidos finalizados listados por ordem
       aparecendo o valor total do pedido e o nome dos produtos pedidos.
       Apenas isso.
        */


        while(true) {


            System.out.print("Digite 0 para voltar: ");


            try {
                String line = sc.nextLine().trim();
                int resposta = line.isEmpty() ? -1 : Integer.parseInt(line);


                if (resposta == 0) {
                    return;
                }
                System.out.println("Opção inválida.");


            }
            catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas 0.");
            }


        }


    }
}

