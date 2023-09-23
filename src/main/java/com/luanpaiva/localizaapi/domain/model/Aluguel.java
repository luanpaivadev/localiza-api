package com.luanpaiva.localizaapi.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tbl_aluguel")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluguel {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Veiculo veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusAluguel statusAluguel;
}
