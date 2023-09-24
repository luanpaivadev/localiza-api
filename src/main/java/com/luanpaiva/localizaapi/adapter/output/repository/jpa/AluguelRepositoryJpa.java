package com.luanpaiva.localizaapi.adapter.output.repository.jpa;

import com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities.AluguelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepositoryJpa extends JpaRepository<AluguelEntity, Long> {
}
