package com.sara.online_examination_system.repository;

import com.sara.online_examination_system.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor,Long> {
}
