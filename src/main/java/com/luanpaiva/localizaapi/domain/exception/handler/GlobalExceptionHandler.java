package com.luanpaiva.localizaapi.domain.exception.handler;

import com.luanpaiva.localizaapi.domain.exception.CepInvalidoException;
import com.luanpaiva.localizaapi.domain.exception.ClienteNaoLocalizadoException;
import com.luanpaiva.localizaapi.domain.exception.model.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exceptionHandler(Exception e) {
        return status(INTERNAL_SERVER_ERROR).body(new ResponseError(INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ResponseError> exceptionHandler(DateTimeParseException e) {
        String mensagem = "Enviar [dataNascimento] no formato [dd/MM/yyyy]";
        return status(BAD_REQUEST).body(new ResponseError(BAD_REQUEST.value(), mensagem));
    }

    @ExceptionHandler(ClienteNaoLocalizadoException.class)
    public ResponseEntity<ResponseError> exceptionHandler(ClienteNaoLocalizadoException e) {
        return status(NOT_FOUND).body(new ResponseError(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ResponseError> exceptionHandler(CepInvalidoException e) {
        return status(BAD_REQUEST).body(new ResponseError(BAD_REQUEST.value(), e.getMessage()));
    }
}
