package br.com.pedija.superadm.dao;

import br.com.pedija.superadm.database.DatabaseConnection;
import br.com.pedija.superadm.model.Parceiro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParceiroDAO {

    // CREATE
    public void criar(Parceiro parceiro) {

        String sql = """
            INSERT INTO Parceiro 
            (cnpj, nome, senha, telefone, email, endereco, categoria, taxaEntrega, cep, estado, cidade, bairro, numero, horarioSemana, horarioFimSemana, formasPagamento)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, parceiro.getCnpj());
            stmt.setString(2, parceiro.getNome());
            stmt.setString(3, parceiro.getSenha());
            stmt.setString(4, parceiro.getTelefone());
            stmt.setString(5, parceiro.getEmail());
            stmt.setString(6, parceiro.getEndereco());
            stmt.setString(7, parceiro.getCategoria());
            stmt.setDouble(8, parceiro.getTaxaEntrega());
            stmt.setString(9, parceiro.getCep());
            stmt.setString(10, parceiro.getEstado());
            stmt.setString(11, parceiro.getCidade());
            stmt.setString(12, parceiro.getBairro());
            stmt.setInt(13, parceiro.getNumero());
            stmt.setString(14, parceiro.getHorarioSemana());
            stmt.setString(15, parceiro.getHorarioFimSemana());

            // converte List<String> → "pix,crédito,débito"
            String formasPagamento = String.join(",", parceiro.getFormasPagamento());
            stmt.setString(16, formasPagamento);

            stmt.executeUpdate();


            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                parceiro.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar parceiro: " + e.getMessage(), e);
        }
    }


    // READ ALL
    public List<Parceiro> buscarTodos() {
        List<Parceiro> parceiros = new ArrayList<>();

        String sql = "SELECT * FROM Parceiro ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Parceiro p = new Parceiro();
                p.setId(rs.getInt("id"));
                p.setCnpj(rs.getString("cnpj"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setEndereco(rs.getString("endereco"));
                p.setCategoria(rs.getString("categoria"));
                p.setTaxaEntrega(rs.getDouble("taxaEntrega"));
                p.setCep(rs.getString("cep"));
                p.setEstado(rs.getString("estado"));
                p.setCidade(rs.getString("cidade"));
                p.setBairro(rs.getString("bairro"));
                p.setNumero(rs.getInt("numero"));
                p.setHorarioSemana(rs.getString("horarioSemana"));
                p.setHorarioFimSemana(rs.getString("horarioFimSemana"));

                String formasPagamento = rs.getString("formasPagamento");
                if (formasPagamento != null && !formasPagamento.isEmpty()) {
                    p.setFormasPagamento(Arrays.asList(formasPagamento.split(",")));
                } else {
                    p.setFormasPagamento(new ArrayList<>());
                }


                parceiros.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar parceiros: " + e.getMessage(), e);
        }

        return parceiros;
    }


    // READ BY ID
    public Parceiro buscarPorId(int id) {
        String sql = "SELECT * FROM Parceiro WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Parceiro p = new Parceiro();
                p.setId(rs.getInt("id"));
                p.setCnpj(rs.getString("cnpj"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setEndereco(rs.getString("endereco"));
                p.setCategoria(rs.getString("categoria"));
                p.setTaxaEntrega(rs.getDouble("taxaEntrega"));
                p.setCep(rs.getString("cep"));
                p.setEstado(rs.getString("estado"));
                p.setCidade(rs.getString("cidade"));
                p.setBairro(rs.getString("bairro"));
                p.setNumero(rs.getInt("numero"));
                p.setHorarioSemana(rs.getString("horarioSemana"));
                p.setHorarioFimSemana(rs.getString("horarioFimSemana"));

                String formasPagamento = rs.getString("formasPagamento");
                if (formasPagamento != null && !formasPagamento.isEmpty()) {
                    p.setFormasPagamento(Arrays.asList(formasPagamento.split(",")));
                } else {
                    p.setFormasPagamento(new ArrayList<>());
                }

                return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar parceiro: " + e.getMessage(), e);
        }

        return null;
    }
    public Parceiro login(String email, String senha) {
        String sql = "SELECT * FROM Parceiro WHERE email = ? AND senha = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // --- Mapeamento feito aqui dentro ---
                Parceiro p = new Parceiro();
                p.setId(rs.getInt("id"));
                p.setCnpj(rs.getString("cnpj"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setEndereco(rs.getString("endereco"));
                p.setCategoria(rs.getString("categoria"));
                p.setTaxaEntrega(rs.getDouble("taxaEntrega"));
                p.setCep(rs.getString("cep"));
                p.setEstado(rs.getString("estado"));
                p.setCidade(rs.getString("cidade"));
                p.setBairro(rs.getString("bairro"));
                p.setNumero(rs.getInt("numero"));
                p.setHorarioSemana(rs.getString("horarioSemana"));
                p.setHorarioFimSemana(rs.getString("horarioFimSemana"));

                String formasPagamento = rs.getString("formasPagamento");
                if (formasPagamento != null && !formasPagamento.isEmpty()) {
                    p.setFormasPagamento(Arrays.asList(formasPagamento.split(",")));
                } else {
                    p.setFormasPagamento(new ArrayList<>());
                }
                return p;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage(), e);
        }

        return null;
    }


    // UPDATE
    public void atualizar(Parceiro parceiro) {
        String sql = """
            UPDATE Parceiro SET 
            cnpj = ?, nome = ?, senha = ?, telefone = ?, email = ?, endereco = ?, categoria = ?, taxaEntrega = ?, 
            cep = ?, estado = ?, cidade = ?, bairro = ?, numero = ?, horarioSemana = ?, horarioFimSemana = ?, 
            formasPagamento = ?
            WHERE id = ?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, parceiro.getCnpj());
            stmt.setString(2, parceiro.getNome());
            stmt.setString(3, parceiro.getSenha());
            stmt.setString(4, parceiro.getTelefone());
            stmt.setString(5, parceiro.getEmail());
            stmt.setString(6, parceiro.getEndereco());
            stmt.setString(7, parceiro.getCategoria());
            stmt.setDouble(8, parceiro.getTaxaEntrega());
            stmt.setString(9, parceiro.getCep());
            stmt.setString(10, parceiro.getEstado());
            stmt.setString(11, parceiro.getCidade());
            stmt.setString(12, parceiro.getBairro());
            stmt.setInt(13, parceiro.getNumero());
            stmt.setString(14, parceiro.getHorarioSemana());
            stmt.setString(15, parceiro.getHorarioFimSemana());

            String formasPagamento = String.join(",", parceiro.getFormasPagamento());
            stmt.setString(16, formasPagamento);

            stmt.setInt(17, parceiro.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Parceiro não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar parceiro: " + e.getMessage(), e);
        }
    }
    // DELETE
    public void deletar(int id) {
        String sql = "DELETE FROM Parceiro WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Parceiro não encontrado!");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover parceiro: " + e.getMessage(), e);
        }
    }
}