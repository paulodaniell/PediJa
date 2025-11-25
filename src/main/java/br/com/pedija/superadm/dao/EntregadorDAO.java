package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Entregador;
import br.com.pedija.superadm.model.Parceiro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntregadorDAO {


    public void criar(Entregador entregador) {

        String sql = """
                INSERT INTO Entregador (nomeEntregador, telefone, cpf, veiculo, disponivel)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entregador.getNomeEntregador());
            stmt.setString(2, entregador.getTelefone());
            stmt.setString(3, entregador.getCpf());
            stmt.setString(4, entregador.getVeiculo());
            stmt.setBoolean(5, entregador.isDisponivel());

            stmt.executeUpdate();

            // Pega o ID gerado pelo banco e salva no objeto
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entregador.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar entregador: " + e.getMessage(), e);
        }
    }


    // READ ALL
    public List<Entregador> buscarTodos() {
        List<Entregador> entregadores = new ArrayList<>();
        // SQL CORRIGIDO: Nome da tabela 'Entregador'
        String sql = "SELECT * FROM Entregador ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                entregadores.add(mapEntregador(rs)); // Usando o map
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar entregadores: " + e.getMessage(), e);
        }

        return entregadores;
    }


    // READ BY ID
    public Entregador buscarPorId(int id) {
        // SQL CORRIGIDO: Nome da tabela 'Entregador'
        String sql = "SELECT * FROM Entregador WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapEntregador(rs); // Usando o map
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entregador: " + e.getMessage(), e);
        }

        return null;
    }

    // READ - LISTAR DISPONÍVEIS (Adicionado do DAO 1, mas corrigido)
    public List<Entregador> listarDisponiveis() {
        List<Entregador> lista = new ArrayList<>();
        // SQL CORRIGIDO: Nome da tabela 'Entregador'
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


    // UPDATE
    public void atualizar(Entregador entregador) {
        // SQL CORRIGIDO: Nome da tabela 'Entregador'
        String sql = """
                UPDATE Entregador
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
        // SQL CORRIGIDO: Nome da tabela 'Entregador'
        String sql = "DELETE FROM Entregador WHERE id = ?";

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

    // MAP RESULTSET → OBJETO (Reutilizável)
    private Entregador mapEntregador(ResultSet rs) throws SQLException {
        Entregador e = new Entregador();
        e.setId(rs.getInt("id"));
        e.setNomeEntregador(rs.getString("nomeEntregador"));
        e.setTelefone(rs.getString("telefone"));
        e.setCpf(rs.getString("cpf"));
        e.setVeiculo(rs.getString("veiculo"));
        e.setDisponivel(rs.getBoolean("disponivel"));
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
                // --- Mapeamento feito aqui dentro ---
                Entregador e = new Entregador();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setEmail(rs.getString("email"));
                e.setSenha(rs.getString("senha"));
                e.setTelefone(rs.getString("telefone"));
                e.setCpf(rs.getString("cpf"));
                e.setVeiculo(rs.getString("veiculo"));
                e.setDisponivel(rs.getBoolean("disponivel"));

                String formasPagamento = rs.getString("formasPagamento");
                if (formasPagamento != null && !formasPagamento.isEmpty()) {
                    e.setFormasPagamento(Arrays.asList(formasPagamento.split(",")));
                } else {
                    e.setFormasPagamento(new ArrayList<>());
                }

                return e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage(), e);
        }

        return null;
    }

}