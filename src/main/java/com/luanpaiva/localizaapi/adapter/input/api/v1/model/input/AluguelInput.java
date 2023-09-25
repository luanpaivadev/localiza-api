package com.luanpaiva.localizaapi.adapter.input.api.v1.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AluguelInput {

    @NotBlank
    private String cpfCliente;
    @NotBlank
    private String placaVeiculo;
    @NotNull
    private LocalDateTime dataHoraRetirada;
    @NotNull
    private LocalDateTime dataHoraDevolucao;
}
