package br.edu.infnet.al.matheus_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JogoNaoEncontradoException.class)
    public ResponseEntity<String> handleJogoNaoEncontrado(JogoNaoEncontradoException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleArgumentoInvalido(IllegalArgumentException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}