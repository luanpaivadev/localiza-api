package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.ClienteDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ClienteInput;
import com.luanpaiva.localizaapi.adapter.input.api.v1.openapi.ClienteControllerOpenApi;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.port.ClienteServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/clientes")
@AllArgsConstructor
public class ClienteController implements ClienteControllerOpenApi {

    private final ClienteServicePort clienteServicePort;

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDto> buscarClientePeloCpf(@PathVariable String cpf) {

        Cliente cliente = clienteServicePort.buscarClientePeloCpf(cpf);
        ClienteDto clienteDto = ClienteDto.toClienteDto(cliente);

        return ResponseEntity.ok(clienteDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteDto> salvarCliente(@RequestBody @Valid ClienteInput clienteInput) {

        String cep = clienteInput.getCep();
        Cliente cliente = clienteInput.toCliente();
        cliente = clienteServicePort.salvarCliente(cliente, cep);
        ClienteDto clienteDto = ClienteDto.toClienteDto(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

}
