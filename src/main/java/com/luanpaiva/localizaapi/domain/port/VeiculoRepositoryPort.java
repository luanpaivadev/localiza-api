package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepositoryPort {

    List<Veiculo> findAll();

    Veiculo save(Veiculo veiculo);

    Optional<Veiculo> findByPlaca(String placa);
}
