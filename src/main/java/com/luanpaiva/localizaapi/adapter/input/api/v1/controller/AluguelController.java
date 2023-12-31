package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.AluguelDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.AluguelServicePort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/alugueis")
@AllArgsConstructor
public class AluguelController {

    private final AluguelServicePort aluguelServicePort;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AluguelDto> salvarAluguel(@RequestBody AluguelInput aluguelInput) {

        Aluguel aluguel = aluguelServicePort.salvarAluguel(aluguelInput);
        AluguelDto aluguelDto = toDtoObject(aluguel);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelDto> finalizarAluguel(@PathVariable Long id,
                                                       @RequestParam LocalDateTime dataHoraDevolucaoEfetivada) {

        Aluguel aluguel = aluguelServicePort.finalizarAluguel(id, dataHoraDevolucaoEfetivada);
        AluguelDto aluguelDto = toDtoObject(aluguel);

        return ResponseEntity.ok(aluguelDto);
    }

    private AluguelDto toDtoObject(Aluguel aluguel) {
        return modelMapper.map(aluguel, AluguelDto.class);
    }
}
