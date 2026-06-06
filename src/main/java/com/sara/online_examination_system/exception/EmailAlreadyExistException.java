package com.sara.online_examination_system.exception;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(String message)
    {
        super(message);
    }
}
