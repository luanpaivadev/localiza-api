package com.luanpaiva.localizaapi.api.v1.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VeiculoDto {

    private Long id;
    private String fabricante;
    private String modelo;
    private String anoFabricacao;
    private String cor;
    private String placa;
    private BigDecimal valorDiariaAluguel;
    private Boolean disponivel;
}
