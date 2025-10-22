package br.com.pedija.consumidor.view.perfil;
import java.util.Scanner;

public class CodigoEntregaConta {

   Scanner sc = new Scanner(System.in);

    private int codigo;

    public CodigoEntregaConta() {
        this.codigo = 0; // código inicial
    }

    public void alterarCodigo() {

            System.out.println("Seu Código Atual: " + codigo);

            System.out.println("Deseja mudar o código? (1 - Sim / 2 - Não)");
            int opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.println("Digite seu novo código:");

                this.codigo = sc.nextInt();

                System.out.println("Código atualizado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
    }

    public void setCodigo(int novoCodigo){
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}