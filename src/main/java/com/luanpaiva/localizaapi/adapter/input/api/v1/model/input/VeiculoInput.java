package com.luanpaiva.localizaapi.adapter.input.api.v1.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class VeiculoInput {

    @NotBlank
    private String fabricante;
    @NotBlank
    private String modelo;
    @NotBlank
    @Length(min = 4, max = 4)
    private String anoFabricacao;
    @NotBlank
    private String cor;
    @NotBlank
    @Length(min = 7, max = 8)
    private String placa;
    @NotNull
    private BigDecimal valorDiariaAluguel;
}
