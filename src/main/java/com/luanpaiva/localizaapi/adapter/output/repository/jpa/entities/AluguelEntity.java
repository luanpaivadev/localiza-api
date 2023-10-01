package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.model.StatusAluguel;
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
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class AluguelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ClienteEntity cliente;
    @ManyToOne
    private VeiculoEntity veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoEfetivada;
    private BigDecimal valorPrevisto;
    private BigDecimal valorExcedente;
    private BigDecimal valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusAluguel statusAluguel;

    public Aluguel toAluguel() {
        Aluguel aluguel = new Aluguel();
        aluguel.setId(id);
        aluguel.setCliente(cliente.toCliente());
        aluguel.setVeiculo(veiculo.toVeiculo());
        aluguel.setDataHoraRetirada(dataHoraRetirada);
        aluguel.setDataHoraDevolucaoPrevista(dataHoraDevolucaoPrevista);
        aluguel.setDataHoraDevolucaoEfetivada(dataHoraDevolucaoEfetivada);
        aluguel.setValorPrevisto(valorPrevisto);
        aluguel.setValorExcedente(valorExcedente);
        aluguel.setValorTotal(valorTotal);
        aluguel.setStatusAluguel(statusAluguel);
        return aluguel;
    }

    public static AluguelEntity toAluguelEntity(Aluguel aluguel) {
        AluguelEntity aluguelEntity = new AluguelEntity();
        aluguelEntity.setId(aluguel.getId());
        aluguelEntity.setCliente(ClienteEntity.toClienteEntity(aluguel.getCliente()));
        aluguelEntity.setVeiculo(VeiculoEntity.toVeiculoEntity(aluguel.getVeiculo()));
        aluguelEntity.setDataHoraRetirada(aluguel.getDataHoraRetirada());
        aluguelEntity.setDataHoraDevolucaoPrevista(aluguel.getDataHoraDevolucaoPrevista());
        aluguelEntity.setDataHoraDevolucaoEfetivada(aluguel.getDataHoraDevolucaoEfetivada());
        aluguelEntity.setValorPrevisto(aluguel.getValorPrevisto());
        aluguelEntity.setValorExcedente(aluguel.getValorExcedente());
        aluguelEntity.setValorTotal(aluguel.getValorTotal());
        aluguelEntity.setStatusAluguel(aluguel.getStatusAluguel());
        return aluguelEntity;
    }
}
