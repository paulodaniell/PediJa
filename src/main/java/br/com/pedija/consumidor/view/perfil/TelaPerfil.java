package br.com.pedija.consumidor.view.perfil;

import br.com.pedija.consumidor.view.perfil.DadosConta;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Usuario;
import java.util.Scanner;


public class TelaPerfil {

    DadosConta dadosConta = new DadosConta();
    private final Scanner sc;
    private final Usuario usuarioLogado;
    private final UsuarioController controller;

    public TelaPerfil(Usuario usuarioLogado, UsuarioController controller) {

        this.sc = new Scanner(System.in);
        this.usuarioLogado = usuarioLogado;
        this.controller = controller;
    }

    public void verPerfil() {

        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println(" 1 - Dados da Conta           ");
            System.out.println(" 2 - Pagamentos               ");
            System.out.println(" 3 - Endereços                ");
            System.out.println(" 0 - voltar                   ");
            System.out.println("------------------------------");

            System.out.print("\nEscolha uma opção: ");

            try {

                String line = sc.nextLine().trim();
                opcao = line.isEmpty() ? -1 : Integer.parseInt(line);
                if (opcao != -1) {
                    resultadoOpcao(opcao);
                }

            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }


        } while (opcao != 0);

    }
    //So opção 1 com metodo
    private void resultadoOpcao(int opcao) {
        switch (opcao) {

            case 1:
                System.out.println("Dados da Conta");
                dadosConta.exibirDados();
                break;

            case 2:
                System.out.println("Pagamentos da Conta");
                break;

            case 3:
                System.out.println("Endereços");
                break;

            case 0:
                return;

            default:System.out.println("Opção inválida!");
                break;

        }
    }
}