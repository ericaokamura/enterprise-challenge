package com.games.enterprisechallenge.exception;

public class ContatoNaoExisteException extends RuntimeException {

    public ContatoNaoExisteException() {
        super();
    }

    public ContatoNaoExisteException(String message) {
        super(message);
    }

    public ContatoNaoExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}

