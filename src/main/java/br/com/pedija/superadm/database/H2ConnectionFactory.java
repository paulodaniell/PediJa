package br.com.pedija.superadm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionFactory implements ConnectionFactory {

    // 💾 Caminho e nome do banco — agora em uma pasta "data" e com nome "pedija"
    private static final String URL = "jdbc:h2:./data/pedija";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static H2ConnectionFactory instance;

    // 🔒 Construtor privado (padrão Singleton)
    private H2ConnectionFactory() {}

    // ✅ Garante uma única instância da factory
    public static H2ConnectionFactory getInstance() {
        if (instance == null) {
            synchronized (H2ConnectionFactory.class) {
                if (instance == null) {
                    instance = new H2ConnectionFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            // Carrega o driver H2 (boa prática, mesmo que moderno)
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("❌ Driver H2 não encontrado!", e);
        }

        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ Conexão H2 aberta: " + URL);
        return conn;
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("🔒 Conexão H2 fechada com sucesso!");
        }
    }

    // Métodos auxiliares para log/debug
    public String getUrl() {
        return URL;
    }

    public String getUser() {
        return USER;
    }
}
