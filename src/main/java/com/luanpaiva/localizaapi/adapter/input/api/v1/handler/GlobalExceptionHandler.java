package com.luanpaiva.localizaapi.adapter.input.api.v1.handler;

import com.luanpaiva.localizaapi.adapter.input.api.v1.handler.model.ResponseError;
import com.luanpaiva.localizaapi.domain.exception.ClienteJaCadastradoException;
import com.luanpaiva.localizaapi.domain.exception.ClienteNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoDisponivelException;
import com.luanpaiva.localizaapi.domain.exception.VeiculoNaoLocalizadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exceptionHandler(Exception e) {
        return status(INTERNAL_SERVER_ERROR).body(getErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ResponseError> exceptionHandler(DateTimeParseException e) {
        String mensagem = "Enviar [dataNascimento] no formato [dd/MM/yyyy]";
        return status(BAD_REQUEST).body(getErrorResponse(BAD_REQUEST, mensagem));
    }

    @ExceptionHandler(ClienteNaoLocalizadoException.class)
    public ResponseEntity<ResponseError> exceptionHandler(ClienteNaoLocalizadoException e) {
        return status(NOT_FOUND).body(getErrorResponse(NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(VeiculoNaoDisponivelException.class)
    public ResponseEntity<ResponseError> exceptionHandler(VeiculoNaoDisponivelException e) {
        return status(UNPROCESSABLE_ENTITY).body(getErrorResponse(UNPROCESSABLE_ENTITY, e.getMessage()));
    }

    @ExceptionHandler(VeiculoNaoLocalizadoException.class)
    public ResponseEntity<ResponseError> exceptionHandler(VeiculoNaoLocalizadoException e) {
        return status(NOT_FOUND).body(getErrorResponse(NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> exceptionHandler(MethodArgumentNotValidException e) {
        String fieldName = e.getFieldError().getField();
        String mensagem = MessageFormat.format("Campo [{0}] nulo/inválido, verifique e tente novamente.", fieldName);
        return status(BAD_REQUEST).body(getErrorResponse(BAD_REQUEST, mensagem));
    }

    @ExceptionHandler(ClienteJaCadastradoException.class)
    public ResponseEntity<ResponseError> exceptionHandler(ClienteJaCadastradoException e) {
        return status(BAD_REQUEST).body(getErrorResponse(BAD_REQUEST, e.getMessage()));
    }

    private ResponseError getErrorResponse(HttpStatus httpStatus, String mensagemErro) {
        return new ResponseError(httpStatus.value(), mensagemErro, LocalDateTime.now());
    }
}
