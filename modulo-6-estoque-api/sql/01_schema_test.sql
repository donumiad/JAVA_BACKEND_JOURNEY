
-- Esquema de testes da API de Estoque
-- Executar exclusivamente em estoque_api_test.

BEGIN;

-- Impede alterações em outro banco.
DO $$
    BEGIN
        IF current_database() <> 'estoque_api_test' THEN
            RAISE EXCEPTION
                'Banco incorreto: %. Esperado: estoque_api_test',
                current_database();
        END IF;
    END
$$;

CREATE TABLE IF NOT EXISTS produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(300) NOT NULL,
    preco NUMERIC(10,2) NOT NULL
        CHECK (preco >= 0),
    estoque BIGINT NOT NULL
        CHECK (estoque >= 0)
);

CREATE TABLE IF NOT EXISTS movimentacoes_estoque (
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

COMMIT;
