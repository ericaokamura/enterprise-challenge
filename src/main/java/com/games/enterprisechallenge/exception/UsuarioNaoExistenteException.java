package com.games.enterprisechallenge.exception;

public class UsuarioNaoExistenteException extends RuntimeException {
    public UsuarioNaoExistenteException() {
        super();
    }

    public UsuarioNaoExistenteException(String message) {
        super(message);
    }

    public UsuarioNaoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
