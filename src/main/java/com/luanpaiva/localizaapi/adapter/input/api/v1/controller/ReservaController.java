package com.luanpaiva.localizaapi.adapter.input.api.v1.controller;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.ReservaDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ReservaInput;
import com.luanpaiva.localizaapi.domain.model.Reserva;
import com.luanpaiva.localizaapi.domain.model.StatusReserva;
import com.luanpaiva.localizaapi.domain.port.ReservaServicePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/reservas")
@AllArgsConstructor
public class ReservaController {

    private final ReservaServicePort reservaServicePort;

    @GetMapping("/status-reserva")
    public ResponseEntity<List<ReservaDto>> listarReservas(@RequestParam(defaultValue = "ABERTO") StatusReserva statusReserva) {

        List<Reserva> reservaList = reservaServicePort.listarReservas(statusReserva);
        List<ReservaDto> reservaDtoList = reservaList.stream().map(ReservaDto::toReservaDto).toList();
        return ResponseEntity.ok(reservaDtoList);
    }

    @PostMapping
    public ResponseEntity<ReservaDto> salvarReserva(@RequestBody @Valid ReservaInput reservaInput) {

        Reserva reserva = reservaServicePort.salvarReserva(reservaInput);
        ReservaDto reservaDto = ReservaDto.toReservaDto(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaDto);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<ReservaDto> finalizarReserva(@PathVariable Long id,
                                                       @RequestParam LocalDateTime dataHoraDevolucaoEfetivada) {

        Reserva reserva = reservaServicePort.finalizarReserva(id, dataHoraDevolucaoEfetivada);
        ReservaDto reservaDto = ReservaDto.toReservaDto(reserva);

        return ResponseEntity.ok(reservaDto);
    }
}
