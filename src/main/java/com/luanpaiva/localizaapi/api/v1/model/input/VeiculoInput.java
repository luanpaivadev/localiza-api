package com.luanpaiva.localizaapi.api.v1.model.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VeiculoInput {

    private String fabricante;
    private String modelo;
    private String anoFabricacao;
    private String cor;
    private String placa;
    private BigDecimal valorDiariaAluguel;
}
