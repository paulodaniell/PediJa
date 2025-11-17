# Pedija - Sistema de Delivery

## Visão Geral
Aplicativo de console (TUI) desenvolvido em Java para gerenciamento de sistema de delivery com três interfaces principais: Parceiro, Consumidor e Sistema de Administração.

## Tecnologias
- **Linguagem:** Java 19
- **Build Tool:** Maven
- **Banco de Dados:** H2 Database (Embarcado)
- **Interface:** Console/Terminal (TUI)

## Estrutura do Projeto
```
src/main/java/br/com/pedija/
├── App.java                    # Classe principal
├── consumidor/                 # Módulo do consumidor
│   ├── controller/            # Controladores (Carrinho, Pedido, Produto, Usuário)
│   └── view/                  # Telas de interface
├── parceiro/                   # Módulo do parceiro
│   ├── controller/            # Controladores (Entregador, Parceiro, Pedido, Produto, Promoção)
│   └── view/                  # Telas de interface
└── superadm/                   # Módulo de administração
    ├── dao/                   # Data Access Objects
    ├── database/              # Configuração de banco de dados
    ├── model/                 # Modelos de dados
    └── view/                  # Telas de interface
```

## Como Executar
O projeto está configurado para executar automaticamente através do workflow "Pedija App". 

### Primeira Execução
Na primeira execução, o script `run.sh` compila o projeto automaticamente:
```bash
./run.sh
```

### Execuções Subsequentes
O script reutiliza o JAR já compilado para inicialização rápida. Para forçar uma recompilação:
```bash
rm -rf target/
./run.sh
```

### Execução Manual com Maven
```bash
mvn clean package
java -jar target/pedija-1.0-SNAPSHOT.jar
```

## Funcionalidades

### Parceiro
- Login de parceiros
- Gerenciamento de produtos
- Gerenciamento de pedidos
- Controle de entregas
- Criação de promoções
- Relatórios

### Consumidor
- Login de consumidores
- Busca de produtos
- Carrinho de compras
- Realização de pedidos
- Acompanhamento de pedidos
- Visualização de promoções
- Gerenciamento de perfil (dados, endereços, pagamentos)

## Banco de Dados
O sistema utiliza H2 Database embarcado com as seguintes tabelas:
- `categorias` - Categorias de produtos
- `produtos` - Produtos disponíveis
- `Usuario` - Usuários consumidores
- `Pagamento` - Formas de pagamento
- `Entregador` - Entregadores cadastrados
- `Parceiro` - Parceiros (estabelecimentos)
- `Pedido` - Pedidos realizados
- `Promocao` - Promoções ativas

O banco é inicializado automaticamente na primeira execução.

## Mudanças Recentes (17/11/2025)
- Ajustada versão do Java de 24 para 19 (compatível com ambiente Replit)
- Configurado Maven Shade Plugin para criar JAR executável com todas as dependências
- Criado script `run.sh` otimizado que compila apenas na primeira execução
- Atualizado `.gitignore` para excluir arquivos de banco de dados H2 e artefatos de build
- Configurado workflow para execução automática no Replit

## Arquitetura
- **MVC Pattern:** Separação entre Model, View e Controller
- **DAO Pattern:** Camada de acesso a dados isolada
- **Factory Pattern:** Usado para conexões de banco de dados
- **Modular:** Separação clara entre módulos de Consumidor, Parceiro e Administração
