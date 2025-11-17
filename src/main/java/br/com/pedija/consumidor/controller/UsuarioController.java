package br.com.pedija.consumidor.controller;


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


    public boolean criarUsuario(Usuario usuario) {
        try {
            usuarioDAO.criar(usuario);
            return true;

        } catch (Exception e) {
            return false;
        }
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

