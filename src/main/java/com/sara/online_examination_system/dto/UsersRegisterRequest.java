package com.sara.online_examination_system.dto;

import com.sara.online_examination_system.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
