package br.com.pedija.consumidor.controller;

import java.util.Scanner;

public class PagamentoController {

    public PagamentoController(){
    }

    public String pagamento(Scanner sc) {
        while (true) {
            System.out.println("\nQual a forma de pagamento?");
            System.out.println("[1] PIX");
            System.out.println("[2] CARTÃO DE CRÉDITO");
            System.out.println("[3] CARTÃO DE DÉBITO");
            System.out.println("[4] DINHEIRO");
            System.out.println("[0] Voltar");
            System.out.print("Escolha: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Digite uma opção.\n");
                continue;
            }

            int op;
            try {
                op = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números!\n");
                continue;
            }

            switch (op) {
                case 1: return "PIX";
                case 2: return "Cartão de Crédito";
                case 3: return "Cartão de Débito";
                case 4: return "Dinheiro";
                case 0: return null;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }
}
