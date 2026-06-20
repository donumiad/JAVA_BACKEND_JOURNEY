-- =========================
-- CLIENTES
-- =========================

INSERT INTO clientes (nome, cpf, email, endereco) VALUES
          ('Raimundo Neto','082.491.730-84', 'raimundo.neto@email.com','apenas um endereço teste'),
          ('Ana Silva','531.064.920-11', 'ana.silva@email.com','apenas um endereço teste'),
          ('Carlos Souza','274.819.350-50', 'carlos.souza@email.com','apenas um endereço teste'),
          ('Mariana Lima','803.627.140-09', 'mariana.lima@email.com','apenas um endereço teste'),
          ('João Pereira','149.538.200-73', 'joao.pereira@email.com','apenas um endereço teste'),
          ('Fernanda Alves','620.471.950-22', 'fernanda.alves@email.com','apenas um endereço teste'),
          ('Pedro Santos','395.182.640-48', 'pedro.santos@email.com','apenas um endereço teste'),
          ('Juliana Costa','716.039.480-95', 'juliana.costa@email.com','apenas um endereço teste'),
          ('Lucas Oliveira','452.910.370-36', 'lucas.oliveira@email.com','apenas um endereço teste'),
          ('Beatriz Martins','938.254.160-67', 'beatriz.martins@email.com','apenas um endereço teste');

-- =========================
-- PRODUTOS
-- =========================

INSERT INTO produtos (nome, preco, estoque) VALUES
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

INSERT INTO pedidos (cliente_id, data_pedido) VALUES
      (1, '2026-06-20'),
      (2, '2026-06-20'),
      (3, '2026-06-22'),
      (1, '2026-06-23'),
      (4, '2026-06-23'),
      (5, '2026-06-24'),
      (6, '2026-06-25'),
      (7, '2026-06-26'),
      (8, '2026-06-27'),
      (2, '2026-06-28');


-- =========================
-- ITENS DO PEDIDO
-- =========================

INSERT INTO itens_pedidos (pedido_id, produto_id, quantidade, preco_unitario) VALUES
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