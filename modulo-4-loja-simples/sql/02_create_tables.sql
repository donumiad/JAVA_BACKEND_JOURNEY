CREATE TABLE clientes (
                          cpf VARCHAR(14) PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          endereco VARCHAR(150) NOT NULL
);

CREATE TABLE produtos (
                          id bigint PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          preco_unitario NUMERIC(10,2) NOT NULL,
                          estoque INT NOT NULL
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    cliente_cpf VARCHAR(14) NOT NULL,
    data_pedido DATE NOT NULL,
    FOREIGN KEY (cliente_cpf) REFERENCES clientes(cpf)
);

CREATE TABLE itens_pedido (
      pedido_id BIGINT NOT NULL,
      produto_id BIGINT NOT NULL,
      quantidade INT NOT NULL,
      valor_unit NUMERIC(10,2) NOT NULL,
      CONSTRAINT pk_itens_pedido
          PRIMARY KEY (pedido_id, produto_id),
      CONSTRAINT fk_itens_pedido_pedidos
          FOREIGN KEY (pedido_id)
              REFERENCES pedidos(id),
      CONSTRAINT fk_itens_pedido_produtos
          FOREIGN KEY (produto_id)
              REFERENCES produtos(id)
);