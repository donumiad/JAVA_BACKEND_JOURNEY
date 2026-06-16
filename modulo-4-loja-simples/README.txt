# Loja Simples — Módulo 4

## Objetivo
Projeto de estudo do Módulo 4 do roadmap de backend Java.
Foco em Maven, estrutura de projeto profissional,
modelagem relacional e SQL.

## Tecnologias
- Java 21
- Maven
- PostgreSQL

## Estrutura do projeto
src/
└── main/java/br/com/raimundo/loja/
    ├── main/         → ponto de entrada
    ├── model/        → entidades do domínio
    ├── repository/   → acesso a dados (em memória)
    └── service/      → lógica de negócio

sql/
├── 00_create_database.sql  → criação do banco loja_simples
├── 01_drop_tables.sql      → remoção de tabelas (ordem reversa de FK)
├── 02_create_tables.sql    → criação das tabelas e constraints
├── 03_insert.sql           → dados iniciais de teste
├── 04_queries_basicas.sql  → consultas com SELECT e WHERE
├── 05_queries_avancadas.sql → consultas com JOIN, GROUP BY e agregações
└── 06_update_delete.sql    → exemplos de UPDATE e DELETE com segurança

## Modelagem do banco
- clientes    (id, nome, email, cpf)
- produtos    (id, nome, preco, estoque)
- pedidos     (id, cliente_id, data)
- itens_pedido (pedido_id, produto_id, quantidade, preco_unit)

## Como executar
1. Criar o banco:   psql -U postgres -f sql/00_create_database.sql
2. Criar tabelas:   psql -U postgres -d loja_simples -f sql/02_create_tables.sql
3. Popular dados:   psql -U postgres -d loja_simples -f sql/03_insert.sql

## O que aprendi
- Estrutura profissional de projetos Maven
- Organização de pacotes para backend Java
- Modelagem relacional com PKs, FKs e constraints
- SQL do básico ao intermediário: SELECT, INSERT,
  UPDATE, DELETE, WHERE, ORDER BY, GROUP BY, JOIN