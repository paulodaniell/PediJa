package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {


    public void criar(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome, descricao) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar categoria: " + e.getMessage(), e);
        }
    }


    public List<Categoria> buscarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nome";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage(), e);
        }

        return categorias;
    }


    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                return categoria;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria: " + e.getMessage(), e);
        }

        return null;
    }


    public void atualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nome = ?, descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Categoria não encontrada!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage(), e);
        }
    }


    public void deletar(int id) {

        String checkSql = "SELECT COUNT(*) FROM produtos WHERE categoria_id = ?";
        String deleteSql = "DELETE FROM categorias WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {


            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, id);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new RuntimeException("Não é possível excluir! Existem produtos vinculados a esta categoria.");
                }
            }


            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, id);
                int rowsAffected = deleteStmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new RuntimeException("Categoria não encontrada!");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover categoria: " + e.getMessage(), e);
        }
    }
}