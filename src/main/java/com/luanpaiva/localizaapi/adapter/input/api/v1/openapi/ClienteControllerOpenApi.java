package com.luanpaiva.localizaapi.adapter.input.api.v1.openapi;

import com.luanpaiva.localizaapi.adapter.input.api.v1.model.dto.ClienteDto;
import com.luanpaiva.localizaapi.adapter.input.api.v1.model.input.ClienteInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Clientes", description = "Responsável por gerenciar clientes.")
public interface ClienteControllerOpenApi {

    @Operation(summary = "Retorna um cliente", description = "Retorna um cliente pelo cpf.", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(ref = "ResponseError"))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(ref = "ResponseError")))
    })
    ResponseEntity<ClienteDto> buscarClientePeloCpf(String cpf);

    @Operation(summary = "Salva novo cliente", description = "Responsável por persistir um novo cliente no banco de dados.", responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(ref = "ResponseError"))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(ref = "ResponseError")))
    })
    ResponseEntity<ClienteDto> salvarCliente(@RequestBody(description = "Representação de um novo cliente", required = true) ClienteInput clienteInput);
}
