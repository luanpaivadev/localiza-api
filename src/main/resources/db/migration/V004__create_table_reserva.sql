CREATE TABLE public.tbl_reserva (
	id bigserial NOT NULL,
	data_hora_devolucao_efetivada timestamp(6) NULL,
	data_hora_devolucao_prevista timestamp(6) NOT NULL,
	data_hora_retirada timestamp(6) NOT NULL,
	status_reserva varchar(255) NOT NULL,
	valor_excedente numeric(38, 2) NULL,
	valor_previsto numeric(38, 2) NOT NULL,
	valor_total numeric(38, 2) NOT NULL,
	cliente_id int8 NOT NULL,
	veiculo_id int8 NOT NULL,
	CONSTRAINT tbl_reserva_pkey PRIMARY KEY (id),
	CONSTRAINT tbl_reserva_status_reserva_check CHECK (((status_reserva)::text = ANY ((ARRAY['ABERTO'::character varying, 'FECHADO'::character varying])::text[])))
);

ALTER TABLE public.tbl_reserva ADD CONSTRAINT fkcjeo7pm9wsk0dfxeuqbmxe57r FOREIGN KEY (veiculo_id) REFERENCES public.tbl_veiculo(id);
ALTER TABLE public.tbl_reserva ADD CONSTRAINT fkmkllhde3d19y5rl6g730vstav FOREIGN KEY (cliente_id) REFERENCES public.tbl_cliente(id);