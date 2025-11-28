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

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                continue;
            }

            switch (opcao) {
                case 1 -> executarEdicao();
                case 2 -> {
                    System.out.println("Voltando...");
                    return;
                }
                default -> System.out.println("Digite 1 ou 2!");
            }
        }
    }

    private void executarEdicao() {

        System.out.println("\n=== EDIÇÃO DE DADOS ===");
        System.out.println("1 - Nome");
        System.out.println("2 - Email");
        System.out.println("3 - Telefone");
        System.out.println("0 - Cancelar");
        System.out.print("Escolha: ");

        int opcao;
        try {
            opcao = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Opção inválida.");
            return;
        }

        if (opcao == 0) {
            System.out.println("Cancelado.");
            return;
        }

        System.out.print("Novo valor: ");
        String novo = sc.nextLine().trim();

        switch (opcao) {
            case 1 -> entregadorLogado.setNome(novo);
            case 2 -> entregadorLogado.setEmail(novo);
            case 3 -> entregadorLogado.setTelefone(novo);
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        controller.atualizarEntregador(entregadorLogado);
        System.out.println("Atualizado com sucesso!");
    }
}
