package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.ParceiroController;
import br.com.pedija.parceiro.controller.ProdutoController;
import br.com.pedija.superadm.model.Parceiro;


import java.util.List;
import java.util.Scanner;

public class TelaPerfilParceiro {
    private Scanner sc;
    private Parceiro parceiro;
    private ProdutoController produtoController;
    private ParceiroController parceiroController;

    public TelaPerfilParceiro(Parceiro parceiro) {
        this.sc = new Scanner(System.in);
        this.parceiro = parceiro;
        this.produtoController = new ProdutoController();
        this.parceiroController = new ParceiroController();
    }

    public void exibirPerfilParceiro() {
        int opcao = -1;

        do {
            System.out.println("------------------------------");
            System.out.println("MINHA LOJA - " + parceiro.getNome());
            System.out.println("------------------------------");
            System.out.println(" 1 - Ver Informções e Dados");
            System.out.println(" 2 - Relatorio e Estatisticas");
            System.out.println(" 3 - Horario de Funcionamento");
            System.out.println(" 4 - Forma de Pagamento");
            System.out.println(" 0 - Voltar");
            System.out.println("------------------------------");
            System.out.print("Escolha uma opção: ");

            try {

                opcao = sc.nextInt();
                sc.nextLine();

                Opcao(opcao);

            } catch (Exception e) {
                System.out.println(" Entrada inválida!");
                sc.nextLine();

            }

        } while (opcao != 0);
    }

    private void Opcao(int opcao) {
        switch (opcao) {
            case 1:
                exibirInfoParceiro();
                break;
            case 2:
                TelaRelatorioParceiro telaRelatorio = new TelaRelatorioParceiro(parceiro);
                telaRelatorio.menuRelatorio();
                break;
            case 3:
                horarioFuncionamento();
                break;
            case 4:
                exibirFormasPagamento();
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println(" Opção inválida!");

        }
    }

    public void exibirInfoParceiro() {
        System.out.println("DADOS PESSOAIS");

        System.out.println("Nome: " + parceiro.getNome());
        System.out.println("CNPJ: " + parceiro.getCnpj());
        System.out.println("Email: " + parceiro.getEmail());
        System.out.println("Telefone: " + parceiro.getTelefone());
        System.out.println("Endereço Completo:\n Cidade: " + parceiro.getCidade() + " CEP: " + parceiro.getCep() + " Estado: " + parceiro.getEstado() + " Bairro: " + parceiro.getBairro() + " Numero: " + parceiro.getNumero());
        System.out.println("Categoria: " + parceiro.getCategoria());
        System.out.println(" Taxa e tempo: " + parceiro.getTaxaEntrega());
    }

    public void horarioFuncionamento() {
        System.out.println("Horário de Funcionamento " + parceiro.getNome());
        System.out.println("Segunda a sexta: " + parceiro.getHorarioSemana());
        System.out.println("Sábado e domingo: " + parceiro.getHorarioFimSemana());

        System.out.println("\nDeseja editar o horário de funcionamento? (S/N)");
        String resposta = sc.nextLine();

        if (!resposta.equalsIgnoreCase("S")) {
            return;
        }

        System.out.println("[1] Editar horário de segunda a sexta");
        System.out.println("[2] Editar horário de sábado e domingo");
        System.out.print("Escolha uma opção: ");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {

            case 1:
                System.out.println("Horário atual: " + parceiro.getHorarioSemana());
                System.out.print("Digite o novo horário (ex: 08:00 às 17:00): ");
                String novoSemana = sc.nextLine();

                parceiro.setHorarioSemana(novoSemana);
                parceiroController.atualizar(parceiro);

                System.out.println("Novo horário de semana definido!");
                break;

            case 2:
                System.out.println("Horário atual: " + parceiro.getHorarioFimSemana());
                System.out.print("Digite o novo horário (ex: 08:00 às 15:00): ");
                String novoFim = sc.nextLine();

                parceiro.setHorarioFimSemana(novoFim);
                parceiroController.atualizar(parceiro);

                System.out.println("Novo horário de fim de semana definido!");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }

    public void exibirFormasPagamento() {

        System.out.println("\n FORMAS DE PAGAMENTO ACEITAS:");
        System.out.println("-------------------------");

        List<String> formas = parceiro.getFormasPagamento();

        if (formas == null || formas.isEmpty()) {
            System.out.println("Nenhuma forma de pagamento cadastrada.");
        } else {
            System.out.println(String.join(", ", formas));
        }

        System.out.println("---------------------");
    }

}