package br.com.pedija.entregador.view;


import java.util.Scanner;

public class TelaMaisEntregador {

    Scanner sc = new Scanner(System.in);

    public void mais() {

            Scanner sc = new Scanner(System.in);


                int opcao = 0;

                do {
                    System.out.println("-----------------------------------");
                    System.out.println(" 1 - Seu Score                     ");
                    System.out.println(" 2 - Promoções e vantagens         ");
                    System.out.println(" 3 - Perfil                        ");
                    System.out.println(" 0 - Voltar                        ");
                    System.out.println("-----------------------------------");

                    System.out.print("\nEscolha uma opção: ");

                    try {
                        opcao = sc.nextInt();
                        resultadoOpcao(opcao);

                    } catch (Exception error) {
                        System.out.println("Opção inválida!");
                        sc.nextLine();
                    }

                } while (opcao != 0);

            }

            private void resultadoOpcao(int opcao) {

            TelaScoreEntregador telascore = new TelaScoreEntregador();
            TelaPromoeVantagens telaspromoev = new TelaPromoeVantagens();
            TelaPerfilEntregador telaperfil = new TelaPerfilEntregador();


                switch (opcao) {
                    case 1:
                        System.out.println("Seu Score\n");
                        telascore.scoreview();
                        break;

                    case 2:
                        System.out.println("Promoções e Vantagens\n");
                        telaspromoev.promoview();
                        break;

                    case 3:
                        System.out.println("Perfil\n");
                        telaperfil.perfilview();

                        break;
                    case 0:
                        System.out.println("Voltando..");
                        break;

                    default:System.out.println("Opção inválida!");
                        break;


                }
            }
    }

