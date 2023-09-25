package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.Cliente;
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

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDataNascimento(dataNascimento);
        cliente.setEndereco(endereco.toEndereco());
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        return cliente;
    }

    public static ClienteEntity toClienteEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf());
        clienteEntity.setDataNascimento(cliente.getDataNascimento());
        clienteEntity.setEndereco(EnderecoEntity.toEnderecoEntity(cliente.getEndereco()));
        clienteEntity.setTelefone(cliente.getTelefone());
        clienteEntity.setEmail(cliente.getEmail());
        return clienteEntity;
    }
}
