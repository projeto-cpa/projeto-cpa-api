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
    'de Oliveira Neitzke',
    '$2a$12$T6WLxhXK7TiSWuRMnP/mKOasLde2GPJcbzqDn69SSRv0P/gM5vWGK'
);