package br.com.pedija.entregador.view;

import java.util.Scanner;

public class Telacorrida {
    public void corrida() {

        Scanner sc = new Scanner(System.in);

        int codigoParceiro;

        System.out.println("Digite o código do parceiro: ");
        codigoParceiro = sc.nextInt();

        System.out.println("Código correto " +  codigoParceiro + "\n");
        System.out.println("Vá para o local de entrega para finalizar a corrida \n");

        int codigoCliente;

        System.out.println("Digite o código do cliente: ");
        codigoCliente = sc.nextInt();

        System.out.println("Código correto " +  codigoCliente + "\n");

    System.out.println("PARABÉNS ENTREGA REALIZADA COM SUCESSO");


    }
}
