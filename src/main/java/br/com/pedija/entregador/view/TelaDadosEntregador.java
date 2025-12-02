package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Entregador;
import java.util.List;
import java.util.Scanner;

public class TelaDadosEntregador {

    private Entregador entregador;

    public TelaDadosEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public void exibirDados() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\nDados do Entregador:");
        System.out.println("1 - ID: " + entregador.getId());
        System.out.println("2 - Nome: " + entregador.getNome());
        System.out.println("3 - Email: " + entregador.getEmail());
        System.out.println("4 - Senha: " + entregador.getSenha());
        System.out.println("5 - Telefone: " + entregador.getTelefone());
        System.out.println("6 - CPF: " + entregador.getCpf());
        System.out.println("7 - Veículo: " + entregador.getVeiculo());
        System.out.println("8 - Disponível: " + (entregador.isDisponivel() ? "Sim" : "Não"));
        System.out.println("9 - Formas de Pagamento: " + entregador.getFormasPagamento());
        System.out.println("10 - Contato de Emergência: " + entregador.getContatoDeEmergencia());
        System.out.println("11 - Nome do Contato de Emergência: " + entregador.getNomeEmergencia());
        System.out.println();

        System.out.println("Deseja mudar algum dado? (1 - Sim / 2 - Não)");
        int mudar = sc.nextInt();
        sc.nextLine(); // limpar buffer

        if (mudar == 1) {
            System.out.println("Qual dado deseja mudar? (Digite o número correspondente)");
            int numdado = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (numdado) {
                case 1:
                    System.out.print("Novo ID: ");
                    entregador.setId(sc.nextInt());
                    sc.nextLine();
                    break;

                case 2:
                    System.out.print("Novo Nome: ");
                    entregador.setNome(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Novo Email: ");
                    entregador.setEmail(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Nova Senha: ");
                    entregador.setSenha(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Novo Telefone: ");
                    entregador.setTelefone(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Novo CPF: ");
                    entregador.setCpf(sc.nextLine());
                    break;

                case 7:
                    System.out.print("Novo Veículo: ");
                    entregador.setVeiculo(sc.nextLine());
                    break;

                case 8:
                    System.out.print("Disponível? (1 - Sim / 0 - Não): ");
                    int disp = sc.nextInt();
                    sc.nextLine();
                    entregador.setDisponivel(disp == 1);
                    break;

                case 9:
                    System.out.println("Digite as formas de pagamento separadas por vírgula:");
                    String formas = sc.nextLine();
                    List<String> listaFormas = List.of(formas.split("\\s*,\\s*"));
                    entregador.setFormasPagamento(listaFormas);
                    break;

                case 10:
                    System.out.print("Novo Contato de Emergência: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Digite apenas números!");
                        sc.next(); // descarta entrada inválida
                    }
                    entregador.setContatoDeEmergencia(sc.nextInt());
                    sc.nextLine();
                    break;

                case 11:
                    System.out.print("Novo Nome do Contato de Emergência: ");
                    entregador.setNomeEmergencia(sc.nextLine());
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            System.out.println("Dados atualizados com sucesso!");
        }

        System.out.println("Voltando ao perfil...");
    }
}
