package com.devsu.apirest2.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String exceptionMessage){
        super(exceptionMessage);
    }
}
