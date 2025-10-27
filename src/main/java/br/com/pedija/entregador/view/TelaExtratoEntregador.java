package br.com.pedija.entregador.view;

import java.util.Scanner;

public class TelaExtratoEntregador {

    public void extrato() {

        TelaInicialEntregador tela = new TelaInicialEntregador();

        double ganhoSemanal = 1300;
        System.out.println("Ganho da semana: " + ganhoSemanal);

        double ganhoDiario = 100;
        System.out.println("Ganho da semana: " + ganhoDiario);

        System.out.println("Voltar (1 - Sim)");
        Scanner sc = new Scanner(System.in);
        int voltar = sc.nextInt();

        if (voltar == 1) {
           tela.TelaInicioEntregador();

        }


    }
}
