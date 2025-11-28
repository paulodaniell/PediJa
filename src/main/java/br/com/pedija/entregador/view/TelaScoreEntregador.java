package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaScoreEntregador {


    private Entregador entregador;

    public TelaScoreEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void scoreview() {

        TelaMaisEntregador maisEntregador = new TelaMaisEntregador(entregador);

        int corridas = 0;

        System.out.println("Seu número de corridas até o momento: " + corridas);
        System.out.println("Faça novas corridas para obter prioridade na sua região!");

        System.out.println("Voltar (1 - Sim)");
        Scanner sc = new Scanner(System.in);
        int voltar = sc.nextInt();

        if (voltar == 1) {
            maisEntregador.mais();
        }
    }
}
