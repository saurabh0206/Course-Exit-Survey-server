package com.project.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.server.entity.Course;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByTeacherEmail(String teacherEmail);
    List<Course> findByTeacherEmailAndIsFormCreated(String teacherEmail, Boolean isFormCreated);
    List<Course> findByTeacherEmailOrEnrolledStudentsContaining(String teacherEmail, String userEmail, Boolean isFormCreated);


}
