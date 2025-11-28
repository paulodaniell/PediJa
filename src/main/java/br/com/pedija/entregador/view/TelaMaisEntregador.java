package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class TelaMaisEntregador {

    private Entregador entregador;
    private Scanner sc = new Scanner(System.in);

    public TelaMaisEntregador(Entregador entregador) {
        this.entregador = entregador;
    }


    public TelaMaisEntregador() {}

    public void mais() {
        int opcao;

        do {
            System.out.println("-----------------------------------");
            System.out.println(" 1 - Seu Score");
            System.out.println(" 2 - Promoções e vantagens");
            System.out.println(" 3 - Perfil");
            System.out.println(" 0 - Voltar");
            System.out.println("-----------------------------------");
            System.out.print("\nEscolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    new TelaScoreEntregador(entregador).scoreview();
                    break;

                case 2:
                    new TelaPromoeVantagens(entregador).promoview();
                    break;

                case 3:
                    new TelaPerfilEntregador(entregador).verPerfil();
                    break;
            }

        } while (opcao != 0);
    }
}
