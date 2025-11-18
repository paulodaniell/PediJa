package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {


    public void criar(Usuario usuario) {

        String sql = "INSERT INTO usuario (email, telefone, cpf, nome, endereco, formadepagamento) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getNome());
            stmt.setString(5, usuario.getEndereco());
            stmt.setString(6, usuario.getFormadepagamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage(), e);
        }
    }


    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                u.setNome(rs.getString("nome"));
                u.setEndereco(rs.getString("endereco"));
                u.setFormadepagamento(rs.getString("formadepagamento"));

                usuarios.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários: " + e.getMessage(), e);
        }

        return usuarios;
    }

    public Usuario login(String email, String telefone) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND telefone = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, telefone);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                u.setNome(rs.getString("nome"));
                u.setEndereco(rs.getString("endereco"));
                u.setFormadepagamento(rs.getString("formadepagamento"));

                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage(), e);
        }

        return null;
    }




    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                u.setNome(rs.getString("nome"));
                u.setEndereco(rs.getString("endereco"));
                u.setFormadepagamento(rs.getString("formadepagamento"));
                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por ID: " + e.getMessage(), e);
        }

        return null;
    }


    public void atualizar(Usuario usuario) {
        String sql = """
                UPDATE usuario
                SET email = ?, telefone = ?, cpf = ?, nome = ?, endereco = ?, formadepagamento = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getNome());
            stmt.setString(5, usuario.getEndereco());
            stmt.setString(6, usuario.getFormadepagamento());
            stmt.setInt(7, usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Usuário não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage(), e);
        }
    }



    public void deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Usuário não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário: " + e.getMessage(), e);
        }
    }
}