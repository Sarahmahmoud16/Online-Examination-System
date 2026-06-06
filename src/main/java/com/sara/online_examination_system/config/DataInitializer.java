package com.sara.online_examination_system.config;

import com.sara.online_examination_system.model.Role;
import com.sara.online_examination_system.model.Status;
import com.sara.online_examination_system.model.User;
import com.sara.online_examination_system.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initiateAdmin(UserRepo repo, PasswordEncoder encoder)
    {
        return  args -> {
            if(repo.findByEmail("admin@gmail.com").isEmpty())
            {
                User admin=new User();
                admin.setName("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.MANAGER);
                admin.setStatus(Status.ACTIVE);
                repo.save(admin);
            }

        };

    }

}
