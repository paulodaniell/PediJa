package br.com.pedija.parceiro.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
    void closeConnection(Connection connection) throws SQLException;
}