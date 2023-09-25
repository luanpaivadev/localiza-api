package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tbl_endereco")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco toEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setUf(uf);
        return endereco;
    }

    public static EnderecoEntity toEnderecoEntity(Endereco endereco) {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(endereco.getId());
        enderecoEntity.setCep(endereco.getCep());
        enderecoEntity.setLogradouro(endereco.getLogradouro());
        enderecoEntity.setComplemento(endereco.getComplemento());
        enderecoEntity.setBairro(endereco.getBairro());
        enderecoEntity.setLocalidade(endereco.getLocalidade());
        enderecoEntity.setUf(endereco.getUf());
        return enderecoEntity;
    }
}
