package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservaDto {

    private Long id;
    private ClienteDto cliente;
    private VeiculoDto veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoEfetivada;
    private BigDecimal valorPrevisto;
    private BigDecimal valorExcedente;
    private BigDecimal valorTotal;
    private StatusReserva statusReserva;

    public static ReservaDto toReservaDto(Reserva reserva) {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setId(reserva.getId());
        reservaDto.setCliente(ClienteDto.toClienteDto(reserva.getCliente()));
        reservaDto.setVeiculo(VeiculoDto.toVeiculoDto(reserva.getVeiculo()));
        reservaDto.setDataHoraRetirada(reserva.getDataHoraRetirada());
        reservaDto.setDataHoraDevolucaoPrevista(reserva.getDataHoraDevolucaoPrevista());
        reservaDto.setDataHoraDevolucaoEfetivada(reserva.getDataHoraDevolucaoEfetivada());
        reservaDto.setValorPrevisto(reserva.getValorPrevisto());
        reservaDto.setValorExcedente(reserva.getValorExcedente());
        reservaDto.setValorTotal(reserva.getValorTotal());
        reservaDto.setStatusReserva(reserva.getStatusReserva());
        return reservaDto;
    }
}
