package com.luanpaiva.localizaapi.domain.repository;

import com.luanpaiva.localizaapi.domain.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}
