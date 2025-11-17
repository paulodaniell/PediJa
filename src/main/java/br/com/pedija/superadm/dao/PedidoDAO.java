package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    // CREATE
    public void criar(Pedido pedido) {
        String sql = """
            INSERT INTO Pedido 
            (idusuario, nomeCliente, valorTotal, idEntregador, status, endereco, formaPagamento, idParceiro)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setString(2, pedido.getNomeCliente());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.setObject(4, pedido.getIdEntregador(), Types.INTEGER);
            stmt.setString(5, pedido.getStatus());
            stmt.setString(6, pedido.getEndereco());
            stmt.setString(7, pedido.getFormaPagamento());
            stmt.setObject(8, pedido.getIdParceiro(), Types.INTEGER);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) pedido.setId(rs.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar pedido: " + e.getMessage(), e);
        }


    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) pedidos.add(mapPedido(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os pedidos: " + e.getMessage(), e);
        }
        return pedidos;
    }

    // READ
    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM Pedido WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return mapPedido(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido: " + e.getMessage(), e);
        }
        return null;
    }

    // UPDATE
    public void atualizar(Pedido pedido) {
        String sql = """
            UPDATE Pedido 
            SET status = ?, valorTotal = ?, endereco = ?, formaPagamento = ?
            WHERE id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getStatus());
            stmt.setDouble(2, pedido.getValorTotal());
            stmt.setString(3, pedido.getEndereco());
            stmt.setString(4, pedido.getFormaPagamento());
            stmt.setInt(5, pedido.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pedido: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pedido: " + e.getMessage(), e);
        }
    }

    // READ - por status
    public List<Pedido> buscarPorStatus(String status) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE status = ? ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) pedidos.add(mapPedido(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        }
        return pedidos;
    }

    private Pedido mapPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setId(rs.getInt("id"));
        p.setIdUsuario(rs.getInt("idusuario"));
        p.setNomeCliente(rs.getString("nomeCliente"));
        p.setValorTotal(rs.getDouble("valorTotal"));
        p.setIdEntregador((Integer) rs.getObject("idEntregador"));
        p.setStatus(rs.getString("status"));
        p.setEndereco(rs.getString("endereco"));
        p.setFormaPagamento(rs.getString("formaPagamento"));
        p.setIdParceiro((Integer) rs.getObject("idParceiro"));
        return p;
    }
}
