
--ATUALIZA PREÇO E ESTOQUE DA TABELA PRODUTO
SELECT id, preco, estoque FROM produto WHERE id = 1;

UPDATE produto
SET preco = 25,
    estoque = 40
WHERE id = 1;

SELECT id,nome FROM cliente WHERE id = 1;

--ATUALIZA NOME DA TABELA CLIENTE NA COLUNA NOME
SELECT id, nome FROM cliente WHERE id = 1;

UPDATE cliente
SET nome = 'Raimundo Alves'
WHERE id = 1;

SELECT id,nome FROM cliente WHERE id = 1;
