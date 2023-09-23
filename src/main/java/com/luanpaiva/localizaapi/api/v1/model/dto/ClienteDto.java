package com.luanpaiva.localizaapi.api.v1.model.dto;

import lombok.Data;

@Data
public class ClienteDto {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private EnderecoDto endereco;
    private String telefone;
    private String email;

    @Data
    static class EnderecoDto {

        private Long id;
        private String cep;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade;
        private String uf;
    }
}
