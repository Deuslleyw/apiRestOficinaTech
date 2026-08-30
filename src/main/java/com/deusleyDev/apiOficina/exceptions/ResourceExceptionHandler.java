package com.deusleyDev.apiOficina.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<StandardError> clienteNotFound(
            ClienteNotFoundException error, HttpServletRequest request) {
        StandardError er = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), error.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);

    }

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<StandardError> veiculoNotFound(
            VeiculoNotFoundException error, HttpServletRequest request) {
        StandardError er = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), error.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);

    }

    @ExceptionHandler(OrdenServicoNotFoundException.class)
    public ResponseEntity<StandardError> ordemServicoNotFound(
            OrdenServicoNotFoundException error, HttpServletRequest request) {
        StandardError er = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), error.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);


    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(
            DataIntegrityViolationException error, HttpServletRequest request) {
        StandardError er = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), error.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);


    }
}