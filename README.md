# Java Backend Journey

<p align="center">
  <img src="https://img.shields.io/badge/Java-Backend-blue" alt="Java Backend" />
  <img src="https://img.shields.io/badge/Focus-Spring%20Boot-success" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Database-PostgreSQL-informational" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Build-Maven-red" alt="Maven" />
  <img src="https://img.shields.io/badge/Status-In%20Progress-yellow" alt="Status" />
</p>

# Sistema de Estoque com JDBC

## Sobre o projeto

Aplicação Java em console para gerenciamento de produtos e
movimentações de estoque utilizando JDBC e PostgreSQL.

O projeto foi desenvolvido como parte de um roteiro de estudos
em backend Java.

## Funcionalidades

- Cadastrar produtos
- Listar produtos
- Buscar produto por id
- Buscar produtos por nome
- Atualizar dados de produto
- Atualizar quantidade
- Remover produtos
- Registrar entrada de estoque
- Registrar movimentação de estoque
- Executar transações com commit e rollback

## Tecnologias

- Java
- JDBC
- PostgreSQL
- Maven
- Git

## Arquitetura

- Model: representação dos objetos
- DAO: acesso aos dados
- Service: regras e transações
- App: menu e execução
- Connection: configuração da conexão

## Banco de dados

O projeto utiliza uma base PostgreSQL com as tabelas:

- produtos
- movimentacoes_estoque

A tabela produtos foi reaproveitada de um projeto anterior
que também possui clientes, pedidos e itens_pedido.

## Como executar

1. Criar ou configurar o banco PostgreSQL.
2. Executar os scripts da pasta sql.
3. Configurar as credenciais do banco.
4. Executar a classe Main.

## Conceitos praticados

- Connection
- PreparedStatement
- ResultSet
- DAO Pattern
- SQL parametrizado
- Tratamento de SQLException
- Transações
- Commit e rollback
- Separação entre DAO, Service e Menu

## Contato

Você pode acompanhar minha evolução e projetos também por aqui:

- LinkedIn: https://www.linkedin.com/in/raimundo-neto-00a677157
- GitHub: https://github.com/donumiad
- Portfólio: no one

---

## Observação final

O foco deste repositório não é apenas “estudar Java”, mas construir uma trajetória real rumo ao desenvolvimento backend profissional, com consistência, documentação e projetos progressivamente mais maduros.
