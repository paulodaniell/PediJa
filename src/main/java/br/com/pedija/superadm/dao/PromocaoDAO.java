package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Promocao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocaoDAO {

    // CREATE
    public void criar(Promocao promocao) {
        String sql = """
            INSERT INTO promocao
            (id, idProduto, idParceiro, precoOriginal, precoPromocional)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, promocao.getId());
            stmt.setInt(2, promocao.getIdProduto());
            stmt.setInt(3, promocao.getIdParceiro());
            stmt.setDouble(4, promocao.getPrecoOriginal());
            stmt.setDouble(5, promocao.getPrecoPromocional());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar promoção: " + e.getMessage(), e);
        }
    }


    // READ ALL
    public List<Promocao> buscarTodos() {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM promocao ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Promocao p = new Promocao();
                p.setId(rs.getInt("id"));
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdParceiro(rs.getInt("idParceiro"));
                p.setPrecoOriginal(rs.getDouble("precoOriginal"));
                p.setPrecoPromocional(rs.getDouble("precoPromocional"));

                promocoes.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar promoções: " + e.getMessage(), e);
        }

        return promocoes;
    }


    // READ BY ID
    public Promocao buscarPorId(int id) {
        String sql = "SELECT * FROM promocao WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Promocao p = new Promocao();
                p.setId(rs.getInt("id"));
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdParceiro(rs.getInt("idParceiro"));
                p.setPrecoOriginal(rs.getDouble("precoOriginal"));
                p.setPrecoPromocional(rs.getDouble("precoPromocional"));

                return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar promoção: " + e.getMessage(), e);
        }

        return null;
    }


    // UPDATE
    public void atualizar(Promocao promocao) {
        String sql = """
            UPDATE promocao SET
            idProduto = ?, idParceiro = ?, precoOriginal = ?, precoPromocional = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, promocao.getIdProduto());
            stmt.setInt(2, promocao.getIdParceiro());
            stmt.setDouble(3, promocao.getPrecoOriginal());
            stmt.setDouble(4, promocao.getPrecoPromocional());
            stmt.setInt(5, promocao.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Promoção não encontrada!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar promoção: " + e.getMessage(), e);
        }
    }


    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM promocao WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Promoção não encontrada!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover promoção: " + e.getMessage(), e);
        }
    }
}
