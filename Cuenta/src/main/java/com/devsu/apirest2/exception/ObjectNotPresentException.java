package com.devsu.apirest2.exception;

public class ObjectNotPresentException extends RuntimeException{

    public ObjectNotPresentException(String exceptionMessage){
        super(exceptionMessage);
    }
}
