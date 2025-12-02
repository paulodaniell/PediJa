package br.com.pedija.consumidor.view.perfil;

import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Usuario;
import java.util.Scanner;

public class DadosConta {

    private final Usuario usuarioLogado;
    private final Scanner sc;
    private final UsuarioController controller;

    public DadosConta(Usuario usuarioLogado, Scanner sc, UsuarioController controller) {
        this.usuarioLogado = usuarioLogado;
        this.sc = sc;
        this.controller = controller;
    }

    public void exibirDados() {


        System.out.println("===== DADOS DA CONTA =====");

        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Telefone: " + usuarioLogado.getTelefone());
        System.out.println("CPF: " + usuarioLogado.getCpf());
        System.out.println("Endereço: " + usuarioLogado.getEndereco());


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
                    if (executarEdicao()) {
                        return;
                    }
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
        System.out.println("1 - Nome (" + usuarioLogado.getNome() + ")");
        System.out.println("2 - Email (" + usuarioLogado.getEmail() + ")");
        System.out.println("3 - Telefone (" + usuarioLogado.getTelefone() + ")");
        System.out.println("4 - Endereço (" + usuarioLogado.getEndereco() + ")");
        System.out.println("5 - CPF: (" + usuarioLogado.getCpf() + ")");
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

        if (dado < 1 || dado > 4) {
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
            case 1:
                usuarioLogado.setNome(novoValor);
                break;
            case 2:
                usuarioLogado.setEmail(novoValor);
                break;
            case 3:
                usuarioLogado.setTelefone(novoValor);
                break;
            case 4:
                usuarioLogado.setEndereco(novoValor);
                break;
            case 5:
                usuarioLogado.setCpf(novoValor);
        }

        controller.atualizarUsuario(usuarioLogado);
        return true;
    }
}