package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Entregador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO {

    // CREATE
    public void criar(Entregador entregador) {
        String sql = "INSERT INTO entregador (id, nomeEntregador, telefone, cpf, veiculo, disponivel) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entregador.getId());
            stmt.setString(2, entregador.getNomeEntregador());
            stmt.setString(3, entregador.getTelefone());
            stmt.setString(4, entregador.getCpf());
            stmt.setString(5, entregador.getVeiculo());
            stmt.setBoolean(6, entregador.isDisponivel());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar entregador: " + e.getMessage(), e);
        }
    }


    // READ ALL
    public List<Entregador> buscarTodos() {
        List<Entregador> entregadores = new ArrayList<>();
        String sql = "SELECT * FROM entregador ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Entregador e = new Entregador();
                e.setId(rs.getInt("id"));
                e.setNomeEntregador(rs.getString("nomeEntregador"));
                e.setTelefone(rs.getString("telefone"));
                e.setCpf(rs.getString("cpf"));
                e.setVeiculo(rs.getString("veiculo"));
                e.setDisponivel(rs.getBoolean("disponivel"));

                entregadores.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar entregadores: " + e.getMessage(), e);
        }

        return entregadores;
    }


    // READ BY ID
    public Entregador buscarPorId(int id) {
        String sql = "SELECT * FROM entregador WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Entregador e = new Entregador();
                e.setId(rs.getInt("id"));
                e.setNomeEntregador(rs.getString("nomeEntregador"));
                e.setTelefone(rs.getString("telefone"));
                e.setCpf(rs.getString("cpf"));
                e.setVeiculo(rs.getString("veiculo"));
                e.setDisponivel(rs.getBoolean("disponivel"));

                return e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entregador: " + e.getMessage(), e);
        }

        return null;
    }


    // UPDATE
    public void atualizar(Entregador entregador) {
        String sql = """
                UPDATE entregador
                SET nomeEntregador = ?, telefone = ?, cpf = ?, veiculo = ?, disponivel = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entregador.getNomeEntregador());
            stmt.setString(2, entregador.getTelefone());
            stmt.setString(3, entregador.getCpf());
            stmt.setString(4, entregador.getVeiculo());
            stmt.setBoolean(5, entregador.isDisponivel());
            stmt.setInt(6, entregador.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Entregador não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar entregador: " + e.getMessage(), e);
        }
    }


    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM entregador WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Entregador não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover entregador: " + e.getMessage(), e);
        }
    }
}
