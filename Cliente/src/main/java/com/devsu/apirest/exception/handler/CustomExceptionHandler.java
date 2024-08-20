package com.devsu.apirest.exception.handler;

import com.devsu.apirest.exception.DuplicateException;
import com.devsu.apirest.exception.ObjectNotPresentException;
import com.devsu.apirest.exception.response.CustomErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(((FieldError) error).getField() + ": " + error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Object> handleDuplicateIdentification(DuplicateException ex) {
        return new ResponseEntity<>(CustomErrorResponse.builder().error("Duplicate Resource").message(ex.getMessage()).build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotPresentException.class)
    public ResponseEntity<Object> handleDuplicateIdentification(ObjectNotPresentException ex) {
        return new ResponseEntity<>(CustomErrorResponse.builder().error("Resource not found").message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Failed to convert value of type '%s' to required type '%s'",
                ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        return new ResponseEntity<>(CustomErrorResponse.builder().error("Bad Request").message(errorMessage).build(), HttpStatus.BAD_REQUEST);
    }

}
