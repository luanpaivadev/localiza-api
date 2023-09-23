package com.luanpaiva.localizaapi.domain.service;

import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public List<Veiculo> buscarListaVeiculos() {
        return veiculoRepository.findAll();
    }

    public Veiculo buscarVeiculoPelaPlaca(String placa) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findByPlaca(placa);
        return veiculoOptional.orElseThrow(() -> new VeiculoNaoLocalizadoException(
                format("Veículo com placa {0} não localizado.", placa)));
    }

    @Transactional
    public Veiculo salvarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public Veiculo atualizarValorDiariaAluguel(String placa, BigDecimal valorDiariaVeiculo) {
        Veiculo veiculo = buscarVeiculoPelaPlaca(placa);
        veiculo.setValorDiariaAluguel(valorDiariaVeiculo);
        return salvarVeiculo(veiculo);
    }
}
