-- Pedidos com nome do cliente
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido
FROM pedidos p
         JOIN clientes c ON p.cliente_id = c.id
ORDER BY p.id;


-- Itens de pedido com nome do produto
SELECT
    ip.produto_id AS item_id,
    ip.pedido_id,
    pr.nome AS produto,
    ip.quantidade,
    ip.preco_unitario
FROM itens_pedidos ip
JOIN produtos pr ON ip.produto_id = pr.id
ORDER BY ip.pedido_id;


-- Pedido completo com cliente, produto e subtotal
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido,
    pr.nome AS produto,
    ip.quantidade,
    ip.preco_unitario,
    ip.quantidade * ip.preco_unitario AS subtotal
FROM pedidos p
         JOIN clientes c ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
         JOIN produtos pr ON ip.produto_id = pr.id
ORDER BY p.id;


-- Total por pedido
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido,
    SUM(ip.quantidade * ip.preco_unitario) AS total_pedido
FROM pedidos p
         JOIN clientes c ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
GROUP BY p.id, c.nome, p.data_pedido
ORDER BY p.id;


-- Total gasto por cliente
SELECT
    c.id AS cliente_id,
    c.nome AS cliente,
    SUM(ip.quantidade * ip.preco_unitario) AS total_gasto
FROM clientes c
         JOIN pedidos p ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
GROUP BY c.id, c.nome
ORDER BY total_gasto DESC;


-- Produtos mais vendidos
SELECT
    pr.id AS produto_id,
    pr.nome AS produto,
    SUM(ip.quantidade) AS total_vendido
FROM produtos pr
         JOIN itens_pedidos ip ON ip.produto_id = pr.id
GROUP BY pr.id, pr.nome
ORDER BY total_vendido DESC;