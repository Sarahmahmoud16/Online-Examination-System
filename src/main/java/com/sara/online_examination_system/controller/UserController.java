package com.sara.online_examination_system.controller;

import com.sara.online_examination_system.dto.ProfileRequet;
import com.sara.online_examination_system.dto.UserResponse;
import com.sara.online_examination_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping("/users/{userId}/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody ProfileRequet request, @PathVariable Long userId)
    {
        UserResponse user=userService.UpdateProfile(request,userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

}
