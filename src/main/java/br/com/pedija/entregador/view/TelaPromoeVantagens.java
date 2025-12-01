package br.com.pedija.entregador.view;

import java.util.Scanner;

public class TelaPromoeVantagens {
    public void promoview() {

        int voltar = 0;

        TelaMaisEntregador maisEntregador = new TelaMaisEntregador();

        System.out.println("Pedi Já pedal");
        System.out.println("Loja do entregador");
        System.out.println("Seguro Moto");
        System.out.println("Seguro Pessoal\n");
        System.out.println("Quer saber mais acesse https://entregador.ifood.com.br/");

        System.out.println("Voltar (1 - Sim)");
        Scanner sc = new Scanner(System.in);
        voltar = sc.nextInt();

        if (voltar == 1) {
            maisEntregador.mais();

        }
    }
}