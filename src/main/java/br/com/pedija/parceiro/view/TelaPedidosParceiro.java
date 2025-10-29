package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.PedidoController;
import br.com.pedija.parceiro.model.Parceiro;
import br.com.pedija.parceiro.model.Pedido;


import java.util.List;
import java.util.Scanner;

public class TelaPedidosParceiro {

    private Scanner sc = new Scanner(System.in);
    private Parceiro parceiro;
    private PedidoController pedidoController;

    public TelaPedidosParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.pedidoController = new PedidoController();
    }

    public void exibirPedidos() {
        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println("\nPedidos" + parceiro.getNome());
            System.out.println("------------------------------");

            int pendentes = pedidoController.contarPorStatus(parceiro.getId(), "PENDENTE");
            int emPreparo = pedidoController.contarPorStatus(parceiro.getId(), "EM_PREPARO");
            int prontos = pedidoController.contarPorStatus(parceiro.getId(), "PRONTOS");

            System.out.println("1- Pedidos Pendentes: (" + pendentes + ")");
            System.out.println("2- Pedidos em Preparo: (" + emPreparo + ")");
            System.out.println("3- Pedidos Prontos: (" + prontos + ")");
            System.out.println("4- Histórico:");
            System.out.println("0- Voltar");

            System.out.println("\nEscolha uma opção: ");


            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOpc(opcao);
            } catch (Exception erro) {
                System.out.println("Erro! Figite apenas número!");
                sc.nextLine();
            }


        } while (opcao != 0);
    }

    private void resultadoOpc(int opcao) {

        switch (opcao) {
            case 1:
                exibirPendentes();
                break;

            case 2:
                exibirEmPreparo();
                break;

            case 3:
                exibirProntos();

                break;
            case 4:
                exibirHistorico();
                break;

            case 0:
                System.out.println("Voltando..");
                break;

            default:
                System.out.println("Opção inválida!");
                break;

        }
    }

    private void exibirPendentes() {
        System.out.println("---------------------");
        System.out.println("PEDIDOS PENDENTES:");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarPorParceiroEStatus(parceiro.getId(), "PENDENTE");

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido em preparo");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("\n PEDIDO #" + p.getId());
            System.out.println("---------------------");
            System.out.println("Clinte: "+p.getNomeCliente());
            System.out.println("Valor: R$"+String.format("%.2f",p.getValoTotal()));
            System.out.println("Pagamento: "+p.getFormaPagamento());
            System.out.println("\nitens:");

            for(var item : p.getItens()){
                System.out.println(" ."+item.getQuantidade()+"x"+item.getNomeProduto()+"- R$"+String.format("%.2f",item.getSubTotal()));

            }
            System.out.println("---------------------");
        }

        System.out.println("\n[1] Aceitar Pedido");
        System.out.println("[2] Rejeitar Pedido");
        System.out.println("[3] Voltar");

        System.out.println("Escolha uma opcao: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {
            System.out.println("ID do Pedido:");
            int id = sc.nextInt();
            sc.nextLine();
            aceitarPedido(id);
        }else if (opcao == 2) {
            System.out.println("ID do Pedido:");
            int id = sc.nextInt();
            sc.nextLine();
            rejeitarPedido(id);
        }
    }
    public void exibirEmPreparo(){
        System.out.println("---------------------");
        System.out.println("PEDIDOS EM PREPARO:");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarPorParceiroEStatus(parceiro.getId(), "EM_PREPARO");

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido em preparo");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("\nPedido #" + p.getId());
            System.out.println("----------------------");
            System.out.println("Clinte: "+p.getNomeCliente());
            System.out.println("Valor: R$"+String.format("%.2f",p.getValoTotal()));
            System.out.println("----------------------");
        }
        System.out.println("\n[1] Marcar como Pronto");
        System.out.println("[0] Voltar ");

        System.out.println("Escolha uma opcao: ");
        int opcao = sc.nextInt();
        sc.nextLine();
        if (opcao == 1) {
            System.out.println("ID do Pedido:");
            int id = sc.nextInt();
            sc.nextLine();
            marcarComoPronto(id);
        }

    }
    private void exibirProntos(){
        System.out.println("---------------------");
        System.out.println("PEDIDOS PRONTOS:");
        System.out.println("---------------------");

        List<Pedido> pedidos = pedidoController.listarPorParceiroEStatus(parceiro.getId(), "PRONTOS");

        if (pedidos.isEmpty()) {
            System.out.println("\nNenhum pedido pronto no momento");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("\n PEDIDO #" + p.getId());
            System.out.println("----------------------");
            System.out.println("Clinte: "+p.getNomeCliente());
            System.out.println("Valor: R$"+String.format("%.2f",p.getValoTotal()));
            System.out.println("Aguardando entregador...");
            System.out.println("----------------------");
        }
    }
    private void exibirHistorico() {
        System.out.println("---------------------");
        System.out.println("HISTÓRICO DE PEDIDOS");
        System.out.println("---------------------");

        List<Pedido> entregues = pedidoController.listarPorParceiroEStatus(parceiro.getId(), "ENTREGUE");
        System.out.println("\n Total de pedidos entregues: " + entregues.size());
        if (!entregues.isEmpty()) {
            System.out.println("\nÚltimos pedidos:");
            int limite = Math.min(10, entregues.size());//so mostra 10 pedidos ou menos
            for (int i = 0; i < limite; i++) {
                Pedido p = entregues.get(i);
                System.out.println("  #" + p.getId() + " - " + p.getNomeCliente() + " - R$ " + String.format("%.2f", p.getValoTotal()));
            }
        }
    }
        private void aceitarPedido(int id){
            System.out.println("\nTEmpo de preparo (minutos): ");
            int tempo = sc.nextInt();
            sc.nextLine();

        if (pedidoController.atualizarStaus(id,"EM_PREPARO")){
            System.out.println("\nPedido #"+ id + "aceito!");
            System.out.println("Tempo de preparo: " + tempo + "minutos");
        }else{
            System.out.println("\nErro ao aceitar pedido!");
        }

    }

        private void rejeitarPedido(int id) {
            System.out.println("\nMotivo da rejeição:");
            System.out.println("[1] Fora da área de entrega");
            System.out.println("[2] Produto indisponivel");
            System.out.println("[3] Outro");

            System.out.println("Escolha:");
            int opcao = sc.nextInt();
            sc.nextLine();

            String motivo = "";
            switch (opcao) {
                case 1:
                    motivo = "Fora da área de entrega";
                    break;
                case 2:
                    motivo = "Produto indisponível";
                    break;
                case 3:
                    System.out.print("Digite o motivo: ");
                    motivo = sc.nextLine();
                    break;
            }
            if (pedidoController.atualizarStaus(id, "REJEITADO")) {
                System.out.println("\nPedido #" + id + "rejeitado!");
                System.out.println("Motivo: " + motivo);
            }
        }
            private void marcarComoPronto(int id){
                if(pedidoController.atualizarStaus(id,"PRONTO")){
                    System.out.println("\nPedido #"+ id + "está PRONTO");
                    System.out.println("Aguardando entregador...");

                }else{
                    System.out.println("\nErro ao atualizar pedido!");
                }
            }

}