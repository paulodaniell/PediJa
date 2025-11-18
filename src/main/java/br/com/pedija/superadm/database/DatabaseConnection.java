package br.com.pedija.superadm.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final ConnectionFactory connectionFactory = H2ConnectionFactory.getInstance();

    public static Connection getConnection() throws SQLException {
        return connectionFactory.getConnection();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connectionFactory.closeConnection(connection);
    }

    public static void initDatabase() {

        String createCategoriasSQL = """
                    CREATE TABLE IF NOT EXISTS categorias (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        descricao VARCHAR(255)
                    );
                """;

        String createParceiroSQL = """
                    CREATE TABLE IF NOT EXISTS Parceiro (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        cnpj VARCHAR(18) NOT NULL,
                        nome VARCHAR(150) NOT NULL,
                        senha VARCHAR(255) NOT NULL,
                        telefone VARCHAR(20),
                        email VARCHAR(150),
                        endereco VARCHAR(200),
                        categoria VARCHAR(100),
                        taxaEntrega DECIMAL(10,2),
                        cep VARCHAR(10),
                        estado VARCHAR(50),
                        cidade VARCHAR(100),
                        bairro VARCHAR(100),
                        numero INT,
                        horarioSemana VARCHAR(50) DEFAULT '10:00 às 18:00',
                        horarioFimSemana VARCHAR(50) DEFAULT '10:00 às 14:00',
                        formasPagamento VARCHAR(255)
                    );
                """;

        String createProdutosSQL = """
                    CREATE TABLE IF NOT EXISTS produtos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        descricao VARCHAR(255),
                        preco DECIMAL(10, 2) NOT NULL,
                        quantidade INT NOT NULL,
                        categoria_id INT,
                        idParceiro INT,
                        disponivel BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (idParceiro) REFERENCES Parceiro(id),
                        FOREIGN KEY (categoria_id) REFERENCES categorias(id)
                    );
                """;

        String createUsuarioSQL = """
                    CREATE TABLE IF NOT EXISTS Usuario (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        email VARCHAR(150) NOT NULL,
                        telefone VARCHAR(20),
                        cpf VARCHAR(14) NOT NULL,
                        nome VARCHAR(150) NOT NULL,
                        endereco VARCHAR(200),
                        formadepagamento VARCHAR(50)
                    );
                """;

        String createPagamentoSQL = """
                    CREATE TABLE IF NOT EXISTS Pagamento (
                        idpagamento INT PRIMARY KEY AUTO_INCREMENT,
                        valor DECIMAL(10,2) NOT NULL,
                        forma VARCHAR(50),
                        prazo INT
                    );
                """;

        String createEntregadorSQL = """
                    CREATE TABLE IF NOT EXISTS Entregador (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        nomeEntregador VARCHAR(150) NOT NULL,
                        telefone VARCHAR(20),
                        cpf VARCHAR(14),
                        veiculo VARCHAR(50),
                        disponivel BOOLEAN DEFAULT TRUE
                    );
                """;

        String createPedidoSQL = """
                    CREATE TABLE IF NOT EXISTS Pedido(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        idUsuario INT NOT NULL,
                        nomeCliente VARCHAR(150) NOT NULL,
                        valorTotal DECIMAL(10,2) NOT NULL,
                        idEntregador INT NULL,
                        status VARCHAR(50),
                        endereco VARCHAR(200),
                        formaPagamento VARCHAR(50),
                        idParceiro INT NULL,
                        FOREIGN KEY (idEntregador) REFERENCES Entregador(id),
                        FOREIGN KEY (idParceiro) REFERENCES Parceiro(id)
                    );
                """;

        String createPromocaoSQL = """
                    CREATE TABLE IF NOT EXISTS Promocao (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        idProduto INT NOT NULL,
                        idParceiro INT NOT NULL,
                        precoOriginal DECIMAL(10,2) NOT NULL,
                        precoPromocional DECIMAL(10,2) NOT NULL,
                        ativa BOOLEAN DEFAULT TRUE,
                        FOREIGN KEY (idParceiro) REFERENCES Parceiro(id)
                    );
                """;

        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();

            stmt.execute(createCategoriasSQL);
            stmt.execute(createParceiroSQL);
            stmt.execute(createProdutosSQL);
            stmt.execute(createUsuarioSQL);
            stmt.execute(createPagamentoSQL);
            stmt.execute(createEntregadorSQL);
            stmt.execute(createPedidoSQL);
            stmt.execute(createPromocaoSQL);

        } catch (SQLException e) {
            System.err.println(" ERRO: Erro ao inicializar banco de dados: " + e.getMessage
                    ());
        } finally {
            try {
                closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("⚠️ ERRO ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
