package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
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
@Table(name = "tbl_reserva")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class ReservaEntity {

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
    private StatusReserva statusReserva;

    public Reserva toReserva() {
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setCliente(cliente.toCliente());
        reserva.setVeiculo(veiculo.toVeiculo());
        reserva.setDataHoraRetirada(dataHoraRetirada);
        reserva.setDataHoraDevolucaoPrevista(dataHoraDevolucaoPrevista);
        reserva.setDataHoraDevolucaoEfetivada(dataHoraDevolucaoEfetivada);
        reserva.setValorPrevisto(valorPrevisto);
        reserva.setValorExcedente(valorExcedente);
        reserva.setValorTotal(valorTotal);
        reserva.setStatusReserva(statusReserva);
        return reserva;
    }

    public static ReservaEntity toReservaEntity(Reserva reserva) {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setId(reserva.getId());
        reservaEntity.setCliente(ClienteEntity.toClienteEntity(reserva.getCliente()));
        reservaEntity.setVeiculo(VeiculoEntity.toVeiculoEntity(reserva.getVeiculo()));
        reservaEntity.setDataHoraRetirada(reserva.getDataHoraRetirada());
        reservaEntity.setDataHoraDevolucaoPrevista(reserva.getDataHoraDevolucaoPrevista());
        reservaEntity.setDataHoraDevolucaoEfetivada(reserva.getDataHoraDevolucaoEfetivada());
        reservaEntity.setValorPrevisto(reserva.getValorPrevisto());
        reservaEntity.setValorExcedente(reserva.getValorExcedente());
        reservaEntity.setValorTotal(reserva.getValorTotal());
        reservaEntity.setStatusReserva(reserva.getStatusReserva());
        return reservaEntity;
    }
}
