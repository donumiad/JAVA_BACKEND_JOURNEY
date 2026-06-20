CREATE TABLE clientes (
          id BIGSERIAL PRIMARY KEY,
          nome VARCHAR(150) NOT NULL,
          cpf VARCHAR(14) NOT NULL UNIQUE,
          email VARCHAR(150) NOT NULL UNIQUE,
          endereco VARCHAR(300) NOT NULL
);

CREATE TABLE produtos (
          id BIGSERIAL PRIMARY KEY,
          nome VARCHAR(300) NOT NULL,
          preco NUMERIC(10,2) NOT NULL CHECK ( preco >= 0 ),
          estoque BIGINT NOT NULL CHECK ( estoque >= 0 )
);

CREATE TABLE pedidos (
         id BIGSERIAL PRIMARY KEY,
         cliente_id BIGINT NOT NULL,
         data_pedido DATE NOT NULL CHECK ( data_pedido >= now() ),

         CONSTRAINT fk_pedido_cliente
             FOREIGN KEY (cliente_id)
                 REFERENCES clientes(id)

);

CREATE TABLE itens_pedidos (
       pedido_id BIGINT NOT NULL,
       produto_id BIGINT NOT NULL,
       quantidade BIGINT NOT NULL CHECK ( quantidade > 0 ),
       preco_unitario NUMERIC(10,2) NOT NULL,

       CONSTRAINT pk_itens_pedidos
           PRIMARY KEY (pedido_id,produto_id),
       CONSTRAINT fk_itens_pedido
           FOREIGN KEY (pedido_id)
               REFERENCES pedidos(id),
       CONSTRAINT fk_itens_produto
           FOREIGN KEY (produto_id)
               REFERENCES produtos(id)

);