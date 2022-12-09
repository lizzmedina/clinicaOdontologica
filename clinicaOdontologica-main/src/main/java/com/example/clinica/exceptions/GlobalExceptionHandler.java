package com.example.clinica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger= Logger.getLogger(String.valueOf(GlobalExceptionHandler.class));

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException (ResourceNotFoundException ex){

        logger.error(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> todosLosErrores(Exception ex, WebRequest req)
    {
        logger.error(ex.getMessage());
        return new ResponseEntity<>("Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
