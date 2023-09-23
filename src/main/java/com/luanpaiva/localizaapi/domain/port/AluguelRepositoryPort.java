package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Aluguel;

public interface AluguelRepositoryPort {

    Aluguel save(Aluguel aluguel);
}
