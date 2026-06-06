package com.sara.online_examination_system.controller;

import com.sara.online_examination_system.dto.LoginRequest;
import com.sara.online_examination_system.dto.RegisterRequest;
import com.sara.online_examination_system.dto.UserResponse;
import com.sara.online_examination_system.service.JwtService;
import com.sara.online_examination_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            UserDetails userDetails=(UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(jwtService.generateToken(userDetails),HttpStatus.FOUND);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request)
    {
        UserResponse user= userService.addStudent(request);
        if(user!=null)
         return new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.FOUND);
    }


}
