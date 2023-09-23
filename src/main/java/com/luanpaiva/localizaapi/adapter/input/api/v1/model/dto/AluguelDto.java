package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.model.Cliente;
import com.luanpaiva.localizaapi.domain.model.StatusAluguel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AluguelDto {

    private Long id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
    private BigDecimal valor;
    private StatusAluguel statusAluguel;
}
