package br.com.pedija.entregador.view;

import java.util.Scanner;
import br.com.pedija.entregador.model.Entregador;

public class TelaLoginEntregador {


    public void telaLoginview() {

        TelaInicialEntregador telainicial = new TelaInicialEntregador();

        Entregador entregador = new Entregador();

        Scanner sc = new Scanner(System.in);

        System.out.println("==========TelaLoginEntregador==========\n\n");

        System.out.println("Já tem login? (1 - Sim) (2 - Não)");
        int opcao = sc.nextInt();

        if  (opcao == 1) {

            System.out.println("Digite seu CPF: ");
            entregador.setCpf(sc.nextInt());

            System.out.println("Digite sua senha: ");
            entregador.setSenha(sc.next());

            telainicial.TelaInicioEntregador();

        }

        else if (opcao == 2) {


            System.out.println("Digite seu nome: ");
            entregador.setNome(sc.next());

            System.out.println("Digite seu CPF: ");
            entregador.setCpf(sc.nextInt());

            System.out.println("Digite seu email: ");
            entregador.setEmail(sc.next());

            System.out.println("Digite seu telefone: ");
            entregador.setTelefone(sc.nextInt());

            System.out.println("Digite como quer receber (Pix/Dinheiro/Cartão): ");
            entregador.setFormaPagamento(sc.next());

            telainicial.TelaInicioEntregador();


        }

    }

}
