package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Pedido;
import br.com.pedija.superadm.model.ItemPedido; // Certifique-se que o ItemPedidoDAO também exista

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {


    private final ItemPedidoDAO itemDAO = new ItemPedidoDAO();

    public int adicionar(Pedido p) {
        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = """
            INSERT INTO Pedido 
            (nomeCliente, idClienteitens, valorTotal, idEntregador, status, endereco, formaPagamento, idParceiro)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNomeCliente());
            stmt.setInt(2, p.getIdClienteitens());
            stmt.setDouble(3, p.getValorTotal());
            stmt.setInt(4, p.getIdEntregador());
            stmt.setString(5, p.getStatus());
            stmt.setString(6, p.getEndereco());
            stmt.setString(7, p.getFormaPagamento());
            stmt.setInt(8, p.getIdParceiro());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                p.setId(idGerado);

                // salva os itens
                for (ItemPedido item : p.getItens()) {
                    // Você precisará criar o ItemPedidoDAO e o método adicionarItem
                    itemDAO.adicionarItem(idGerado, item);
                }

                return idGerado;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir pedido: " + e.getMessage(), e);
        }

        return -1;
    }

    public Pedido buscarPorId(int id) {
        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = "SELECT * FROM Pedido WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Pedido p = mapPedido(rs);

                // Carrega itens do pedido
                p.setItens(itemDAO.listarPorPedido(id)); // Precisa criar este método

                return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Pedido> listarPorParceiroEStatus(int idParceiro, String status) {
        List<Pedido> lista = new ArrayList<>();

        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = "SELECT * FROM Pedido WHERE idParceiro = ? AND status = ? ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            stmt.setString(2, status);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido p = mapPedido(rs);
                p.setItens(itemDAO.listarPorPedido(p.getId())); // Precisa criar este método
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        }

        return lista;
    }


    public boolean atualizarStatus(int idPedido, String novoStatus) {
        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = "UPDATE Pedido SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idPedido);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status: " + e.getMessage(), e);
        }
    }

    public boolean atribuirEntregador(int idPedido, int idEntregador) {

        String sql = """
            UPDATE Pedido
            SET idEntregador = ?, status = 'EM_ENTREGA'
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntregador);
            stmt.setInt(2, idPedido);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atribuir entregador: " + e.getMessage(), e);
        }
    }


    public List<Pedido> listarAguardandoEntregador(int idParceiro) {
        List<Pedido> lista = new ArrayList<>();


        String sql = """
            SELECT * FROM Pedido
            WHERE idParceiro = ?
            AND status = 'PRONTO'
            AND idEntregador = 0
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido p = mapPedido(rs);
                p.setItens(itemDAO.listarPorPedido(p.getId())); // Precisa criar este método
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos aguardando entregador: " + e.getMessage(), e);
        }

        return lista;
    }


    public double calcularFaturamentoTotal(int idParceiro) {
        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = """
            SELECT SUM(valorTotal) AS total
            FROM Pedido
            WHERE idParceiro = ?
            AND status = 'ENTREGUE'
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular faturamento: ".concat(e.getMessage()), e);
        }

        return 0;
    }


    public int contarPedidosEntregues(int idParceiro) {
        // SQL CORRIGIDO: usa 'Pedido' maiúsculo
        String sql = """
            SELECT COUNT(*) AS qtd
            FROM Pedido
            WHERE idParceiro = ?
            AND status = 'ENTREGUE'
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("qtd");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar pedidos entregues: " + e.getMessage(), e);
        }

        return 0;
    }

    public int contarPorStatus(int idParceiro, String status) {
        String sql = "SELECT COUNT(*) AS total FROM Pedido WHERE idParceiro = ? AND status = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            stmt.setString(2, status);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar pedidos por status: " + e.getMessage(), e);
        }
        return 0;
    }

    private Pedido mapPedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();

        p.setId(rs.getInt("id"));
        p.setNomeCliente(rs.getString("nomeCliente"));
        p.setIdClienteitens(rs.getInt("idClienteitens"));
        p.setValorTotal(rs.getDouble("valorTotal"));
        p.setIdEntregador(rs.getInt("idEntregador"));
        p.setStatus(rs.getString("status"));
        p.setEndereco(rs.getString("endereco"));
        p.setFormaPagamento(rs.getString("formaPagamento"));
        p.setIdParceiro(rs.getInt("idParceiro"));

        return p;
    }
}