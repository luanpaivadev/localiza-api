package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.VeiculoDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.VeiculoInput;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/veiculos")
@AllArgsConstructor
public class VeiculoController {

    private final VeiculoServicePort veiculoServicePort;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> listarVeiculos() {

        List<Veiculo> veiculos = veiculoServicePort.buscarListaVeiculos();
        List<VeiculoDto> veiculoDtoList = veiculos.stream()
                .map(veiculo -> modelMapper.map(veiculo, VeiculoDto.class))
                .toList();

        return ResponseEntity.ok(veiculoDtoList);
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> cadastrarNovoVeiculo(@RequestBody @Valid VeiculoInput veiculoInput) {

        Veiculo veiculo = modelMapper.map(veiculoInput, Veiculo.class);
        veiculo = veiculoServicePort.salvarVeiculo(veiculo);
        VeiculoDto veiculoDto = modelMapper.map(veiculo, VeiculoDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoDto);
    }

    @PutMapping("/{placa}/atualizar-valor-diaria")
    public ResponseEntity<VeiculoDto> atualizarValorDiariaVeiculo(@PathVariable String placa,
                                                                  @RequestParam BigDecimal valorDiariaVeiculo) {

        Veiculo veiculo = veiculoServicePort.atualizarValorDiariaAluguel(placa, valorDiariaVeiculo);
        VeiculoDto veiculoDto = modelMapper.map(veiculo, VeiculoDto.class);

        return ResponseEntity.ok(veiculoDto);
    }
}
