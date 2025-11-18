package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {


    public void criar(Pagamento pagamento) {
        String sql = "INSERT INTO pagamento (idpagamento, valor, forma, prazo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pagamento.getIdpagamento());
            stmt.setDouble(2, pagamento.getValor());
            stmt.setString(3, pagamento.getForma());
            stmt.setInt(4, pagamento.getPrazo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar pagamento: " + e.getMessage(), e);
        }
    }



    public List<Pagamento> buscarTodos() {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM pagamento ORDER BY idpagamento";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pagamento p = new Pagamento();
                p.setIdpagamento(rs.getInt("idpagamento"));
                p.setValor(rs.getDouble("valor"));
                p.setForma(rs.getString("forma"));
                p.setPrazo(rs.getInt("prazo"));

                pagamentos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pagamentos: " + e.getMessage(), e);
        }

        return pagamentos;
    }



    public Pagamento buscarPorId(int idpagamento) {
        String sql = "SELECT * FROM pagamento WHERE idpagamento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpagamento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pagamento p = new Pagamento();
                p.setIdpagamento(rs.getInt("idpagamento"));
                p.setValor(rs.getDouble("valor"));
                p.setForma(rs.getString("forma"));
                p.setPrazo(rs.getInt("prazo"));
                return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pagamento por ID: " + e.getMessage(), e);
        }

        return null;
    }



    public void atualizar(Pagamento pagamento) {
        String sql = """
                UPDATE pagamento
                SET valor = ?, forma = ?, prazo = ?
                WHERE idpagamento = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValor());
            stmt.setString(2, pagamento.getForma());
            stmt.setInt(3, pagamento.getPrazo());
            stmt.setInt(4, pagamento.getIdpagamento());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Pagamento não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento: " + e.getMessage(), e);
        }
    }



    public void deletar(int idpagamento) {
        String sql = "DELETE FROM pagamento WHERE idpagamento = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpagamento);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Pagamento não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover pagamento: " + e.getMessage(), e);
        }
    }
}