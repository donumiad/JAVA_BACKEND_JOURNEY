CREATE SEQUENCE movimentacoes_estoque_id_seq INCREMENT BY 1;

CREATE TABLE movimentacoes_estoque (
                                       id BIGINT PRIMARY KEY DEFAULT nextval('movimentacoes_estoque_id_seq'),
                                       produto_id BIGINT NOT NULL,
                                       tipo_movimentacao VARCHAR(10) NOT NULL,
                                       quantidade BIGINT NOT NULL,
                                       data_movimentacao TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                       CONSTRAINT fk_movimentacoes_produto
                                           FOREIGN KEY (produto_id) REFERENCES produtos(id),

                                       CONSTRAINT ck_movimentacoes_tipo
                                           CHECK (tipo_movimentacao IN ('ENTRADA', 'SAIDA')),

                                       CONSTRAINT ck_movimentacoes_quantidade
                                           CHECK (quantidade > 0)
);