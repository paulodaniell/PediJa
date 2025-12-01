package br.com.pedija.entregador.view;

import br.com.pedija.entregador.controller.EntregadorController;
import br.com.pedija.superadm.dao.EntregadorDAO;
import br.com.pedija.superadm.model.Entregador;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TelaLoginEntregador {

    private Scanner sc;
    private EntregadorController controller;
    private EntregadorDAO entregadorDAO;

    public TelaLoginEntregador() {
        this.sc = new Scanner(System.in);
        this.controller = new EntregadorController();
        this.entregadorDAO = new EntregadorDAO();
    }

    public void exibir() {

        int opcao;

        do {
            System.out.println("\nPEDIJA - Entregador");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> entrar();
                case 2 -> cadastrar();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida!");
            }

        } while (true);
    }

    private void cadastrar() {
        System.out.println("\n-----------------------------------");
        System.out.println("||     CADASTRO NOVO ENTREGADOR    ||");
        System.out.println("-----------------------------------");

        Entregador novoEntregador = new Entregador();

        try {
            System.out.println("═══ DADOS BÁSICOS ═══");

            System.out.print(" Nome do Entregador: ");
            novoEntregador.setNome(sc.nextLine());

            System.out.print(" Email: ");
            novoEntregador.setEmail(sc.nextLine());

            System.out.print(" Senha: ");
            String senha = sc.nextLine();

            System.out.print(" Confirme a Senha: ");
            String confirmaSenha = sc.nextLine();

            if (!senha.equals(confirmaSenha)) {
                System.out.println("\n As senhas não coincidem!");
                return;
            }
            novoEntregador.setSenha(senha);

            System.out.println("═══ DADOS COMPLEMENTARES ═══");

            System.out.print(" CPF: ");
            novoEntregador.setCpf(sc.nextLine());

            System.out.print(" Telefone: ");
            novoEntregador.setTelefone(sc.nextLine());

            System.out.print(" Veículo (Carro, moto ou bicicleta): ");
            novoEntregador.setVeiculo(sc.nextLine());

            novoEntregador.setDisponivel(true);

            System.out.print(" Contato de Emergência: ");
            novoEntregador.setContatoDeEmergencia(Integer.parseInt(sc.nextLine()));

            System.out.print(" Nome do Contato de Emergência: ");
            novoEntregador.setNomeEmergencia(sc.nextLine());

            System.out.println("═══ FORMAS DE PAGAMENTO ═══");
            System.out.print("Digite as formas de pagamento (ex: pix,debito,credito): ");
            String formasInput = sc.nextLine();

            List<String> formas = Arrays.asList(formasInput.split(","));
            novoEntregador.setFormasPagamento(formas);

            System.out.println("\n-----------------------------------");
            System.out.println("||      CONFIRMAR CADASTRO?      ||");
            System.out.println("-----------------------------------");
            System.out.println(" Nome: " + novoEntregador.getNome());
            System.out.println(" Email: " + novoEntregador.getEmail());
            System.out.println(" CPF: " + novoEntregador.getCpf());
            System.out.println(" Telefone: " + novoEntregador.getTelefone());
            System.out.println(" Contato de Emergência: " + novoEntregador.getContatoDeEmergencia());
            System.out.println(" Nome do Contato: " + novoEntregador.getNomeEmergencia());
            System.out.println("----------------------------------");

            System.out.print("\nConfirma cadastro? (S/N): ");
            String confirma = sc.nextLine();

            if (confirma.equalsIgnoreCase("S")) {

                if (controller.cadastrar(novoEntregador)) {
                    System.out.println("\n Entregador cadastrado com sucesso!");
                    System.out.println(" Bem-vindo ao Pedija, " + novoEntregador.getNome() + "!");

                    Entregador entregadorLogado = controller.login(
                            novoEntregador.getEmail(),
                            novoEntregador.getSenha()
                    );

                    if (entregadorLogado != null) {
<<<<<<< Updated upstream
                        TelaInicialEntregador menu = new TelaInicialEntregador();
=======

                        TelaInicialEntregador menu = new TelaInicialEntregador(entregadorLogado);
>>>>>>> Stashed changes
                        menu.TelaInicioEntregador();
                    }

                } else {
                    System.out.println("\n Erro ao cadastrar entregador!");
                }

            } else {
                System.out.println("\n Cadastro cancelado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n Erro no cadastro.");
        }
    }

    private void entrar() {
        System.out.println("Digite seu Email: ");
        String email = sc.nextLine();

        System.out.println("Digite sua Senha: ");
        String senha = sc.nextLine();

        Entregador entregadorLogado = entregadorDAO.login(email, senha);

        if (entregadorLogado != null) {
            System.out.println("Login realizado com sucesso! Bem-vindo " + entregadorLogado.getNome());
<<<<<<< Updated upstream
            TelaInicialEntregador menu = new TelaInicialEntregador();
=======

            TelaInicialEntregador menu = new TelaInicialEntregador(entregadorLogado);
>>>>>>> Stashed changes
            menu.TelaInicioEntregador();

        } else {
            System.out.println("Email ou senha inválidos!");
        }
    }
}
