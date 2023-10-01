package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.domain.exception.ClienteJaCadastradoException;
import com.luanpaiva.localizaapi.domain.exception.ClienteNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.model.Endereco;
import com.luanpaiva.localizaapi.domain.port.CepServicePort;
import com.luanpaiva.localizaapi.domain.port.ClienteRespositoryPort;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Optional;

import static java.text.MessageFormat.format;

public class ClienteService implements ClienteServicePort {

    private Counter qtdClientesCadastrados;

    private static final String CLIENTE_JA_POSSUI_CADASTRO = "Cliente já possui cadastro.";
    private static final String CLIENTE_COM_CPF_0_NAO_LOCALIZADO = "Cliente com CPF {0} não localizado.";

    private final ClienteRespositoryPort clienteRespositoryPort;
    private final CepServicePort cepServicePort;


    public ClienteService(ClienteRespositoryPort clienteRespositoryPort, CepServicePort cepServicePort, MeterRegistry meterRegistry) {
        this.clienteRespositoryPort = clienteRespositoryPort;
        this.cepServicePort = cepServicePort;
        this.qtdClientesCadastrados = Counter.builder("qtd_clientes_cadastrados")
                .description("Quantidade de clientes cadastrados")
                .register(meterRegistry);
    }

    @Override
    public Cliente buscarClientePeloCpf(String cpf) {
        Optional<Cliente> cliente = clienteRespositoryPort.findByCpf(cpf);
        return cliente.orElseThrow(() -> new ClienteNaoLocalizadoException(
                format(CLIENTE_COM_CPF_0_NAO_LOCALIZADO, cpf)));
    }

    @Override
    public Cliente salvarCliente(Cliente cliente, String cep) {

        clienteRespositoryPort.findByCpf(cliente.getCpf()).ifPresent(c -> {
            throw new ClienteJaCadastradoException(CLIENTE_JA_POSSUI_CADASTRO);
        });

        Endereco endereco = cepServicePort.consultarEnderecoPorCep(cep);
        cliente.setEndereco(endereco);
        cliente = clienteRespositoryPort.save(cliente);
        qtdClientesCadastrados.increment();
        return cliente;
    }
}
