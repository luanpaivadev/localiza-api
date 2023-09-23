package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.VeiculoRepositoryPort;
import com.luanpaiva.localizaapi.domain.port.VeiculoServicePort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

public class VeiculoService implements VeiculoServicePort {

    private final VeiculoRepositoryPort veiculoRepositoryPort;

    public VeiculoService(VeiculoRepositoryPort veiculoRepositoryPort) {
        this.veiculoRepositoryPort = veiculoRepositoryPort;
    }

    @Override
    public List<Veiculo> buscarListaVeiculos() {
        return veiculoRepositoryPort.findAll();
    }

    public Veiculo buscarVeiculoPelaPlaca(String placa) {
        Optional<Veiculo> veiculoOptional = veiculoRepositoryPort.findByPlaca(placa);
        return veiculoOptional.orElseThrow(() -> new VeiculoNaoLocalizadoException(
                format("Veículo com placa {0} não localizado.", placa)));
    }

    @Override
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepositoryPort.save(veiculo);
    }

    @Override
    public Veiculo atualizarValorDiariaAluguel(String placa, BigDecimal valorDiariaVeiculo) {
        Veiculo veiculo = buscarVeiculoPelaPlaca(placa);
        veiculo.setValorDiariaAluguel(valorDiariaVeiculo);
        return salvarVeiculo(veiculo);
    }
}
