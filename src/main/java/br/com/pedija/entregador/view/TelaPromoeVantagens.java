package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaPromoeVantagens {

    private Entregador entregador;

    public TelaPromoeVantagens(Entregador entregador) {
        this.entregador = entregador;
    }

    public void promoview() {

        TelaMaisEntregador maisEntregador = new TelaMaisEntregador(entregador);


        System.out.println("Pedi Já pedal");
        System.out.println("Loja do entregador");
        System.out.println("Seguro Moto");
        System.out.println("Seguro Pessoal\n");
        System.out.println("Quer saber mais acesse https://entregador.ifood.com.br/");

        System.out.println("Voltar (1 - Sim)");
        Scanner sc = new Scanner(System.in);
        int voltar = sc.nextInt();

        if (voltar == 1) {
            maisEntregador.mais();
        }
    }
}
