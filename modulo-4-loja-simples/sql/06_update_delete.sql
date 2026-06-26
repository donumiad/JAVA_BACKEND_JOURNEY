
--ATUALIZA PREÇO E ESTOQUE DA TABELA PRODUTO
SELECT
    id,
    preco,
    estoque
FROM produtos
WHERE id = 1;

UPDATE produtos
SET preco = 25,
    estoque = 40
WHERE id = 1;

SELECT
    id,
    nome
FROM clientes
WHERE id = 1;

--ATUALIZA NOME DA TABELA CLIENTE NA COLUNA NOME
SELECT
    id,
    nome
FROM clientes
WHERE id = 1;

UPDATE clientes
SET nome = 'Raimundo Alves'
WHERE id = 1;

SELECT
    id,
    nome
FROM clientes
WHERE id = 1;
