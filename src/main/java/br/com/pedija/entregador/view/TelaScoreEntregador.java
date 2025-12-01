package br.com.pedija.entregador.view;

import java.util.Scanner;

public class TelaScoreEntregador {
    public void scoreview() {

        TelaMaisEntregador maisEntregador = new TelaMaisEntregador();

        int corridas = 0;
        int voltar = 0;

        System.out.println("Seu número de corridas até o momento: " + corridas);

        System.out.println("Faça novas corridas para obter prioridade na sua região!");

        System.out.println("Voltar (1 - Sim)");
        Scanner sc = new Scanner(System.in);
        voltar = sc.nextInt();

        if (voltar == 1) {
            maisEntregador.mais();

        }

    }
}