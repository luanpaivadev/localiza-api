package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Aluguel;
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

    public static AluguelDto toAluguelDto(Aluguel aluguel) {
        AluguelDto aluguelDto = new AluguelDto();
        aluguelDto.setId(aluguel.getId());
        aluguelDto.setCliente(ClienteDto.toClienteDto(aluguel.getCliente()));
        aluguelDto.setVeiculo(VeiculoDto.toVeiculoDto(aluguel.getVeiculo()));
        aluguelDto.setDataHoraRetirada(aluguel.getDataHoraRetirada());
        aluguelDto.setDataHoraDevolucaoPrevista(aluguel.getDataHoraDevolucaoPrevista());
        aluguelDto.setDataHoraDevolucaoEfetivada(aluguel.getDataHoraDevolucaoEfetivada());
        aluguelDto.setValorPrevisto(aluguel.getValorPrevisto());
        aluguelDto.setValorExcedente(aluguel.getValorExcedente());
        aluguelDto.setValorTotal(aluguel.getValorTotal());
        aluguelDto.setStatusAluguel(aluguel.getStatusAluguel());
        return aluguelDto;
    }
}
