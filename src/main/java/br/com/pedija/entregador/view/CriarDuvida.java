package br.com.pedija.entregador.view;

import br.com.pedija.superadm.model.Duvida;
import java.util.ArrayList;

import java.util.Scanner;

public class CriarDuvida {

    Scanner sc = new Scanner(System.in);

    public void criarduvida (ArrayList<Duvida> duvidas) {


        System.out.println("=== CRIAR NOVA DÚVIDA ===");

        System.out.print("Digite o ID da dúvida: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Digite o título da sua dúvida: ");
        String titulo = sc.nextLine();

        System.out.print("Digite a descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Digite a data (ex: 23/10/2025): ");
        String data = sc.nextLine();

        Duvida novaDuvida = new Duvida(id, titulo, descricao, data);

        duvidas.add(novaDuvida);

        System.out.println("\n Dúvida criada com sucesso!");
        System.out.println(novaDuvida);

        System.out.println("Duvida atualizada com sucesso!");




    }





}
