CREATE SEQUENCE produtos_id_seq INCREMENT BY 1;

CREATE TABLE produtos (
                          id BIGINT PRIMARY KEY DEFAULT nextval('produtos_id_seq'),
                          nome VARCHAR(300) NOT NULL CHECK (length(btrim(nome)) > 0),
                          preco NUMERIC(10, 2) NOT NULL CHECK (preco > 0),
                          estoque BIGINT NOT NULL DEFAULT 0 CHECK (estoque >= 0),
                          largura_cm INTEGER,
                          altura_cm INTEGER,
                          profundidade_cm INTEGER,
                          CONSTRAINT ck_produto_dimensoes CHECK (
                              num_nonnulls(largura_cm, altura_cm, profundidade_cm) = 0
                                  OR (
                                  num_nonnulls(largura_cm, altura_cm, profundidade_cm) = 3
                                      AND largura_cm > 0 AND altura_cm > 0 AND profundidade_cm > 0
                                  )
                              )
);