package com.luanpaiva.localizaapi.adapter.input.api.v1.model.input;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ClienteInput {

    private String nome;
    @CPF
    private String cpf;
    private String dataNascimento;
    private String telefone;
    @Email
    private String email;
    @Length(min = 8, max = 8)
    private String cep;
}
