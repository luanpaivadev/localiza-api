package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Endereco;
import lombok.Data;

@Data
public class EnderecoDto {

    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public static EnderecoDto toEnderecoDto(Endereco endereco) {
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setId(endereco.getId());
        enderecoDto.setCep(endereco.getCep());
        enderecoDto.setLogradouro(endereco.getLogradouro());
        enderecoDto.setComplemento(endereco.getComplemento());
        enderecoDto.setBairro(endereco.getBairro());
        enderecoDto.setLocalidade(endereco.getLocalidade());
        enderecoDto.setUf(endereco.getUf());
        return enderecoDto;
    }
}
