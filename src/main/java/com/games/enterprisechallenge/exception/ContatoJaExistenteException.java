package com.games.enterprisechallenge.exception;

public class ContatoJaExistenteException extends RuntimeException {

    public ContatoJaExistenteException() {
        super();
    }

    public ContatoJaExistenteException(String message) {
        super(message);
    }

    public ContatoJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

}
