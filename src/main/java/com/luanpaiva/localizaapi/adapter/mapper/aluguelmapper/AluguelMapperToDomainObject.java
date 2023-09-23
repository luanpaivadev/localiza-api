package com.luanpaiva.localizaapi.adapter.mapper.aluguelmapper;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.AluguelInput;
import com.luanpaiva.localizaapi.domain.model.Aluguel;
import com.luanpaiva.localizaapi.domain.port.ModelMapperPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AluguelMapperToDomainObject implements ModelMapperPort<AluguelInput, Aluguel> {

    private final ModelMapper modelMapper;

    @Override
    public Aluguel map(AluguelInput aluguelInput, Class<Aluguel> aluguelClass) {
        return modelMapper.map(aluguelInput, aluguelClass);
    }

    @Override
    public List<Aluguel> map(List<AluguelInput> aluguelInputList, Class<Aluguel> aluguelClass) {
        return aluguelInputList.stream().map(aluguelInput -> modelMapper.map(aluguelInput, aluguelClass)).toList();
    }
}
