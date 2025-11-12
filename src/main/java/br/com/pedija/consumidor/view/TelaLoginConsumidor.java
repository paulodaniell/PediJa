package br.com.pedija.consumidor.view;

import br.com.pedija.consumidor.controller.UsuarioController;
import br.com.pedija.consumidor.model.Usuario;
import br.com.pedija.consumidor.view.MenuPrincipalConsumidorView;
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
            int opcao = sc.nextInt();

            try {
                opcao = sc.nextInt();
                resultadoOpcao(opcao);
            } catch (Exception erro) {
                System.out.println("Opção inválida!");
                sc.nextLine(); // limpa o buffer
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

        //colocar um confere aqui para saber se a pessoa existe ou não. Eu não sei como faz.
        System.out.println("Digite seu Email: ");

        System.out.println("Digite seu telefone: ");

        MenuPrincipalConsumidorView menu = new MenuPrincipalConsumidorView();
        menu.exibirMenuCliente();
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

        menu.exibirMenuCliente();
    }

}
