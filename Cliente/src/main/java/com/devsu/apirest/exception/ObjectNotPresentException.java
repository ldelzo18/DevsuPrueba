package com.devsu.apirest.exception;

public class ObjectNotPresentException extends RuntimeException{

    public ObjectNotPresentException(String exceptionMessage){
        super(exceptionMessage);
    }
}
