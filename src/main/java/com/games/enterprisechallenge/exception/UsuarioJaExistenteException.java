package com.games.enterprisechallenge.exception;

public class UsuarioJaExistenteException extends RuntimeException {

    public UsuarioJaExistenteException() {
        super();
    }

    public UsuarioJaExistenteException(String message) {
        super(message);
    }

    public UsuarioJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
