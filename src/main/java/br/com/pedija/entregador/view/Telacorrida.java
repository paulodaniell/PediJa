package br.com.pedija.entregador.view;

import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;

public class Telacorrida {
    PedidoDAO pedidoDAO = new PedidoDAO();

    public void corrida(Pedido pedido) {


        String pedidostatus = pedido.getStatus();

        if (pedido.getStatus() == null) {
            System.out.println("Pedido invalido ou sem status.");
            return;
        }

        if (pedidostatus.equals("EM ENTREGA")) {
            System.out.println(pedidoDAO.buscarPorStatus("EM ENTREGA"));
            System.out.println("Esperando Consumidor Confirmar Entrega");
        }

        if (pedidostatus.equals("ENTREGUE")) {
            System.out.println(pedidoDAO.buscarPorStatus("ENTREGUE"));
            System.out.println("PARABÉNS ENTREGA REALIZADA COM SUCESSO");

            System.out.println("Deseja voltar para o menu inicial? (S/N)");
            String opcao = new java.util.Scanner(System.in).nextLine();
            if (opcao.equalsIgnoreCase("S")) {
                TelaInicialEntregador telaInicialEntregador = new TelaInicialEntregador();
                telaInicialEntregador.TelaInicioEntregador();
            }
        }
    }
}