package com.luanpaiva.localizaapi.api.v1.model.input;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AluguelInput {

    private String cpfCliente;
    private String placaVeiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
}
