CREATE TABLE public.tbl_endereco (
	id bigserial NOT NULL,
	bairro varchar(255) NOT NULL,
	cep varchar(255) NOT NULL,
	complemento varchar(255) NULL,
	localidade varchar(255) NOT NULL,
	logradouro varchar(255) NOT NULL,
	uf varchar(255) NOT NULL,
	CONSTRAINT tbl_endereco_pkey PRIMARY KEY (id)
);