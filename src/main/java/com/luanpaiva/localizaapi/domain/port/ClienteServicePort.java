package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Cliente;

public interface ClienteServicePort {

    Cliente buscarClientePeloCpf(String cpf);

    Cliente salvarCliente(Cliente cliente, String cep);
}
