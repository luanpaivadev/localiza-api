package com.luanpaiva.localizaapi.domain.exception;

public class ClienteNaoLocalizadoException extends RuntimeException {

    public ClienteNaoLocalizadoException(String message) {
        super(message);
    }
}
