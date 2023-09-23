package com.luanpaiva.localizaapi.adapter.output.repository;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.AluguelRepositoryJpa;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.AluguelRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class AluguelRepositoryJpaAdapter implements AluguelRepositoryPort {

    private final AluguelRepositoryJpa aluguelRepositoryJpa;

    @Override
    @Transactional
    public Aluguel save(Aluguel aluguel) {
        return aluguelRepositoryJpa.save(aluguel);
    }
}
