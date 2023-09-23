package com.luanpaiva.localizaapi.adapter.mapper.veiculomapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.VeiculoDto;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VeiculoMapperToDtoObject implements ModelMapperPort<Veiculo, VeiculoDto> {

    private final ModelMapper modelMapper;

    @Override
    public VeiculoDto map(Veiculo veiculo, Class<VeiculoDto> veiculoDtoClass) {
        return modelMapper.map(veiculo, veiculoDtoClass);
    }

    @Override
    public List<VeiculoDto> map(List<Veiculo> veiculoList, Class<VeiculoDto> veiculoDtoClass) {
        return veiculoList.stream().map(veiculo -> modelMapper.map(veiculo, veiculoDtoClass)).toList();
    }
}
