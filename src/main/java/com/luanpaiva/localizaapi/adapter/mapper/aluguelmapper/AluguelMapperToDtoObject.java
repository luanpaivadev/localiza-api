package com.luanpaiva.localizaapi.adapter.mapper.aluguelmapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.AluguelDto;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AluguelMapperToDtoObject implements ModelMapperPort<Aluguel, AluguelDto> {

    private final ModelMapper modelMapper;

    @Override
    public AluguelDto map(Aluguel aluguel, Class<AluguelDto> aluguelDtoClass) {
        return modelMapper.map(aluguel, aluguelDtoClass);
    }

    @Override
    public List<AluguelDto> map(List<Aluguel> aluguelList, Class<AluguelDto> aluguelDtoClass) {
        return aluguelList.stream().map(aluguel -> modelMapper.map(aluguel, aluguelDtoClass)).toList();
    }
}
