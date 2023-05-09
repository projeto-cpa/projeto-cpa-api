SET GLOBAL log_bin_trust_function_creators = 1;

-- 1) 
CREATE FUNCTION soma(a INT, b INT) RETURNS INT RETURN a + b;

-- 2)
CREATE FUNCTION multiplica(a INT, b INT) RETURNS INT RETURN a * b;

-- 3) 
CREATE FUNCTION subtracao(a INT, b INT) RETURNS INT RETURN a - b;

-- 4)
INSERT INTO filme (filme_id, preco_da_locacao) VALUES (
    999, 
    (SELECT soma(filme.preco_da_locacao, 9.99) FROM filme)
) 

-- 5)
UPDATE filme SET preco_da_locacao = soma(preco_da_locacao , 2) WHERE filme_id = 50;
SELECT titulo, preco_da_locacao FROM filme WHERE filme_id = 30;

-- 6)
CREATE FUNCTION aumento_10(valor FLOAT)
RETURNS FLOAT
RETURN valor * 0.1;

-- 7)
UPDATE filme 
SET preco_da_locacao = aumento_10(preco_da_locacao)
WHERE filme_id = 150;

-- 8)
CREATE FUNCTION titulo_do_filme(N INT)
RETURNS VARCHAR(255)
RETURN (SELECT titulo FROM filme WHERE filme_id = N);
SELECT titulo_do_filme(2);

-- 9) 
CREATE FUNCTION nome_da_categoria(N INT)
RETURNS VARCHAR(255)
RETURN (SELECT nome FROM categoria WHERE categoria_id = N);
SELECT nome_da_categoria(2);

-- 10)
CREATE FUNCTION nome_do_autor(N INT)
RETURNS VARCHAR(255)
RETURN (SELECT nome FROM ator WHERE ator_id = N);
