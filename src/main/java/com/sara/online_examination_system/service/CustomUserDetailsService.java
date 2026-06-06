package com.sara.online_examination_system.service;

import com.sara.online_examination_system.model.User;
import com.sara.online_examination_system.model.UserPrinciple;
import com.sara.online_examination_system.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        return new UserPrinciple(user);
    }
}
