package com.luanpaiva.localizaapi.adapter.input.api.v1.model.input;

import lombok.Data;

@Data
public class ClienteInput {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String telefone;
    private String email;
    private String cep;
}
