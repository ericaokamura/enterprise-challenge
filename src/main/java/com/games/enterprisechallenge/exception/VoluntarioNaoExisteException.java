package com.games.enterprisechallenge.exception;

public class VoluntarioNaoExisteException extends RuntimeException {

    public VoluntarioNaoExisteException() {
        super();
    }

    public VoluntarioNaoExisteException(String message) {
        super(message);
    }

    public VoluntarioNaoExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
