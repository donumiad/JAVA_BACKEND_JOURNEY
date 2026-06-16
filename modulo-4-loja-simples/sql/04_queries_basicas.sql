SELECT * FROM cliente;

SELECT * FROM produto;

SELECT * FROM pedido;

SELECT * FROM itens_pedidos;

SELECT id, nome, email
FROM cliente
ORDER BY nome;

SELECT id, nome, preco, estoque
FROM produto
WHERE estoque < 10
ORDER BY estoque;