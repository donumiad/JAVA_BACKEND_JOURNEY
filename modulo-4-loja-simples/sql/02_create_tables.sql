CREATE TABLE clientes (
    id BIGINT PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco VARCHAR(150) NOT NULL
);

CREATE TABLE produtos (
      id BIGSERIAL PRIMARY KEY,
      nome VARCHAR(100) NOT NULL,
      preco NUMERIC(10,2) NOT NULL CHECK ( preco >= 0 ),
      estoque INT NOT NULL CHECK ( estoque >= 0 )
);

CREATE TABLE pedido (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL ,
    data_pedido DATE NOT NULL CHECK ( data_pedido <= now() ),

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
);

CREATE TABLE itens_pedido (
      pedido_id BIGINT NOT NULL,
      produto_id BIGINT NOT NULL,
      quantidade INT NOT NULL CHECK ( quantidade > 0 ),
      valor_unit NUMERIC(10,2) NOT NULL CHECK ( valor_unit >=0 ),
      CONSTRAINT pk_itens_pedido
          PRIMARY KEY (pedido_id, produto_id),
      CONSTRAINT fk_itens_pedido_pedidos
          FOREIGN KEY (pedido_id)
              REFERENCES pedido(id),
      CONSTRAINT fk_itens_pedido_produtos
          FOREIGN KEY (produto_id)
              REFERENCES produtos(id)
);