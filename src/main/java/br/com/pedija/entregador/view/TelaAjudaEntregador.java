package br.com.pedija.entregador.view;

import br.com.pedija.entregador.model.Duvida;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaAjudaEntregador {

    ArrayList<Duvida> duvidas = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void ajuda() {

        CriarDuvida criarDuvida = new CriarDuvida();

        if (duvidas.isEmpty()) {

            System.out.println("Nenhum duvida registrada");
        }

        else {
            System.out.println("=== DÚVIDAS EXISTENTES ===");
            System.out.println(duvidas);
        }

        System.out.print("Encontrou a resposta sua dúvida? Ou quer registrar uma nova?");
        System.out.println("(1 - Registrar) (2 - Encontrei)");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
            System.out.println("Criar Duvida selecionada!");
            criarDuvida.criarduvida(duvidas);

            case 2:
                System.out.println("Voltando...");
                break;


        }
    }
}