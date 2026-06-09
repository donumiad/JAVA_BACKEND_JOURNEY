CREATE TABLE clientes (
    id BIGINT PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(150) NOT NULL
);

CREATE TABLE produtos (
                          id bigint PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          preco NUMERIC(10,2) NOT NULL,
                          estoque INT NOT NULL
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    quantidade BIGINT NOT NULL,
    cliente_id VARCHAR(14) NOT NULL,
    produto_id BIGINT NOT NULL,
    data_pedido DATE NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE itens_pedido (
      pedido_id BIGSERIAL NOT NULL,
      produto_id BIGSERIAL NOT NULL,
      quantidade INT NOT NULL,
      valor_unit NUMERIC(10,2) NOT NULL,
      CONSTRAINT pk_itens_pedido
          PRIMARY KEY (pedido_id, produto_id),
      CONSTRAINT fk_itens_pedido_pedidos
          FOREIGN KEY (pedido_id)
              REFERENCES pedido(id),
      CONSTRAINT fk_itens_pedido_produtos
          FOREIGN KEY (produto_id)
              REFERENCES produtos(id)
);