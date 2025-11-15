package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.consumidor.model.Usuario;
import java.util.Scanner;

public class TelaLoginConsumidor {

    private Scanner sc;
    private UsuarioController controller;
    MenuPrincipalConsumidorView menu = new MenuPrincipalConsumidorView();
    int opcao = -1;

    public TelaLoginConsumidor(){
        this.sc = new Scanner(System.in);
        this.controller = new UsuarioController();
    }

    public void exibirLogin() {

        do {
            System.out.println("=====PEDIJÁ - CONSUMIDOR=====");
            System.out.println("[1] ENTRAR");
            System.out.println("[2] CADASTRAR");
            System.out.print("\n Escolha uma opção: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
                resultadoOpcao(opcao);
            } catch (Exception erro) {
                System.out.println("Opção inválida!");
                sc.nextLine();
            }
        } while (opcao != 0);

    }

    private void resultadoOpcao(int opcao) {

        switch (opcao) {
            case 1:
                entrar();
                break;
            case 2:
                cadastrar();
                break;
        }
    }

    public void entrar(){

        System.out.println("Digite seu Email: ");
        String email = sc.nextLine();

        System.out.println("Digite seu telefone: ");
        String telefone = sc.nextLine();

        // ===============================================================
        // AQUI ENTRA O BANCO DE DADOS (FUTURO)
        // Aqui vai chamar algo como controller.validarLogin(email, telefone)
        // ===============================================================

        if (email.isBlank() || telefone.isBlank()) {
            System.out.println("Preencha email e telefone antes de entrar.\n");
            return;
        }

        boolean existe = controller.validarLogin(email, telefone);

        if(existe) {
            System.out.println("LOGIN REALIZADO COM SUCESSO!");
            menu.exibirMenuCliente();
        }   else {
            System.out.println("Credenciais inválidas!\n");
        }

    }

    public void cadastrar(){

        Usuario novoConsumidor = new Usuario();

        System.out.print("Email: ");
        novoConsumidor.setEmail(sc.nextLine());

        System.out.print("Telefone: ");
        novoConsumidor.setTelefone(sc.nextLine());

        System.out.print("CPF: ");
        novoConsumidor.setCPF(sc.nextLine());

        System.out.print("Nome: ");
        novoConsumidor.setNome(sc.nextLine());

        System.out.println("CADASTRO REALIZADO COM SUCESSO!");
        System.out.println("BEM-VINDO AO PEDIJÁ!");

        // ===============================================================
        // AQUI ENTRA O BANCO DE DADOS (FUTURO)
        // Aqui vai salvar o novo usuário no banco
        // ===============================================================

        controller.cadastrarUsuario(novoConsumidor); // SIMULAÇÃO

        menu.exibirMenuCliente();
    }

}