package com.luanpaiva.localizaapi.adapter.output.repository.jpa.entities;

import com.luanpaiva.localizaapi.domain.model.Veiculo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tbl_veiculo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VeiculoEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fabricante;
    private String modelo;
    private String anoFabricacao;
    private String cor;
    private String placa;
    private BigDecimal valorDiariaAluguel;
    private Boolean disponivel = Boolean.TRUE;

    public Veiculo toVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);
        veiculo.setFabricante(fabricante);
        veiculo.setModelo(modelo);
        veiculo.setAnoFabricacao(anoFabricacao);
        veiculo.setCor(cor);
        veiculo.setPlaca(placa);
        veiculo.setValorDiariaAluguel(valorDiariaAluguel);
        veiculo.setDisponivel(disponivel);
        return veiculo;
    }

    public static VeiculoEntity toVeiculoEntity(Veiculo veiculo) {
        VeiculoEntity veiculoEntity = new VeiculoEntity();
        veiculoEntity.setId(veiculo.getId());
        veiculoEntity.setFabricante(veiculo.getAnoFabricacao());
        veiculoEntity.setModelo(veiculo.getModelo());
        veiculoEntity.setAnoFabricacao(veiculo.getAnoFabricacao());
        veiculoEntity.setCor(veiculo.getCor());
        veiculoEntity.setPlaca(veiculo.getPlaca());
        veiculoEntity.setValorDiariaAluguel(veiculo.getValorDiariaAluguel());
        veiculoEntity.setDisponivel(veiculo.getDisponivel());
        return veiculoEntity;
    }
}
