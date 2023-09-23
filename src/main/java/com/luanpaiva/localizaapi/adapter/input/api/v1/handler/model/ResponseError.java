package com.luanpaiva.localizaapi.adapter.input.api.v1.handler.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResponseError")
public class ResponseError {

    @Schema(example = "404")
    private Integer codigo;
    @Schema(example = "Recurso não encontrado")
    private String mensagem;
}
