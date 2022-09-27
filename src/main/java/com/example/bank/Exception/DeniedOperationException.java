package com.example.bank.Exception;

public class DeniedOperationException extends RuntimeException{

    public DeniedOperationException(String message) {
        super(message);
    }
}
