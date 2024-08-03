package com.games.enterprisechallenge.exception;

public class AlunoJaExistenteException extends RuntimeException {

    public AlunoJaExistenteException() {
        super();
    }

    public AlunoJaExistenteException(String message) {
        super(message);
    }

    public AlunoJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }

}
