package com.games.enterprisechallenge.exception;

public class UsuarioNaoAceitouTermosException extends RuntimeException {

    public UsuarioNaoAceitouTermosException() {
        super();
    }

    public UsuarioNaoAceitouTermosException(String message) {
        super(message);
    }

    public UsuarioNaoAceitouTermosException(String message, Throwable cause) {
        super(message, cause);
    }
}
