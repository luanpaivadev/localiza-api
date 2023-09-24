package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.AluguelRepositoryJpa;
import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.AluguelEntity;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.AluguelRepositoryPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AluguelRepositoryJpaAdapter implements AluguelRepositoryPort {

    private final AluguelRepositoryJpa aluguelRepositoryJpa;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Aluguel save(Aluguel aluguel) {
        AluguelEntity aluguelEntity = modelMapper.map(aluguel, AluguelEntity.class);
        aluguelRepositoryJpa.save(aluguelEntity);
        return modelMapper.map(aluguelEntity, Aluguel.class);
    }

    @Override
    public Optional<Aluguel> findById(Long id) {
        Optional<AluguelEntity> aluguelEntityOptional = aluguelRepositoryJpa.findById(id);
        return aluguelEntityOptional.map(aluguelEntity -> modelMapper.map(aluguelEntity, Aluguel.class));
    }
}
