package com.luanpaiva.localizaapi.adapter.input.api.v1.model.input;

import com.luanpaiva.localizaapi.domain.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

@Data
public class ClienteInput {

    @NotBlank
    private String nome;
    @CPF
    @NotBlank
    private String cpf;
    @NotBlank
    private String dataNascimento;
    @NotBlank
    private String telefone;
    @Email
    @NotBlank
    private String email;
    @Length(min = 8, max = 8)
    @NotBlank
    private String cep;

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf.replaceAll("\\D", ""));
        cliente.setDataNascimento(formatarDataNascimento(dataNascimento));
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        return cliente;
    }

    private LocalDate formatarDataNascimento(String dataNascimento) {
        return parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
