package br.com.pedija.parceiro.controller;
import java.util.ArrayList;
import java.util.List;
import br.com.pedija.superadm.model.Parceiro;


public class ParceiroController {

    private static List<Parceiro> parceiros = new ArrayList<>();
    private static int proximoId = 1;

    public Parceiro login(String email, String senha) {

        if (email == null || email.trim().isEmpty()) {
            System.out.println(" Email não pode ser vazio!");
            return null;
        }

        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha não pode ser vazia!");
            return null;
        }
        for (Parceiro p : parceiros) {
            if (p.getEmail().equalsIgnoreCase(email) &&
                    p.getSenha().equals(senha)) {
                return p;
            }
        }

        return null;
    }

    public static boolean cadastrar(Parceiro parceiro) {

        if (parceiro.getNome() == null || parceiro.getNome().trim().isEmpty()) {
            System.out.println(" Nome é obrigatório!");
            return false;
        }

        if (parceiro.getNome().length() < 3) {
            System.out.println(" Nome deve ter pelo menos 3 caracteres!");
            return false;
        }


        if (parceiro.getEmail() == null || parceiro.getEmail().trim().isEmpty()) {
            System.out.println(" Email é obrigatório!");
            return false;
        }

        if (!parceiro.getEmail().contains("@")) {
            System.out.println(" Email inválido! Deve conter @");
            return false;
        }



        if (parceiro.getSenha() == null || parceiro.getSenha().trim().isEmpty()) {
            System.out.println(" Senha é obrigatória!");
            return false;
        }

        if (parceiro.getSenha().length() < 3) {
            System.out.println(" Senha deve ter pelo menos 3 caracteres!");
            return false;
        }


        if (parceiro.getCnpj() == null || parceiro.getCnpj().trim().isEmpty()) {
            System.out.println(" CNPJ é obrigatório!");
            return false;
        }


        if (parceiro.getTelefone() == null || parceiro.getTelefone().trim().isEmpty()) {
            System.out.println(" Telefone é obrigatório!");
            return false;
        }


        if (parceiro.getCategoria() == null || parceiro.getCategoria().trim().isEmpty()) {
            System.out.println(" Tipo de restaurante é obrigatório!");
            return false;
        }


        if (parceiro.getTaxaEntrega() < 0) {
            System.out.println("Taxa de entrega não pode ser negativa!");
            return false;
        }

        parceiro.setId(proximoId++);


        parceiro.setAberto(true);

        boolean sucesso = parceiros.add(parceiro);

        if (sucesso) {
            System.out.println(" ID gerado: " + parceiro.getId());
        }

        return sucesso;
    }


}
