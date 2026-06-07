package com.sara.online_examination_system.repository;

import com.sara.online_examination_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
}
