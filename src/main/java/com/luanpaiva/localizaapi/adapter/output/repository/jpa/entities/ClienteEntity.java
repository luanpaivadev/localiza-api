package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
    private String telefone;
    private String email;

    @Data
    @Entity
    @Table(name = "tbl_endereco")
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class EnderecoEntity {

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
    }
}
