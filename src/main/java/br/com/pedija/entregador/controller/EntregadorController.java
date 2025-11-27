package br.com.pedija.entregador.controller;

import br.com.pedija.superadm.dao.EntregadorDAO;
import br.com.pedija.superadm.model.Entregador;

import java.util.List;

public class EntregadorController {

    private EntregadorDAO entregadorDAO = new EntregadorDAO();

    public List<Entregador> listarTodos() {
        return entregadorDAO.buscarTodos();
    }

    public List<Entregador> listarDisponiveis() {
        return entregadorDAO.listarDisponiveis();
    }

    public Entregador buscarPorId(int id) {
        return entregadorDAO.buscarPorId(id);
    }


    public boolean atribuirPedido(int idEntregador) {
        Entregador e = entregadorDAO.buscarPorId(idEntregador);

        if (e != null && e.isDisponivel()) {

            e.setDisponivel(false);


            entregadorDAO.atualizar(e);

            return true;
        }
        return false;
    }
    public Entregador login(String email, String senha) {

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email não pode ser vazio!");
            return null;
        }

        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha não pode ser vazia!");
            return null;
        }

        Entregador entregador = entregadorDAO.login(email, senha);

        if (entregador == null) {
            System.out.println("Email ou senha incorretos!");
            return null;
        }

        return entregador;
    }



    public boolean cadastrar(Entregador entregador) {

        if (entregador.getNome() == null || entregador.getNome().trim().isEmpty()) {
            System.out.println("Nome é obrigatório!");
            return false;
        }

        if (entregador.getEmail() == null || !entregador.getEmail().contains("@")) {
            System.out.println("Email inválido!");
            return false;
        }

        if (entregador.getSenha() == null || entregador.getSenha().length() < 3) {
            System.out.println("Senha deve ter pelo menos 3 caracteres!");
            return false;
        }

        if (entregador.getCpf() == null || entregador.getCpf().trim().isEmpty()) {
            System.out.println("CPF é obrigatório!");
            return false;
        }

        if (entregador.getFormasPagamento() == null || entregador.getFormasPagamento().isEmpty()) {
            System.out.println("Formas de pagamento são obrigatórias!");
            return false;
        }

        entregadorDAO.criar(entregador);

        System.out.println("Entregador cadastrado no banco!");
        return true;
    }

    public boolean atualizarEntregador(Entregador entregador) {
        try {
            entregadorDAO.atualizar(entregador);
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

}