package br.com.pedija.parceiro.view;

import br.com.pedija.parceiro.controller.ParceiroController;
import br.com.pedija.superadm.model.Parceiro;

import java.util.Scanner;

public class TelaLoginParceiro {

    private Scanner sc;
    private ParceiroController controller;

    public TelaLoginParceiro() {
        this.sc = new Scanner(System.in);
        this.controller = new ParceiroController();
    }

    public void exibir() {

        System.out.println("          PEDIJA - PARCEIRO");
        System.out.println("\n-----------------------------------");
        System.out.println("||     CADASTRO NOVO PARCEIRO    ||");
        System.out.println("-----------------------------------");

        Parceiro novoParceiro = new Parceiro();

        try {
            System.out.println("═══ DADOS BÁSICOS ═══");

            System.out.print(" Nome do Restaurante: ");
            novoParceiro.setNome(sc.nextLine());

            System.out.print(" Email: ");
            novoParceiro.setEmail(sc.nextLine());

            System.out.print(" Senha: ");
            String senha = sc.nextLine();

            System.out.print(" Confirme a Senha: ");
            String confirmaSenha = sc.nextLine();

            if (!senha.equals(confirmaSenha)) {
                System.out.println("\n As senhas não coincidem!");
                return;
            }
            novoParceiro.setSenha(senha);

            System.out.println("═══ DADOS COMPLEMENTARES ═══");

            System.out.print(" CNPJ: ");
            novoParceiro.setCnpj(sc.nextLine());

            System.out.print(" Telefone: ");
            novoParceiro.setTelefone(sc.nextLine());

            System.out.print(" Cidade: ");
            novoParceiro.setCidade(sc.nextLine());

            System.out.print(" Estado: ");
            novoParceiro.setEstado(sc.nextLine());

            System.out.print(" Bairro: ");
            novoParceiro.setBairro(sc.nextLine());

            System.out.print(" CEP: ");
            novoParceiro.setCep(sc.nextLine());

            System.out.print(" Numero: ");
            novoParceiro.setNumero(sc.nextInt());
            sc.nextLine();

            System.out.println(" Categoria da sua loja ");
            System.out.println("[1] Pizzaria");
            System.out.println("[2] Hamburgueria");
            System.out.println("[3] Japonês");
            System.out.println("[4] Churrascaria");
            System.out.println("[5] Lanchonete");
            System.out.println("[6] Padaria");
            System.out.println("[7] Outro");

            System.out.print("Escolha: ");
            int tipoOpcao = Integer.parseInt(sc.nextLine());

            String categoria = exibirTipoRestaurante(tipoOpcao);
            if (tipoOpcao == 7) {
                System.out.print("Digite o tipo: ");
                categoria = sc.nextLine();
            }
            novoParceiro.setCategoria(categoria);


            System.out.println("\n-----------------------------------");
            System.out.println("||      CONFIRMAR CADASTRO?      ||");
            System.out.println("-----------------------------------");
            System.out.println(" Nome: " + novoParceiro.getNome());
            System.out.println(" Email: " + novoParceiro.getEmail());
            System.out.println(" CNPJ: " + novoParceiro.getCnpj());
            System.out.println(" Telefone: " + novoParceiro.getTelefone());
            System.out.println(" Tipo: " + novoParceiro.getCategoria());
            System.out.println("----------------------------------");

            System.out.print("\nConfirma cadastro? (S/N): ");
            String confirma = sc.nextLine();

            if (confirma.equalsIgnoreCase("S")) {

                if (controller.cadastrar(novoParceiro)) {
                    System.out.println("\n Parceiro cadastrado com sucesso!");
                    System.out.println(" Bem-vindo ao Pedija, " + novoParceiro.getNome() + "!");


                    Parceiro parceiroLogado = controller.login(novoParceiro.getEmail(), novoParceiro.getSenha());
                    if (parceiroLogado != null) {
                        MenuPrincipalParceiro menu = new MenuPrincipalParceiro(parceiroLogado);
                        menu.exibirMenuParceiro();
                    }
                } else {
                    System.out.println("\n Erro ao cadastrar parceiro!");
                }
            } else {
                System.out.println("\n Cadastro cancelado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n️ Erro no cadastro.");
        }
    }


    private String exibirTipoRestaurante(int opcao) {
        switch (opcao) {
            case 1: return "Pizzaria";
            case 2: return "Hamburgueria";
            case 3: return "Japonês";
            case 4: return "Churrascaria";
            case 5: return "Lanchonete";
            case 6: return "Padaria";
            case 7: return "Outro";
            default: return "Restaurante";
        }
    }
}