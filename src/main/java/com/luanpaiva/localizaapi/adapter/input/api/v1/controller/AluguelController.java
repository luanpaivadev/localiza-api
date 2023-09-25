package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.AluguelDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.AluguelServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping
    public ResponseEntity<AluguelDto> salvarReserva(@RequestBody @Valid AluguelInput aluguelInput) {

        Aluguel aluguel = aluguelServicePort.salvarReserva(aluguelInput);
        AluguelDto aluguelDto = AluguelDto.toAluguelDto(aluguel);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelDto> finalizarAluguel(@PathVariable Long id,
                                                       @RequestParam LocalDateTime dataHoraDevolucaoEfetivada) {

        Aluguel aluguel = aluguelServicePort.finalizarAluguel(id, dataHoraDevolucaoEfetivada);
        AluguelDto aluguelDto = AluguelDto.toAluguelDto(aluguel);

        return ResponseEntity.ok(aluguelDto);
    }
}
