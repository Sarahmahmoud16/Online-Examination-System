package com.sara.online_examination_system.service;

import com.sara.online_examination_system.dto.CourseRequest;
import com.sara.online_examination_system.dto.CourseResponse;
import com.sara.online_examination_system.exception.NotFoundException;
import com.sara.online_examination_system.model.Course;
import com.sara.online_examination_system.model.Instructor;
import com.sara.online_examination_system.repository.CourseRepo;
import com.sara.online_examination_system.repository.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private InstructorRepo instructorRepo;

    private CourseResponse mapToResponse(Course course) {
        CourseResponse response=new CourseResponse();
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        return  response;
    }
    public CourseResponse createCourse(CourseRequest courseRequest)
    {
        Instructor instructor=instructorRepo.findById(courseRequest.getInstructorId())
                .orElseThrow(()->new NotFoundException("Instructor not found"));
        Course course=new Course();
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setInstructor(instructor);
        courseRepo.save(course);
        return mapToResponse(course);
    }


    public CourseResponse deleteCourse(Long courseId)
    {
        Course course=courseRepo.findById(courseId).orElseThrow(()->new NotFoundException("Course Not Found!!"));

        courseRepo.delete(course);
        return mapToResponse(course);

    }

}
