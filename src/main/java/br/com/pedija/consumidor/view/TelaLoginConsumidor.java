package br.com.pedija.consumidor.view;


import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.superadm.model.Usuario;
import java.util.Scanner;


public class TelaLoginConsumidor {


    private final Scanner sc;
    private final UsuarioController controller;
    private int opcao = -1;


    public TelaLoginConsumidor(){
        this.sc = new Scanner(System.in);
        this.controller = new UsuarioController();
    }


    public void exibirLogin() {


        do {
            System.out.println("=====PEDIJÁ - CONSUMIDOR=====");
            System.out.println("[1] ENTRAR");
            System.out.println("[2] CADASTRAR");
            System.out.println("[0] VOLTAR");
            System.out.print("\n Escolha uma opção: ");


            try {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("Opção inválida! Digite um número.");
                    continue;
                }
                opcao = Integer.parseInt(line);


                if (opcao == 1) {
                    entrar();
                } else if (opcao == 2) {
                    cadastrar();
                } else if (opcao != 0) {
                    System.out.println("Opção inválida!");
                }


            } catch (NumberFormatException erro) {
                System.out.println("Opção inválida! Digite apenas números.");
            }
        } while (opcao != 0);
    }


    private void entrar(){


        System.out.println("Digite seu Email: ");
        String email = sc.nextLine();


        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();


        if (email.isBlank() || telefone.isBlank()) {
            System.out.println("Preencha email e telefone antes de entrar.\n");
            return;
        }


        Usuario usuarioLogado = controller.buscarPorEmailETelefone(email, telefone);


        if(usuarioLogado != null) {
            System.out.println("LOGIN REALIZADO COM SUCESSO!");


            // CORREÇÃO DE FLUXO E USUÁRIO NULO:
            MenuPrincipalConsumidorView menu = new MenuPrincipalConsumidorView();
            menu.setUsuarioLogado(usuarioLogado);
            menu.exibirMenuCliente();


            this.opcao = 0;
        }   else {
            System.out.println("Credenciais inválidas!\n");
        }


    }


    private void cadastrar(){


        Usuario novoConsumidor = new Usuario();


        System.out.print("Email: ");
        novoConsumidor.setEmail(sc.nextLine());


        System.out.print("Telefone: ");
        novoConsumidor.setTelefone(sc.nextLine());


        System.out.print("CPF: ");
        novoConsumidor.setcpf(sc.nextLine());


        System.out.print("Nome: ");
        novoConsumidor.setNome(sc.nextLine());


        System.out.print("Endereço: ");
        novoConsumidor.setEndereco(sc.nextLine());


        controller.cadastrarUsuario(novoConsumidor);


        System.out.println("CADASTRO REALIZADO COM SUCESSO!");
        System.out.println("BEM-VINDO AO PEDIJÁ!");


        MenuPrincipalConsumidorView menu = new MenuPrincipalConsumidorView();
        menu.setUsuarioLogado(novoConsumidor);
        menu.exibirMenuCliente();


        this.opcao = 0;
    }


}
