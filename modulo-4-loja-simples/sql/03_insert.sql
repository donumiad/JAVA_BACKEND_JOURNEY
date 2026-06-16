-- =========================
-- CLIENTES
-- =========================

INSERT INTO cliente (nome, email) VALUES
   ('Raimundo Neto', 'raimundo.neto@email.com'),
   ('Ana Silva', 'ana.silva@email.com'),
   ('Carlos Souza', 'carlos.souza@email.com'),
   ('Mariana Lima', 'mariana.lima@email.com'),
   ('João Pereira', 'joao.pereira@email.com'),
   ('Fernanda Alves', 'fernanda.alves@email.com'),
   ('Pedro Santos', 'pedro.santos@email.com'),
   ('Juliana Costa', 'juliana.costa@email.com'),
   ('Lucas Oliveira', 'lucas.oliveira@email.com'),
   ('Beatriz Martins', 'beatriz.martins@email.com');


-- =========================
-- PRODUTOS
-- =========================

INSERT INTO produto (nome, preco, estoque) VALUES
    ('Teclado Mecânico', 250.00, 15),
    ('Mouse Gamer', 120.00, 30),
    ('Monitor 24 Polegadas', 850.00, 8),
    ('Headset USB', 180.00, 20),
    ('Webcam Full HD', 200.00, 12),
    ('Notebook Lenovo', 3500.00, 5),
    ('Cadeira Gamer', 950.00, 6),
    ('SSD 1TB', 420.00, 18),
    ('Memória RAM 16GB', 300.00, 25),
    ('Hub USB-C', 90.00, 40);


-- =========================
-- PEDIDOS
-- =========================

INSERT INTO pedido (cliente_id, data_pedido) VALUES
      (1, '2026-06-01'),
      (2, '2026-06-01'),
      (3, '2026-06-02'),
      (1, '2026-06-03'),
      (4, '2026-06-03'),
      (5, '2026-06-04'),
      (6, '2026-06-05'),
      (7, '2026-06-06'),
      (8, '2026-06-07'),
      (2, '2026-06-08');


-- =========================
-- ITENS DO PEDIDO
-- =========================

INSERT INTO itens_pedidos (pedido_id, produto_id, quantidade, valor_unit) VALUES
     (1, 1, 1, 250.00),
     (1, 2, 2, 120.00),

     (2, 3, 1, 850.00),
     (2, 4, 1, 180.00),

     (3, 5, 2, 200.00),
     (3, 10, 1, 90.00),

     (4, 2, 1, 120.00),
     (4, 8, 1, 420.00),

     (5, 6, 1, 3500.00),
     (5, 9, 2, 300.00),

     (6, 7, 1, 950.00),
     (6, 4, 1, 180.00),

     (7, 8, 2, 420.00),
     (7, 10, 3, 90.00),

     (8, 1, 1, 250.00),
     (8, 9, 1, 300.00),

     (9, 3, 1, 850.00),
     (9, 5, 1, 200.00),

     (10, 2, 3, 120.00),
     (10, 10, 2, 90.00);