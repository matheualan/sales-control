package com.salescontrol.handler;

import com.salescontrol.exception.ClientNotFoundException;
import com.salescontrol.exception.ClientNotFoundExceptionDetails;
import com.salescontrol.exception.UniqueIndexCpfExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

//@ControllerAdvice
@RestControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ClientNotFoundExceptionDetails> handlerClientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClientNotFoundExceptionDetails.builder()
                .error("BAD REQUEST")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .developMessage(e.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build());
    }

//    Exception quando tenta cadastrar um CPF ja existente na base de dados
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<UniqueIndexCpfExceptionDetails> handlerUniqueIndexCpfException(
            SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(UniqueIndexCpfExceptionDetails.builder()
                .error("BAD REQUEST")
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage().concat(" - CPF JÁ EXISTE NA BASE DE DADOS."))
                .developMessage(e.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build());
    }

}
