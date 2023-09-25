package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Cliente;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class ClienteDto {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private EnderecoDto endereco;
    private String telefone;
    private String email;

    public static ClienteDto toClienteDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNome(cliente.getNome());
        clienteDto.setCpf(cliente.getCpf());
        clienteDto.setDataNascimento(cliente.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        clienteDto.setEndereco(EnderecoDto.toEnderecoDto(cliente.getEndereco()));
        clienteDto.setTelefone(cliente.getTelefone());
        clienteDto.setEmail(cliente.getEmail());
        return clienteDto;
    }
}
