package com.luanpaiva.localizaapi.adapter.mapper.veiculomapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.VeiculoInput;
import com.luanpaiva.localizaapi.domain.model.Veiculo;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VeiculoMapperToDomainObject implements ModelMapperPort<VeiculoInput, Veiculo> {

    private final ModelMapper modelMapper;

    @Override
    public Veiculo map(VeiculoInput veiculoInput, Class<Veiculo> veiculoClass) {
        return modelMapper.map(veiculoInput, veiculoClass);
    }

    @Override
    public List<Veiculo> map(List<VeiculoInput> veiculoInputList, Class<Veiculo> veiculoClass) {
        return veiculoInputList.stream().map(veiculoInput -> modelMapper.map(veiculoInput, veiculoClass)).toList();
    }
}
