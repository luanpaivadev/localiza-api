package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Aluguel;

import java.util.Optional;

public interface AluguelRepositoryPort {

    Aluguel save(Aluguel aluguel);
    Optional<Aluguel> findById(Long id);
}
