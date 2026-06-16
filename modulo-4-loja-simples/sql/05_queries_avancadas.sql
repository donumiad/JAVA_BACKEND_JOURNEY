-- Pedidos com nome do cliente
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido
FROM pedido p
         JOIN cliente c ON p.cliente_id = c.id
ORDER BY p.id;


-- Itens de pedido com nome do produto
SELECT
    ip.produto_id AS item_id,
    ip.pedido_id,
    pr.nome AS produto,
    ip.quantidade,
    ip.valor_unit
FROM itens_pedidos ip
         JOIN produto pr ON ip.produto_id = pr.id
ORDER BY ip.pedido_id;


-- Pedido completo com cliente, produto e subtotal
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido,
    pr.nome AS produto,
    ip.quantidade,
    ip.valor_unit,
    ip.quantidade * ip.valor_unit AS subtotal
FROM pedido p
         JOIN cliente c ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
         JOIN produto pr ON ip.produto_id = pr.id
ORDER BY p.id;


-- Total por pedido
SELECT
    p.id AS pedido_id,
    c.nome AS cliente,
    p.data_pedido,
    SUM(ip.quantidade * ip.valor_unit) AS total_pedido
FROM pedido p
         JOIN cliente c ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
GROUP BY p.id, c.nome, p.data_pedido
ORDER BY p.id;


-- Total gasto por cliente
SELECT
    c.id AS cliente_id,
    c.nome AS cliente,
    SUM(ip.quantidade * ip.valor_unit) AS total_gasto
FROM cliente c
         JOIN pedido p ON p.cliente_id = c.id
         JOIN itens_pedidos ip ON ip.pedido_id = p.id
GROUP BY c.id, c.nome
ORDER BY total_gasto DESC;


-- Produtos mais vendidos
SELECT
    pr.id AS produto_id,
    pr.nome AS produto,
    SUM(ip.quantidade) AS total_vendido
FROM produto pr
         JOIN itens_pedidos ip ON ip.produto_id = pr.id
GROUP BY pr.id, pr.nome
ORDER BY total_vendido DESC;