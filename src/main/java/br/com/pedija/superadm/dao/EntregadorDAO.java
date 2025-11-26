package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Entregador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntregadorDAO {

    public void criar(Entregador entregador) {

        String sql = """
                INSERT INTO Entregador 
                (nome, email, senha, telefone, cpf, veiculo, disponivel, formasPagamento, contatoDeEmergencia, nomeEmergencia)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entregador.getNome());
            stmt.setString(2, entregador.getEmail());
            stmt.setString(3, entregador.getSenha());
            stmt.setString(4, entregador.getTelefone());
            stmt.setString(5, entregador.getCpf());
            stmt.setString(6, entregador.getVeiculo());
            stmt.setBoolean(7, entregador.isDisponivel());

            if (entregador.getFormasPagamento() != null)
                stmt.setString(8, String.join(",", entregador.getFormasPagamento()));
            else
                stmt.setString(8, null);

            stmt.setInt(9, entregador.getContatoDeEmergencia());
            stmt.setString(10, entregador.getNomeEmergencia());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entregador.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar entregador: " + e.getMessage(), e);
        }
    }

    public List<Entregador> buscarTodos() {
        List<Entregador> entregadores = new ArrayList<>();

        String sql = "SELECT * FROM Entregador ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entregadores.add(mapEntregador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar entregadores: " + e.getMessage(), e);
        }

        return entregadores;
    }

    public Entregador buscarPorId(int id) {
        String sql = "SELECT * FROM Entregador WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEntregador(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entregador por ID: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Entregador> listarDisponiveis() {
        List<Entregador> lista = new ArrayList<>();

        String sql = "SELECT * FROM Entregador WHERE disponivel = TRUE ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapEntregador(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar entregadores disponíveis: " + ex.getMessage(), ex);
        }

        return lista;
    }

    public void atualizar(Entregador entregador) {

        String sql = """
                UPDATE Entregador
                SET nome = ?, email = ?, senha = ?, telefone = ?, cpf = ?, veiculo = ?, 
                    disponivel = ?, formasPagamento = ?, contatoDeEmergencia = ?, nomeEmergencia = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entregador.getNome());
            stmt.setString(2, entregador.getEmail());
            stmt.setString(3, entregador.getSenha());
            stmt.setString(4, entregador.getTelefone());
            stmt.setString(5, entregador.getCpf());
            stmt.setString(6, entregador.getVeiculo());
            stmt.setBoolean(7, entregador.isDisponivel());

            if (entregador.getFormasPagamento() != null)
                stmt.setString(8, String.join(",", entregador.getFormasPagamento()));
            else
                stmt.setString(8, null);

            stmt.setInt(9, entregador.getContatoDeEmergencia());
            stmt.setString(10, entregador.getNomeEmergencia());

            stmt.setInt(11, entregador.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Entregador não encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar entregador: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {

        String sql = "DELETE FROM Entregador WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Entregador não encontrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir entregador: " + e.getMessage(), e);
        }
    }

    private Entregador mapEntregador(ResultSet rs) throws SQLException {
        Entregador e = new Entregador();

        e.setId(rs.getInt("id"));
        e.setNome(rs.getString("nome"));
        e.setEmail(rs.getString("email"));
        e.setSenha(rs.getString("senha"));
        e.setTelefone(rs.getString("telefone"));
        e.setCpf(rs.getString("cpf"));
        e.setVeiculo(rs.getString("veiculo"));
        e.setDisponivel(rs.getBoolean("disponivel"));

        String formas = rs.getString("formasPagamento");
        if (formas != null && !formas.isEmpty()) {
            e.setFormasPagamento(Arrays.asList(formas.split(",")));
        }

        e.setContatoDeEmergencia(rs.getInt("contatoDeEmergencia"));
        e.setNomeEmergencia(rs.getString("nomeEmergencia"));

        return e;
    }

    public Entregador login(String email, String senha) {

        String sql = "SELECT * FROM Entregador WHERE email = ? AND senha = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEntregador(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro no login: " + e.getMessage(), e);
        }

        return null;
    }
}
