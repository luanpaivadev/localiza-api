CREATE TABLE public.tbl_cliente (
	id bigserial NOT NULL,
	cpf varchar(255) NOT NULL,
	data_nascimento date NOT NULL,
	email varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(255) NULL,
	endereco_id int8 NOT NULL,
	CONSTRAINT tbl_cliente_pkey PRIMARY KEY (id),
	CONSTRAINT uk_13o8l25m1x1rnd39k5acqt82t UNIQUE (cpf),
	CONSTRAINT uk_22flsvn266bk5yhegeh2ge17c UNIQUE (endereco_id),
	CONSTRAINT uk_60sdd7i2fhb6mwyrmkk2d6u5k UNIQUE (email)
);

ALTER TABLE public.tbl_cliente ADD CONSTRAINT fkja26shtbty51kgoljrys855ei FOREIGN KEY (endereco_id) REFERENCES public.tbl_endereco(id);