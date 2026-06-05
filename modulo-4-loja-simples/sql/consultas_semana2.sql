SELECT * FROM produtos WHERE preco_unitario > 20.00;
SELECT * FROM clientes WHERE nome LIKE 'Rai%';
SELECT * FROM clientes WHERE cpf = '12345678900';
SELECT * FROM pedidos WHERE cliente_cpf = '12345678900';
SELECT * FROM produtos WHERE estoque < 5;