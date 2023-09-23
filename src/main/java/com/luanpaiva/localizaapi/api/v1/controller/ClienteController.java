package com.luanpaiva.localizaapi.api.v1.controller;

import com.luanpaiva.localizaapi.api.v1.model.dto.ClienteDto;
import com.luanpaiva.localizaapi.api.v1.model.input.ClienteInput;
import com.luanpaiva.localizaapi.api.v1.openapi.ClienteControllerOpenApi;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.service.ClienteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDate.parse;

@RestController
@RequestMapping("/v1/clientes")
@AllArgsConstructor
public class ClienteController implements ClienteControllerOpenApi {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteDto> buscarClientePeloCpf(@PathVariable String cpf) {

        Cliente cliente = clienteService.buscarClientePeloCpf(cpf);
        ClienteDto clienteDto = mapToClienteDto(cliente);

        return ResponseEntity.ok(clienteDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteDto> salvarCliente(@RequestBody ClienteInput clienteInput) {

        String cep = clienteInput.getCep();
        LocalDate dataNascimento = formatarDataNascimento(clienteInput);

        Cliente cliente = modelMapper.map(clienteInput, Cliente.class);
        cliente.setCpf(clienteInput.getCpf().replaceAll("\\D", ""));
        cliente.setDataNascimento(dataNascimento);
        cliente = clienteService.salvarCliente(cliente, cep);
        ClienteDto clienteDto = mapToClienteDto(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
    }

    private LocalDate formatarDataNascimento(ClienteInput clienteInput) {
        String dataNascimento = clienteInput.getDataNascimento();
        return parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private ClienteDto mapToClienteDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }
}
