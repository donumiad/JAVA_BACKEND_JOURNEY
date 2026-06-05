INSERT INTO clientes (cpf, nome, email, endereco) VALUES
  ('12345678900', 'Raimundo Neto', 'raimundo@email.com', 'Ender Rai'),
  ('96385274199', 'Maria Oliveira', 'maria@email.com', 'Ender Maria'),
  ('74185296377', 'Junior Carvalho', 'junior@email.com', 'Ender Junior');

INSERT INTO produtos (id, nome, preco_unitario, estoque) VALUES
    (1, 'Produto 1', 15.00, 5),
    (2, 'Produto 2', 25.00, 6),
    (3, 'Produto 3', 19.00, 9);

INSERT INTO pedidos (id, descricao, data_pedido, cliente_cpf) VALUES
   (4,'pedido teste 1', '2026-08-08', '12345678900'),
   (5,'pedido teste 2', '2026-06-05', '12345678900'),
   (6,'pedido teste 3', '2026-07-03', '74185296377');

INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, valor_unit) VALUES
 (4, 1, 3, 15.00),
 (4, 3, 5, 19.00),
 (6, 3, 4, 19.00),
 (5, 2, 3, 25.00);


