package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void criar(Pedido pedido) {
        String sqlPedido = """
            INSERT INTO Pedido 
            (idusuario, nomeCliente, valorTotal, status, endereco, formaPagamento, idParceiro)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setString(2, pedido.getNomeCliente());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.setString(4, pedido.getStatus());
            stmt.setString(5, pedido.getEndereco());
            stmt.setString(6, pedido.getFormaPagamento());
            stmt.setObject(7, pedido.getIdParceiro(), Types.INTEGER);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt(1));
            }

            salvarItensPedido(pedido);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar pedido: " + e.getMessage(), e);
        }
    }

    private void salvarItensPedido(Pedido pedido) {
        String sqlItens = """
            INSERT INTO PedidoItens (idPedido, idProduto, nomeProduto, preco, quantidade)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlItens)) {

            for (Produto p : pedido.getItens()) {
                stmt.setInt(1, pedido.getId());
                stmt.setInt(2, p.getId());
                stmt.setString(3, p.getNome());
                stmt.setDouble(4, p.getPreco());
                stmt.setInt(5, p.getQuantidade());
                stmt.addBatch();
            }

            stmt.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar itens do pedido: " + e.getMessage(), e);
        }
    }

    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM Pedido WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pedido pedido = mapPedido(rs);
                carregarItensPedido(pedido);
                return pedido;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido: " + e.getMessage(), e);
        }
        return null;
    }

    private void carregarItensPedido(Pedido pedido) {
        String sql = "SELECT * FROM PedidoItens WHERE idPedido = ?";
        List<Produto> itens = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("idProduto"));
                p.setNome(rs.getString("nomeProduto"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                itens.add(p);
            }

            pedido.setItens(itens);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao carregar itens do pedido: " + e.getMessage(), e);
        }
    }

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

    public void deletar(int id) {
        String sqlItens = "DELETE FROM PedidoItens WHERE idPedido = ?";
        String sqlPedido = "DELETE FROM Pedido WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmtItens = conn.prepareStatement(sqlItens);
             PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido)) {

            stmtItens.setInt(1, id);
            stmtItens.executeUpdate();

            stmtPedido.setInt(1, id);
            stmtPedido.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pedido: " + e.getMessage(), e);
        }
    }

    public List<Pedido> buscarPorStatus(String status) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE status = ? ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = mapPedido(rs);
                carregarItensPedido(pedido);
                pedidos.add(pedido);
            }

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
        p.setStatus(rs.getString("status"));
        p.setEndereco(rs.getString("endereco"));
        p.setFormaPagamento(rs.getString("formaPagamento"));

        Integer idParceiro = (Integer) rs.getObject("idParceiro");
        p.setIdParceiro(idParceiro == null ? 0 : idParceiro);

        return p;
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedido pedido = mapPedido(rs);
                carregarItensPedido(pedido);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os pedidos: " + e.getMessage(), e);
        }

        return pedidos;
    }
}
