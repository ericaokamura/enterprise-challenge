package com.games.enterprisechallenge.exception;

public class VoluntarioJaExistenteException extends RuntimeException {

    public VoluntarioJaExistenteException() {
        super();
    }

    public VoluntarioJaExistenteException(String message) {
        super(message);
    }

    public VoluntarioJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

}
