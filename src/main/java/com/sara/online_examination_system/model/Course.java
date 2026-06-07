package com.sara.online_examination_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @NotBlank
    @Column(nullable = false,length = 100)
    private String title;
    @NotBlank
    @Column(nullable = false,length = 200)
    private String description;
    @ManyToOne
    private Instructor instructor;
    @ManyToOne
    private List<Student> students;
}
