package br.com.pedija.parceiro.controller;

import br.com.pedija.superadm.dao.ParceiroDAO;
import br.com.pedija.superadm.model.Parceiro;

public class ParceiroController {

    private ParceiroDAO parceiroDAO = new ParceiroDAO();

    public Parceiro login(String email, String senha) {

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email não pode ser vazio!");
            return null;
        }

        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha não pode ser vazia!");
            return null;
        }


        Parceiro parceiro = parceiroDAO.login(email, senha);

        if (parceiro == null) {
            System.out.println("Email ou senha incorretos!");
            return null;
        }

        return parceiro;
    }

    public boolean cadastrar(Parceiro parceiro) {

        if (parceiro.getNome() == null || parceiro.getNome().trim().isEmpty()) {
            System.out.println("Nome é obrigatório!");
            return false;
        }

        if (parceiro.getEmail() == null || !parceiro.getEmail().contains("@")) {
            System.out.println("Email inválido!");
            return false;
        }

        if (parceiro.getSenha() == null || parceiro.getSenha().length() < 3) {
            System.out.println("Senha deve ter pelo menos 3 caracteres!");
            return false;
        }

        if (parceiro.getCnpj() == null || parceiro.getCnpj().trim().isEmpty()) {
            System.out.println("CNPJ é obrigatório!");
            return false;
        }

        if (parceiro.getCategoria() == null) {
            System.out.println("Categoria obrigatória!");
            return false;
        }

        if (parceiro.getFormasPagamento() == null || parceiro.getFormasPagamento().isEmpty()) {
            System.out.println("Formas de pagamento são obrigatórias!");
            return false;
        }

        parceiroDAO.criar(parceiro);

        System.out.println("Parceiro cadastrado no banco!");
        return true;
    }



    public Parceiro buscarPorId(int id) {
        return parceiroDAO.buscarPorId(id);
    }

    public boolean atualizar(Parceiro parceiro) {
        try {
            parceiroDAO.atualizar(parceiro);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }

}