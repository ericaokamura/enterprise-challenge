package com.games.enterprisechallenge.exception;

public class OficinaNaoExistenteException extends RuntimeException {
    public OficinaNaoExistenteException() {
        super();
    }

    public OficinaNaoExistenteException(String message) {
        super(message);
    }

    public OficinaNaoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }


}
