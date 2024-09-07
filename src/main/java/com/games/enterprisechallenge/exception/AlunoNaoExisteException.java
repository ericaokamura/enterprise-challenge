package com.games.enterprisechallenge.exception;

public class AlunoNaoExisteException extends RuntimeException {

    public AlunoNaoExisteException() {
        super();
    }

    public AlunoNaoExisteException(String message) {
        super(message);
    }

    public AlunoNaoExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
