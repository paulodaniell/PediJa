package br.com.pedija.consumidor.view.perfil;

import br.com.pedija.consumidor.model.Usuario;
import java.util.Scanner;

public class DadosConta {

    private Scanner sc = new Scanner(System.in);
    private Usuario usuario = new Usuario();

    public DadosConta() {}

    public void exibirDados() {

        System.out.println("===== DADOS DA CONTA =====");

//        System.out.println("Nome: " + usuario.getNome());
//        System.out.println("Idade: " + usuario.getIdade());
//        System.out.println("Email: " + usuario.getEmail());
//        System.out.println("Telefone: " + usuario.getTelefone());

        System.out.println("\n Quer alterar algum dado? (1 - Sim, 2 - Não)");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("1 - Nome");
                System.out.println("2 - Idade");
                System.out.println("3 - Email");
                System.out.println("4 - Telefone");
                System.out.print("Qual dado quer alterar? ");
                int dado = sc.nextInt();

//                switch (dado) {
//                    case 1:
//                        System.out.print("Novo nome: ");
//                        usuario.setNome(sc.nextLine());
//                        break;
//                    case 2:
//                        System.out.print("Nova idade: ");
//                        usuario.setIdade(sc.nextInt());
//                        break;
//                    case 3:
//                        System.out.print("Novo email: ");
//                        sc.nextLine(); // limpar buffer
//                        usuario.setEmail(sc.nextLine());
//                        break;
//                    case 4:
//                        System.out.print("Novo telefone: ");
//                        sc.nextLine(); // limpar buffer
//                        usuario.setTelefone(sc.nextLine());
//                        break;
//                    default:
//                        System.out.println("Opção inválida!");
//                        return;
//                }

                System.out.println("Dado atualizado com sucesso!");
                break;

            case 2:
                System.out.println("Saindo...");
                break;

            default:
                System.out.println("Digite um valor válido!");
                break;
        }
    }
}