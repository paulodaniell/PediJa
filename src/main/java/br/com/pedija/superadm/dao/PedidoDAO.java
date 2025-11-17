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
       (idUsuario, nomeCliente, valorTotal, idEntregador, status, endereco, formaPagamento, idParceiro)
       VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setString(2, pedido.getNomeCliente());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.setInt(4, pedido.getIdEntregador()); // pode ser 0 ou NULL se não tiver
            stmt.setString(5, pedido.getStatus());
            stmt.setString(6, pedido.getEndereco());
            stmt.setString(7, pedido.getFormaPagamento());
            stmt.setInt(8, pedido.getIdParceiro()); // pode ser 0 ou NULL

            stmt.executeUpdate();

            // Recuperar ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // ajuda a identificar problemas
            throw new RuntimeException("Erro ao cadastrar pedido: " + e.getMessage(), e);
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido ORDER BY id";


        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
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


                pedidos.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        }


        return pedidos;
    }




    // READ ALL
    public List<Pedido> buscarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido ORDER BY id";


        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
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


                pedidos.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pedidos: " + e.getMessage(), e);
        }


        return pedidos;
    }




    // READ BY ID
    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
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


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedido: " + e.getMessage(), e);
        }


        return null;
    }




    // UPDATE
    public void atualizar(Pedido pedido) {
        String sql = """
           UPDATE pedido SET
           nomeCliente = ?, idClienteitens = ?, valorTotal = ?, idEntregador = ?, status = ?, endereco = ?, formaPagamento = ?, idParceiro = ?
           WHERE id = ?
       """;


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, pedido.getNomeCliente());
            stmt.setInt(2, pedido.getIdClienteitens());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.setInt(4, pedido.getIdEntregador());
            stmt.setString(5, pedido.getStatus());
            stmt.setString(6, pedido.getEndereco());
            stmt.setString(7, pedido.getFormaPagamento());
            stmt.setInt(8, pedido.getIdParceiro());
            stmt.setInt(9, pedido.getId());


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Pedido não encontrado!");
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pedido: " + e.getMessage(), e);
        }
    }




    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, id);


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new RuntimeException("Pedido não encontrado!");
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover pedido: " + e.getMessage(), e);
        }
    }


    //Por status


    public List<Pedido> buscarPorStatus(String status) {
        String sql = "SELECT * FROM pedido WHERE status = ? ORDER BY id";
        List<Pedido> pedidos = new ArrayList<>();


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
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


                pedidos.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedidos por status: " + e.getMessage(), e);
        }


        return pedidos;
    }








}





