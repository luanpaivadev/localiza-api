package com.luanpaiva.localizaapi.api.v1.controller;

import com.luanpaiva.localizaapi.api.v1.model.dto.AluguelDto;
import com.luanpaiva.localizaapi.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.service.AluguelService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final AluguelService aluguelService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AluguelDto> salvarAluguel(@RequestBody AluguelInput aluguelInput) {

        Aluguel aluguel = aluguelService.salvarAluguel(aluguelInput);
        AluguelDto aluguelDto = modelMapper.map(aluguel, AluguelDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelDto);
    }
}
