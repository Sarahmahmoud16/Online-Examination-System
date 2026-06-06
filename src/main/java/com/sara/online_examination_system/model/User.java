package com.sara.online_examination_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)//prevent edit it
    private Long id;
    @NotBlank
    @Column(nullable = false,length = 100)
    private String name;
    @NotBlank
    @Email(message = "Invalid Email Format!")
    @Column(nullable = false, unique = true,length = 100)
    private String email;
    @NotBlank
    @Size(min = 6,message = "Password must be at least 6 characters")
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
private Status status;
    @PrePersist //call method auto when object created for first time
    public void onCreate(){
        this.createdAt=LocalDateTime.now();
    }
}
