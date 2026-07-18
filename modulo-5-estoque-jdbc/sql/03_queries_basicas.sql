SELECT * FROM clientes;

SELECT * FROM produtos;

SELECT * FROM pedidos;

SELECT * FROM itens_pedidos;

SELECT id, nome, email
FROM clientes
ORDER BY nome;

SELECT id, nome, preco, estoque
FROM produtos
WHERE estoque < 10
ORDER BY estoque;