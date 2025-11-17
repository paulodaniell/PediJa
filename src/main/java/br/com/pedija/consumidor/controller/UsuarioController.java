package br.com.pedija.consumidor.controller;


import br.com.pedija.superadm.model.Parceiro;
import br.com.pedija.superadm.model.Usuario;
import br.com.pedija.superadm.dao.UsuarioDAO;

/**
 * Controller mínimo para trabalhar com VIEW + MODEL sem DAO.
 * Atualmente usa uma lista em memória como "banco fake".
 *
 * // AQUI ENTRA O BANCO DE DADOS (FUTURO)
 * - Substituir a lista por chamadas ao DAO/Repository que acesse o BD.
 */

public class UsuarioController {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String email, String telefone) {

        return usuarioDAO.login(email, telefone);
    }


    public boolean cadastrar(Usuario  usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            System.out.println("Nome é obrigatório!");
            return false;
        }

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            System.out.println("Email inválido!");
            return false;
        }

        if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
            System.out.println("CPF é obrigatório!");
            return false;
        }

        if (usuario.getEndereco() == null || usuario.getEndereco().isEmpty()) {
            System.out.println("Endereço é obrigatórias!");
            return false;
        }

        usuarioDAO.criar(usuario);

        System.out.println("Parceiro cadastrado no banco!");
        return true;
    }

    public boolean atualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO.atualizar(usuario);
            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }


}

