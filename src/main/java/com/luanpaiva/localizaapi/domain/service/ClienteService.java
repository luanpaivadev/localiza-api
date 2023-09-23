package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.domain.exception.ClienteNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.repository.ClienteRespository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRespository clienteRespository;
    private final CepService cepService;

    public Cliente buscarClientePeloCpf(String cpf) {
        Optional<Cliente> cliente = clienteRespository.findByCpf(cpf);
        return cliente.orElseThrow(() -> new ClienteNaoLocalizadoException(format("Cliente com CPF {0} não localizado.", cpf)));
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente, String cep) {
        Cliente.Endereco endereco = cepService.consultarEnderecoPorCep(cep);
        cliente.setEndereco(endereco);
        return clienteRespository.save(cliente);
    }
}
