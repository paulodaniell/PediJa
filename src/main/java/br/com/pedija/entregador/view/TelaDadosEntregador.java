package br.com.pedija.entregador.view;

import java.util.Scanner;
import br.com.pedija.superadm.model.Entregador;

public class TelaDadosEntregador {
    public void dadosview() {

        TelaPerfilEntregador telaPerfil = new TelaPerfilEntregador();

        Entregador entregador = new Entregador();

        Scanner sc = new Scanner(System.in);

        System.out.println("Dados Pessoais:");
        System.out.println("1 - CPF: " + entregador.getCpf());
        System.out.println("2 - Telefone: " + entregador.getTelefone());
        System.out.println("3 - Email:  " + entregador.getEmail());
        System.out.println("4 - Contato de Emerência: " + entregador.getContatoDeEmergencia());
        System.out.println("5 - Nome Contato de emergência: " + entregador.getNomeEmergencia() + "\n");


        System.out.println("Deseja mudar algum dado do seu perfil? (1 - Sim) (2 - Não)");
        int mudar = sc.nextInt();
        sc.nextLine();

        if (mudar == 1) {

            System.out.println("Qual dado quer mudar? Digite pelo numero");
            int numdado = sc.nextInt();
            sc.nextLine();

            switch (numdado) {

                case 1:
                    System.out.println("CPF: ");
                    String novoCpf = sc.nextLine();
                    sc.nextLine();
                    entregador.setCpf(novoCpf);
                    break;

                case 2:
                    System.out.println("Telefone: ");
                    int novoTelefone = sc.nextInt();
                    sc.nextLine();
                    entregador.setTelefone(novoTelefone);
                    break;

                case 3:
                    System.out.println("Email: ");
                    String novoEmail = sc.nextLine();
                    entregador.setEmail(novoEmail);
                    break;

                case 4:
                    System.out.println("Contato de emergencia: ");
                    int novoContato = sc.nextInt();
                    sc.nextLine();
                    entregador.setContatoDeEmergencia(novoContato);
                    break;

                case 5:
                    System.out.println("Nome do contato: ");
                    String novoNome = sc.nextLine();
                    entregador.setNomeEmergencia(novoNome);
                    break;

                default:
                    break;

            }
        }

        else {
                telaPerfil.verPerfil();

        }
        }
    }