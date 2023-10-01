package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Veiculo;

import java.math.BigDecimal;
import java.util.List;

public interface VeiculoServicePort {

    List<Veiculo> buscarListaVeiculos();

    Veiculo buscarVeiculoPelaPlaca(String placa);

    Veiculo salvarVeiculo(Veiculo veiculo);

    Veiculo atualizarValorDiariaReserva(String placa, BigDecimal valorDiariaVeiculo);
}
