package com.dh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptions extends Exception{

   // private static final Logger logger = Logger.getLogger(GlobalExceptions.class);
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> datosIncorrectos(BadRequestException ex){
       // logger.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({NotFoundExceptions.class})
    public ResponseEntity<?> noExisteEnDB(NotFoundExceptions ex) {
       // logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
