package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.ItemPedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

        public void adicionarItem(int pedidoId, ItemPedido item) {

            String sql = """
            INSERT INTO ItemPedido
            (pedidoId, produtoId, quantidade, precoUnitario, nomeProduto, subTotal)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, pedidoId);
                stmt.setInt(2, item.getProdutoId());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getPrecoUnitario());
                stmt.setString(5, item.getNomeProduto());
                stmt.setDouble(6, item.getTotal());

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao inserir item de pedido: " + e.getMessage(), e);
            }
        }

        public List<ItemPedido> listarPorPedido(int pedidoId) {

            List<ItemPedido> itens = new ArrayList<>();

            String sql = "SELECT * FROM ItemPedido WHERE pedidoId = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, pedidoId);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    itens.add(mapItem(rs));
                }

            } catch (SQLException e) {
                throw new RuntimeException("Erro ao listar itens do pedido: " + e.getMessage(), e);
            }

            return itens;
        }

        // ------------------------------------------------
        // MAPEAR ResultSet → ItemPedido
        // ------------------------------------------------
        private ItemPedido mapItem(ResultSet rs) throws SQLException {
            ItemPedido item = new ItemPedido();

            item.setId(rs.getInt("id"));
            item.setPedidoId(rs.getInt("pedidoId"));
            item.setProdutoId(rs.getInt("produtoId"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setPrecoUnitario(rs.getDouble("precoUnitario"));
            item.setNomeProduto(rs.getString("nomeProduto"));
            item.setSubTotal(rs.getDouble("subTotal"));

            return item;
        }
    }


