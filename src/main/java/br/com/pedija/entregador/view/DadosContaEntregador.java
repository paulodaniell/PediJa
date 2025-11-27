package br.com.pedija.entregador.view;

import br.com.pedija.entregador.controller.EntregadorController;
import br.com.pedija.superadm.model.Entregador;
import java.util.Scanner;

public class DadosContaEntregador {

    private final Entregador entregadorLogado;
    private final Scanner sc;
    private final EntregadorController controller;

    public DadosContaEntregador(Entregador entregadorLogado, Scanner sc, EntregadorController controller) {
        this.entregadorLogado = entregadorLogado;
        this.sc = sc;
        this.controller = controller;
    }

    public void exibirDados() {

        System.out.println("===== DADOS DA CONTA =====");

        System.out.println("Nome: " + entregadorLogado.getNome());
        System.out.println("Email: " + entregadorLogado.getEmail());
        System.out.println("Telefone: " + entregadorLogado.getTelefone());
        System.out.println("CPF: " + entregadorLogado.getCpf());
        System.out.println("Veículo: " + entregadorLogado.getVeiculo());
        System.out.println("Disponível: " + (entregadorLogado.isDisponivel() ? "Sim" : "Não"));

        while (true) {
            System.out.println("\nQuer alterar algum dado? (1 - Sim, 2 - Não)");
            System.out.print("Escolha: ");

            int opcao = -1;
            try {
                String line = sc.nextLine().trim();
                opcao = line.isEmpty() ? -1 : Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite 1 ou 2.");
                continue;
            }

            switch (opcao) {
                case 1:
                  executarEdicao();
                  break;

                case 2:
                    System.out.println("Voltando...");
                    return;

                default:
                    System.out.println("Digite um valor válido (1 ou 2)!");
                    break;
            }
        }
    }

    private boolean executarEdicao() {
        System.out.println("\n=== EDIÇÃO DE DADOS ===");
        System.out.println("1 - Nome (" + entregadorLogado.getNome() + ")");
        System.out.println("2 - Email (" + entregadorLogado.getEmail() + ")");
        System.out.println("3 - Telefone (" + entregadorLogado.getTelefone() + ")");
        System.out.println("0 - Cancelar Edição");
        System.out.print("Qual dado quer alterar? ");

        int dado;
        try {
            String line = sc.nextLine().trim();
            dado = line.isEmpty() ? -1 : Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite apenas o número.");
            return false;
        }

        if (dado == 0) {
            System.out.println("Edição cancelada.");
            return false;
        }

        if (dado < 1 || dado > 3) {
            System.out.println("Opção de dado inválida!");
            return false;
        }

        System.out.print("Digite o novo valor: ");
        String novoValor = sc.nextLine().trim();

        if (novoValor.isEmpty()) {
            System.out.println("Valor não pode ser vazio. Edição cancelada.");
            return false;
        }

        switch (dado) {
            case 1 -> entregadorLogado.setNome(novoValor);
            case 2 -> entregadorLogado.setEmail(novoValor);
            case 3 -> entregadorLogado.setTelefone(novoValor);
        }


        controller.atualizarEntregador(entregadorLogado);

        return true;
    }
}