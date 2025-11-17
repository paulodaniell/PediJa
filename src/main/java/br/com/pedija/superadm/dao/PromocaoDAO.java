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
            INSERT INTO Promocao (idProduto, idParceiro, precoOriginal, precoPromocional, ativa)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, promocao.getIdProduto());
            stmt.setInt(2, promocao.getIdParceiro());
            stmt.setDouble(3, promocao.getPrecoOriginal());
            stmt.setDouble(4, promocao.getPrecoPromocional());
            stmt.setBoolean(5, promocao.isAtiva()); // Salva o status 'ativa'

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                promocao.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar promoção: " + e.getMessage(), e);
        }
    }


    public List<Promocao> listarPorParceiro(int idParceiro) {
        List<Promocao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Promocao WHERE idParceiro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapPromocao(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar promoções: " + e.getMessage(), e);
        }
        return lista;
    }


    public List<Promocao> listarAtivasPorParceiro(int idParceiro) {
        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM Promocao WHERE idParceiro = ? AND ativa = TRUE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapPromocao(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar promoções ativas: " + e.getMessage(), e);
        }
        return lista;
    }


    public void atualizarStatus(int idPromocao, boolean ativa) {
        String sql = "UPDATE Promocao SET ativa = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, ativa);
            stmt.setInt(2, idPromocao);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da promoção: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Promocao WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover promoção: " + e.getMessage(), e);
        }
    }


    private Promocao mapPromocao(ResultSet rs) throws SQLException {
        Promocao p = new Promocao();
        p.setId(rs.getInt("id"));
        p.setIdProduto(rs.getInt("idProduto"));
        p.setIdParceiro(rs.getInt("idParceiro"));
        p.setPrecoOriginal(rs.getDouble("precoOriginal"));
        p.setPrecoPromocional(rs.getDouble("precoPromocional"));
        p.setAtiva(rs.getBoolean("ativa")); // Lê o campo 'ativa'
        return p;
    }
}