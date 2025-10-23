package br.com.pedija.entregador.view;

import java.util.Scanner;

public class TelaNovaEntrega {

    Telacorrida telacorrida = new Telacorrida();

    Scanner sc = new Scanner(System.in);

    public void novaentrega() {

        System.out.println("---------Você está disponível para novas entregas-----------------\n");

        int opcao = 0;


    System.out.println("Temos uma entrega para você!");

    System.out.println("Valor: 13,98");
    System.out.println("De: Sushi Loko");
    System.out.println("Para: Setor O");

    System.out.println("Aceitar a rota? (1- Sim) (2 - Não)");
    opcao = sc.nextInt();

    switch (opcao) {
        case 1:

            System.out.println("Corrida Iniciada! \n");
            telacorrida.corrida();
            break;

        case 2:
            System.out.println("Voltando...");
            break;
    }












    }
}
