package com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto;

import com.luanpaiva.localizaapi.domain.model.Veiculo;
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
    private BigDecimal valorDiariaReserva;
    private Boolean disponivel;

    public static VeiculoDto toVeiculoDto(Veiculo veiculo) {
        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setId(veiculo.getId());
        veiculoDto.setFabricante(veiculo.getFabricante());
        veiculoDto.setModelo(veiculo.getModelo());
        veiculoDto.setAnoFabricacao(veiculo.getAnoFabricacao());
        veiculoDto.setCor(veiculo.getCor());
        veiculoDto.setPlaca(veiculo.getPlaca());
        veiculoDto.setValorDiariaReserva(veiculo.getValorDiariaReserva());
        veiculoDto.setDisponivel(veiculo.getDisponivel());
        return veiculoDto;
    }
}
