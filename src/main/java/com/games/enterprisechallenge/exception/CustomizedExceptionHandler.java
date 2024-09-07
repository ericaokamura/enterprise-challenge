package com.games.enterprisechallenge.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(UsuarioJaExistenteException.class)
    public ResponseEntity usuarioJaExistenteException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(AlunoJaExistenteException.class)
    public ResponseEntity alunoJaExistenteException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(AlunoNaoExisteException.class)
    public ResponseEntity alunoNaoExisteException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ContatoJaExistenteException.class)
    public ResponseEntity contatoJaExistenteException() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(VoluntarioJaExistenteException.class)
    public ResponseEntity voluntarioJaExistenteException() {
        return ResponseEntity.badRequest().build();
    }


}
