package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.AluguelDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.AluguelServicePort;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/alugueis")
@AllArgsConstructor
public class AluguelController {

    private final AluguelServicePort aluguelServicePort;
    private final ModelMapperPort<Aluguel, AluguelDto> toDtoObject;

    @PostMapping
    public ResponseEntity<AluguelDto> salvarAluguel(@RequestBody AluguelInput aluguelInput) {

        Aluguel aluguel = aluguelServicePort.salvarAluguel(aluguelInput);
        AluguelDto aluguelDto = toDtoObject.map(aluguel, AluguelDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelDto);
    }
}
