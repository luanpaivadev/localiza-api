package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.StatusAluguel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tbl_aluguel")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class AluguelEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteEntity cliente;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private VeiculoEntity veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoEfetivada;
    private BigDecimal valorPrevisto;
    private BigDecimal valorExcedente;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusAluguel statusAluguel;
}
