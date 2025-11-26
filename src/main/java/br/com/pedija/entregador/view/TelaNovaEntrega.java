package br.com.pedija.entregador.view;

import br.com.pedija.superadm.dao.PedidoDAO;
import br.com.pedija.superadm.model.Pedido;

import java.util.Scanner;

public class TelaNovaEntrega {

    Telacorrida telacorrida = new Telacorrida();
    PedidoDAO pedidoDAO = new PedidoDAO();

    Scanner sc = new Scanner(System.in);

    public void novaentrega() {

        System.out.println("---------Você está disponível para novas entregas-----------------\n");

        int opcao = 0;


    System.out.println("Temos corridas para você!");

    System.out.println(pedidoDAO.buscarPorStatus("PRONTO"));

    System.out.println("Deseja aceitar algum pedido: (1 - Sim) ( 0 - Não)");
    int aceitar = sc.nextInt();

    if (aceitar == 1) {

        System.out.println("Digite o id do pedido que quer aceitar: ");
        int id = sc.nextInt();
        Pedido pedidoaceito = new Pedido();
        pedidoaceito.setId(id);

        System.out.println("Tem certeza que deseja aceitar: (1  - Sim) (2 - Não)");

        switch (opcao) {
            case 1:

                System.out.println("Corrida Iniciada! \n");
                pedidoaceito.setStatus("EM ENTREGA");
                telacorrida.corrida();
                break;

            case 2:
                System.out.println("Voltando...");
                break;
        }

        }

    else {return;}

    }

    }

