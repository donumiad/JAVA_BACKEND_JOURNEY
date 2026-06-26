-- sql/01_create.sql
-- Tabela de produtos do Sistema de Estoque

DROP TABLE IF EXISTS produtos;

CREATE TABLE produtos (
  id         BIGSERIAL      PRIMARY KEY,
  nome       VARCHAR(100)   NOT NULL,
  categoria  VARCHAR(50)    NOT NULL,
  preco      DECIMAL(10, 2) NOT NULL CHECK (preco > 0),
  quantidade INTEGER        NOT NULL DEFAULT 0 CHECK (quantidade >= 0)

);