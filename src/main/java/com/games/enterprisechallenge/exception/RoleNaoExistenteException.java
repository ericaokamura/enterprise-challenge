package com.games.enterprisechallenge.exception;

public class RoleNaoExistenteException extends RuntimeException {

    public RoleNaoExistenteException() {
        super();
    }

    public RoleNaoExistenteException(String message) {
        super(message);
    }

    public RoleNaoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}

