package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.StatusAluguel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AluguelDto {

    private Long id;
    private ClienteDto cliente;
    private VeiculoDto veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoEfetivada;
    private BigDecimal valorPrevisto;
    private BigDecimal valorExcedente;
    private BigDecimal valorTotal;
    private StatusAluguel statusAluguel;
}
