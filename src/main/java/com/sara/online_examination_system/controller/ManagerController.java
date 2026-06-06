package com.sara.online_examination_system.controller;

import com.sara.online_examination_system.dto.ProfileRequet;
import com.sara.online_examination_system.dto.UserResponse;
import com.sara.online_examination_system.dto.UsersRegisterRequest;
import com.sara.online_examination_system.exception.UserNotFoundException;
import com.sara.online_examination_system.model.User;
import com.sara.online_examination_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('MANAGER')")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")

    public ResponseEntity<UserResponse> createUser(@RequestBody UsersRegisterRequest request)
    {
        UserResponse user=userService.addUser(request);
        if (user!=null)
            return  new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers()
    {
        List<UserResponse> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId)
    {
        UserResponse user=userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @PatchMapping("/users/{userId}/deactivate")
    public ResponseEntity<UserResponse> deactivateuser(@PathVariable Long userId)
    {
        UserResponse user=userService.deactivateUser(userId);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @PatchMapping("/users/{userId}/profile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody ProfileRequet requet, @PathVariable Long userId)
    {
        UserResponse user=userService.UpdateProfile(requet,userId);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/users/filter/{role}")
    public  ResponseEntity<List<UserResponse>> filterUsersByRole(@PathVariable String role){
        List<UserResponse> users=userService.filterUsersByRole(role);
        if (!users.isEmpty())
            return new ResponseEntity<>(users,HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
