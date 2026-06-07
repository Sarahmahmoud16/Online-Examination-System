package com.sara.online_examination_system.controller;

import com.sara.online_examination_system.dto.*;
import com.sara.online_examination_system.service.CourseService;
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

    @Autowired
    private CourseService courseService;
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

    @PostMapping("/course")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest)
    {
        CourseResponse response=courseService.createCourse(courseRequest) ;
        if(response!=null)
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<CourseResponse> deleteCourse(@PathVariable Long courseId)
    {
        CourseResponse response=courseService.deleteCourse(courseId);
        if (response!=null)
            return new ResponseEntity<>(response,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
