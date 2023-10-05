package com.luanpaiva.localizaapi.domain.port;

import com.luanpaiva.localizaapi.domain.model.DadosEmail;
import com.luanpaiva.localizaapi.domain.model.Reserva;

public interface EnvioEmailPort {

    void enviarEmail(DadosEmail dadosEmail, Reserva reserva);
}
