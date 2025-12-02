package br.com.pedija.consumidor.view.perfil;

import br.com.pedija.consumidor.controller.PedidoController;
import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Usuario;
import java.util.Scanner;


public class TelaPerfil {

    private final Scanner sc;
    private final Usuario usuarioLogado;
    private final UsuarioController controller;
    private final PedidoController pedidoController;

    public TelaPerfil(Usuario usuarioLogado, UsuarioController controller, PedidoController pedidoController) {

        this.sc = new Scanner(System.in);
        this.usuarioLogado = usuarioLogado;
        this.controller = controller;
        this.pedidoController = pedidoController;
    }

    public void verPerfil() {

        int opcao = -1;

        do {
            System.out.println("\n1 - Dados da Conta           ");
            System.out.println("2 - Pagamentos               ");
            System.out.println("0 - voltar                   ");

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

    private void resultadoOpcao(int opcao) {
        switch (opcao) {

            case 1:
                System.out.println("Dados da Conta");
                DadosConta dadosContaView = new DadosConta(this.usuarioLogado, this.sc, this.controller);
                dadosContaView.exibirDados();
                break;

            case 2:
                System.out.println("Pagamentos da Conta");
                PagamentosConta pagamentosView = new PagamentosConta(this.usuarioLogado, this.sc, this.pedidoController);
                pagamentosView.exibir();
                break;

            case 0:
                return;

            default:System.out.println("Opção inválida!");
                break;

        }
    }
}