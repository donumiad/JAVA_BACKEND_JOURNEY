
-- V1: Estrutura inicial da API de Estoque
-- Destinada à criação do esquema em um banco vazio.

CREATE TABLE produtos (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(300) NOT NULL,
                          preco NUMERIC(10,2) NOT NULL
                              CHECK (preco > 0),
                          estoque BIGINT NOT NULL
                              CHECK (estoque >= 0)
);

CREATE TABLE movimentacoes_estoque (
                                       id BIGSERIAL PRIMARY KEY,

                                       tipo_movimentacao VARCHAR(10) NOT NULL
                                           CHECK (tipo_movimentacao IN ('ENTRADA', 'SAIDA')),

                                       quantidade BIGINT NOT NULL
                                           CHECK (quantidade > 0),

                                       data_movimentacao TIMESTAMP NOT NULL
                                           DEFAULT CURRENT_TIMESTAMP,

                                       produto_id BIGINT NOT NULL,

                                       CONSTRAINT fk_movimentacao_produto
                                           FOREIGN KEY (produto_id)
                                               REFERENCES produtos(id)
);
