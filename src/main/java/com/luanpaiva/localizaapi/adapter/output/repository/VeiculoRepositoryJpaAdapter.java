package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.VeiculoRepositoryJpa;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.VeiculoRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VeiculoRepositoryJpaAdapter implements VeiculoRepositoryPort {

    private final VeiculoRepositoryJpa veiculoRepositoryJpa;

    @Override
    public List<Veiculo> findAll() {
        return veiculoRepositoryJpa.findAll();
    }

    @Override
    @Transactional
    public Veiculo save(Veiculo veiculo) {
        return veiculoRepositoryJpa.save(veiculo);
    }

    @Override
    public Optional<Veiculo> findByPlaca(String placa) {
        return veiculoRepositoryJpa.findByPlaca(placa);
    }
}
