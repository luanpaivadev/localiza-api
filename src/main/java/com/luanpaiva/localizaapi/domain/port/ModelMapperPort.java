package com.luanpaiva.localizaapi.domain.port;

import java.util.List;

public interface ModelMapperPort<I, O> {

    O map(I i, Class<O> o);

    List<O> map(List<I> i, Class<O> o);
}
