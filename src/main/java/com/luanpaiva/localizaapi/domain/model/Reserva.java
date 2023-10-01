package com.luanpaiva.localizaapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva {

    private Long id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucaoPrevista;
    private LocalDateTime dataHoraDevolucaoEfetivada;
    private BigDecimal valorPrevisto;
    private BigDecimal valorExcedente;
    private BigDecimal valorTotal;
    private StatusReserva statusReserva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataHoraRetirada() {
        return dataHoraRetirada;
    }

    public void setDataHoraRetirada(LocalDateTime dataHoraRetirada) {
        this.dataHoraRetirada = dataHoraRetirada;
    }

    public LocalDateTime getDataHoraDevolucaoPrevista() {
        return dataHoraDevolucaoPrevista;
    }

    public void setDataHoraDevolucaoPrevista(LocalDateTime dataHoraDevolucaoPrevista) {
        this.dataHoraDevolucaoPrevista = dataHoraDevolucaoPrevista;
    }

    public LocalDateTime getDataHoraDevolucaoEfetivada() {
        return dataHoraDevolucaoEfetivada;
    }

    public void setDataHoraDevolucaoEfetivada(LocalDateTime dataHoraDevolucaoEfetivada) {
        this.dataHoraDevolucaoEfetivada = dataHoraDevolucaoEfetivada;
    }

    public BigDecimal getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(BigDecimal valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public BigDecimal getValorExcedente() {
        return valorExcedente;
    }

    public void setValorExcedente(BigDecimal valorExcedente) {
        this.valorExcedente = valorExcedente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataHoraRetirada=" + dataHoraRetirada +
                ", dataHoraDevolucaoPrevista=" + dataHoraDevolucaoPrevista +
                ", dataHoraDevolucaoEfetivada=" + dataHoraDevolucaoEfetivada +
                ", valorPrevisto=" + valorPrevisto +
                ", valorExcedente=" + valorExcedente +
                ", valorTotal=" + valorTotal +
                ", statusReserva=" + statusReserva +
                '}';
    }
}
