package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    /**
     * CREATE
     * Salva um novo produto no banco.
     */
    public void criar(Produto produto) {

        String sql = """
            INSERT INTO produtos (nome, descricao, preco, quantidade, idParceiro)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getIdParceiro());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar produto: " + e.getMessage(), e);
        }
    }

    /**
     * READ
     * Lista todos os produtos que pertencem a um parceiro específico.
     */
    public List<Produto> buscarPorParceiro(int idParceiro) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE idParceiro = ? ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                produtos.add(mapProduto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos: " + e.getMessage(), e);
        }
        return produtos;
    }

    /**
     * READ
     * Busca um produto único pelo seu ID.
     */
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapProduto(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * UPDATE
     * Atualiza os dados principais de um produto.
     */
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            // LINHA 117 CORRIGIDA
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    public List<Produto> listarMaisVendidos(int idParceiro) {
        List<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT p.id, p.nome, p.descricao, p.preco, p.quantidade, p.idParceiro, p.disponivel
            FROM ItemPedido ip
            JOIN Pedido ped ON ip.pedidoId = ped.id
            JOIN produtos p ON ip.produtoId = p.id
            WHERE ped.idParceiro = ? AND ped.status = 'ENTREGUE'
            GROUP BY p.id, p.nome, p.descricao, p.preco, p.quantidade, p.idParceiro, p.disponivel
            ORDER BY SUM(ip.quantidade) DESC
            LIMIT 5
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Reutiliza o mapProduto para transformar o resultado em objeto
                produtos.add(mapProduto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos mais vendidos: " + e.getMessage(), e);
        }
        return produtos;
    }

    public boolean alterarDisponibilidade(int id, boolean disponivel) {
        String sql = "UPDATE produtos SET disponivel = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disponibilidade: " + e.getMessage(), e);
        }
    }


    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover produto: " + e.getMessage(), e);
        }
    }

    public int contarPorParceiro(int idParceiro) {
        String sql = "SELECT COUNT(*) AS total FROM produtos WHERE idParceiro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idParceiro);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar produtos: " + e.getMessage(), e);
        }
        return 0;
    }


    private Produto mapProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setPreco(rs.getDouble("preco"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setIdParceiro(rs.getInt("idParceiro"));
        // p.setDisponivel(rs.getBoolean("disponivel")); // Descomente quando adicionar a coluna
        return p;
    }
}