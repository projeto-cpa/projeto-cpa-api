-- USE cpa;
-- Popula o banco de dados

-- cargo
INSERT INTO cargo (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Administrador',
    'Administrador'
);

INSERT INTO cargo (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Diretor',
    'Diretor'
);

INSERT INTO cargo (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Coordenador',
    'Coordenador'
);

INSERT INTO cargo (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Professor',
    'Professor'
);

INSERT INTO cargo (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Aluno',
    'Aluno'
);

-- curso
INSERT INTO curso (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Analise e desenvolvimento de sistemas',
    'Analise e desenvolvimento de sistemas'
);

INSERT INTO curso (
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	0,
    current_date(),
    current_date(),
    'Engenharia de software',
    'Engenharia de software'
);

-- turma ads
INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	1,
	0,
    current_date(),
    current_date(),
    '1º Periodo - ADS',
    '1º Periodo - ADS'
);

INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	1,
	0,
    current_date(),
    current_date(),
    '2º Periodo - ADS',
    '2º Periodo - ADS'
);

INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	1,
	0,
    current_date(),
    current_date(),
    '3º Periodo - ADS',
    '3º Periodo - ADS'
);

-- turma eng
INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	2,
	0,
    current_date(),
    current_date(),
    '1º Periodo - ENG',
    '1º Periodo - ENG'
);

INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	2,
	0,
    current_date(),
    current_date(),
    '2º Periodo - ENG',
    '2º Periodo - ENG'
);

INSERT INTO turma (
	id_curso,
	ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
	2,
	0,
    current_date(),
    current_date(),
    '3º Periodo - ENG',
    '3º Periodo - ENG'
);

-- usuario
-- admins
INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'lucas@email.com',
    'Lucas',
    'Oliveira',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'joao@email.com',
    'João',
    'Gama',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'juliano@email.com',
    'Juliano',
    'Henrique',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'jorge@email.com',
    'Jorge',
    'Wilchen',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'matheus@email.com',
    'Matheus',
    'Schuch',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'luka.pc.pc@gmail.com',
    'Lucas',
    'Gmail',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

INSERT INTO usuario (
	id_cargo,
    id_turma,
	ativo,
    data_criacao,
    data_atualizacao,
    data_nascimento,
    email,
    nome,
    sobrenome,
    senha
) VALUES (
	1,
	1,
	0,
    current_date(),
    current_date(),
    current_date(),
    'joaocletto@gmail.com',
    'João',
    'Gmail',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);

-- eixo
INSERT INTO eixo (
    ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
    0,
    current_date(),
    current_date(),
    'Eixo de desenvolvimento',
    'Desenvolvimento'
);

INSERT INTO eixo (
    ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
    0,
    current_date(),
    current_date(),
    'Eixo de infraestrutura',
    'Infraestrutura'
);

INSERT INTO eixo (
    ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
    0,
    current_date(),
    current_date(),
    'Eixo de gestão',
    'Gestão'
);

INSERT INTO eixo(
    ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
    0,
    current_date(),
    current_date(),
    'Eixo de negócios',
    'Negócios'
);

INSERT INTO eixo (
    ativo,
    data_criacao,
    data_atualizacao,
    descricao,
    nome
) VALUES (
    0,
    current_date(),
    current_date(),
    'Eixo de produção cultural e design',
    'Produção cultural e design'
);