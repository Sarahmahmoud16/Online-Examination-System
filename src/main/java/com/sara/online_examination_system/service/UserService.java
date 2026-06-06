package com.sara.online_examination_system.service;

import com.sara.online_examination_system.dto.ProfileRequet;
import com.sara.online_examination_system.dto.RegisterRequest;
import com.sara.online_examination_system.dto.UserResponse;
import com.sara.online_examination_system.dto.UsersRegisterRequest;
import com.sara.online_examination_system.exception.EmailAlreadyExistException;
import com.sara.online_examination_system.exception.UserNotFoundException;
import com.sara.online_examination_system.model.*;
import com.sara.online_examination_system.repository.StudentRepo;
import com.sara.online_examination_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserResponse mapToResponse(User user) {

        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setRole(user.getRole());
        res.setStatus(user.getStatus());

        return res;
    }

    public UserResponse addStudent(RegisterRequest request)
    {
        if(userRepo.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException("Email Already Exists!!");
        }
         User user=new User();
         user.setName(request.getName());
         user.setEmail(request.getEmail());
         user.setPassword(passwordEncoder.encode(request.getPassword()));
         user.setRole(Role.STUDENT);
         user.setStatus(Status.ACTIVE);
         Student student=new Student();
         student.setUser(user);
         studentRepo.save(student);

        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(),user.getStatus());
    }

    public UserResponse addUser(UsersRegisterRequest request)
    {
        if(userRepo.existsByEmail(request.getEmail()))
        {
            throw new EmailAlreadyExistException("Email Already Exists!!");
        }
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setStatus(Status.ACTIVE);
        userRepo.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(),user.getStatus());
    }

    public List<UserResponse> getAllUsers() {
        List<User> users=userRepo.findAll();
        return users.stream().map(this::mapToResponse).toList();
    }

    public UserResponse getUserById(Long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found with id "+userId));
        return mapToResponse(user);
    }

    public UserResponse deactivateUser(Long userId)
    {
        User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found with id "+userId));
        if(user!=null&&user.getStatus()==Status.ACTIVE){
            user.setStatus(Status.INACTIVE);
        }
        userRepo.save(user);
        return mapToResponse(user);
    }

    public UserResponse UpdateProfile(ProfileRequet profileRequet,Long userId)
    {
        User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found With Id : "+userId));
        user.setName(profileRequet.getName());

            user.setEmail(profileRequet.getEmail());

        userRepo.save(user);
        return mapToResponse(user);
    }
    public List<UserResponse> filterUsersByRole(String role)
    {
        List<User> users=userRepo.findAll();
         return users.stream()
                 .filter(user -> user.getRole().name().equalsIgnoreCase(role))
                 .map(this::mapToResponse)
                 .toList();
    }
}
