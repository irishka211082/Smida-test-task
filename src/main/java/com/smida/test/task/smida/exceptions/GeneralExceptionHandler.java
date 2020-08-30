package com.smida.test.task.smida.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoShareException.class)
    protected ResponseEntity<GeneralException> handleNoSharesAvailableException() {
        return new ResponseEntity<>(new GeneralException(("There is no share with such id")), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class GeneralException {
        private String message;
    }
}

