package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // CREATE
    public void criar(Produto produto) {

        String sql = """
            INSERT INTO produtos 
            (nome, descricao, preco, quantidade, categoria_id, idParceiro, disponivel)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setObject(5, produto.getCategoria_id(), Types.INTEGER);
            stmt.setInt(6, produto.getIdParceiro());
            stmt.setBoolean(7, produto.isDisponivel());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar produto: " + e.getMessage(), e);
        }
    }

    // READ - todos
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produtos.add(mapProduto(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }

        return produtos;
    }

    // READ - por parceiro
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

    // READ - por ID
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

    // READ - por Nome
    public Produto buscarPorNome(String nome) {

        String sql = "SELECT * FROM produtos WHERE nome = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapProduto(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto: " + e.getMessage(), e);
        }

        return null;
    }


    // UPDATE
    public void atualizar(Produto produto) {

        String sql = """
            UPDATE produtos 
            SET nome = ?, descricao = ?, preco = ?, quantidade = ?, categoria_id = ? 
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setObject(5, produto.getCategoria_id());
            stmt.setInt(6, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    // UPDATE - disponibilidade
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

    // DELETE
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


    private Produto mapProduto(ResultSet rs) throws SQLException {

        Produto p = new Produto();

        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setPreco(rs.getDouble("preco"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setCategoria_id(rs.getInt("categoria_id"));
        p.setIdParceiro(rs.getInt("idParceiro"));
        p.setDisponivel(rs.getBoolean("disponivel"));

        return p;
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

    public Produto buscarPorNomeComParceiro(String nome) {
        String sql = """
        SELECT p.*, u.nome AS nome_parceiro
        FROM produtos p
        JOIN Parceiro u ON p.idParceiro = u.id
        WHERE p.nome = ?
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = mapProduto(rs);
                produto.setNomeParceiro(rs.getString("nome_parceiro"));
                return produto;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto com parceiro: " + e.getMessage(), e);
        }
        return null;
    }

}

