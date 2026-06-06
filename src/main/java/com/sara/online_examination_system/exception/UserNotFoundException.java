package com.sara.online_examination_system.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
