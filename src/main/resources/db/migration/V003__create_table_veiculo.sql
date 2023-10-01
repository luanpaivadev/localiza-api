CREATE TABLE public.tbl_veiculo (
	id bigserial NOT NULL,
	ano_fabricacao varchar(4) NOT NULL,
	cor varchar(255) NOT NULL,
	disponivel bool NOT NULL,
	fabricante varchar(255) NOT NULL,
	modelo varchar(255) NOT NULL,
	placa varchar(8) NOT NULL,
	valor_diaria_reserva numeric(38, 2) NULL,
	CONSTRAINT tbl_veiculo_pkey PRIMARY KEY (id),
	CONSTRAINT uk_4nf3cdncytit2du4oq0vex6gp UNIQUE (placa)
);