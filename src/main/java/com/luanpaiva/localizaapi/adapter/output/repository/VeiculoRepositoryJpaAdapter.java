package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.VeiculoRepositoryJpa;
import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.VeiculoEntity;
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
        List<VeiculoEntity> veiculoEntityList = veiculoRepositoryJpa.findAll();
        return veiculoEntityList.stream().map(VeiculoEntity::toVeiculo).toList();
    }

    @Override
    @Transactional
    public Veiculo save(Veiculo veiculo) {
        VeiculoEntity veiculoEntity = VeiculoEntity.toVeiculoEntity(veiculo);
        veiculoRepositoryJpa.save(veiculoEntity);
        return veiculoEntity.toVeiculo();
    }

    @Override
    public Optional<Veiculo> findByPlaca(String placa) {
        Optional<VeiculoEntity> veiculoEntityOptional = veiculoRepositoryJpa.findByPlaca(placa);
        return veiculoEntityOptional.map(VeiculoEntity::toVeiculo);
    }
}
