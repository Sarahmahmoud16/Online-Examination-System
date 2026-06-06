package com.sara.online_examination_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private String message;
    private int status;
    private LocalDateTime timestamp;


}
