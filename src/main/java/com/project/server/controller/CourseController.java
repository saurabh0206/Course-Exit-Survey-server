package com.project.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import com.project.server.configuration.EmailService;
import com.project.server.entity.Course;
import com.project.server.entity.Course.ExitSurvey;
import com.project.server.entity.Question;
import com.project.server.entity.SurveyResponse;
import com.project.server.entity.SurveyResponseRequest;
import com.project.server.entity.SurveyUpdateRequest;
import com.project.server.entity.User;
import com.project.server.repository.CourseRepository;
import com.project.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Create a new course
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            // Create the course
            Course createdCourse = courseRepository.save(course);

            // Check if teacher exists, if not, create a new user
            Optional<User> existingTeacher = userRepository.findByEmail(course.getTeacherEmail());
            if (!existingTeacher.isPresent()) {
                User teacher = new User(course.getTeacherEmail(),
                        BCrypt.hashpw(course.getTeacherEmail(), BCrypt.gensalt()), "teacher");
                userRepository.save(teacher);
            }

            // Create user accounts for students
            if (course.getEnrolledStudents() != null) {
                for (String studentEmail : course.getEnrolledStudents()) {
                    Optional<User> existingStudent = userRepository.findByEmail(studentEmail);
                    if (!existingStudent.isPresent()) {
                        User student = new User(studentEmail, BCrypt.hashpw(studentEmail, BCrypt.gensalt()), "normal");
                        userRepository.save(student);
                    }
                }
            }

            return ResponseEntity.status(201).body(createdCourse);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    // Get all courses
    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    // Get courses by teacher email
    @GetMapping("/teacher")
    public ResponseEntity<List<Course>> getCoursesByTeacherEmail(@RequestParam String teacherEmail,
            @RequestParam Boolean isFormCreated) {
        List<Course> courses = courseRepository.findByTeacherEmailAndIsFormCreated(teacherEmail, isFormCreated);
        return ResponseEntity.ok(courses);
    }

    // Create or update a survey for a course
    @PostMapping("/create-update")
    public ResponseEntity<String> createAndUpdateSurvey(@RequestBody SurveyUpdateRequest request) {
        Optional<Course> courseOpt = courseRepository.findById(request.getCourseId());
        if (!courseOpt.isPresent()) {
            return ResponseEntity.status(404).body("Course not found.");
        }

        Course course = courseOpt.get();
        List<Question> updatedQuestions = request.getQuestions();

        // Update survey questions, title, and description
        course.getExitSurvey().setQuestions(updatedQuestions);
        course.getExitSurvey().setTitle(request.getTitle());
        course.getExitSurvey().setDescription(request.getDescription());
        course.setIsFormCreated(true);

        courseRepository.save(course);

        // Send email to enrolled students
        boolean emailStatus = true;
        for (String email : course.getEnrolledStudents()) {
            boolean emailSent = emailService.sendEmail(email, "Survey Notification",
                    "Dear student, a new survey has been created/updated for your course. Please check and provide your feedback.");
            if (!emailSent) {
                emailStatus = false;
            }
        }

        return emailStatus ? ResponseEntity.ok("Survey Added & Email sent.")
                : ResponseEntity.ok("Survey Added but Email not sent.");
    }

    // Get surveys by user
    @GetMapping("/surveys-by-user/{userEmail}")
    public ResponseEntity<List<SurveyResponse>> getSurveysByUser(@PathVariable String userEmail) {
        List<Course> courses = courseRepository.findByTeacherEmailOrEnrolledStudentsContaining(userEmail, userEmail,
                true);
        List<SurveyResponse> surveys = courses.stream().map(course -> {
            ExitSurvey survey = course.getExitSurvey();
            if (survey != null && !"None".equals(survey.getId())) {
                return new SurveyResponse(survey.getTitle(), survey.getDescription(), survey.getSubmittedBy().size(),
                        course.getEnrolledStudents().size(), course.getId(), survey.getSubmittedBy());
            }
            return null;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(surveys);
    }

    // Delete survey in a course
    @PostMapping("/delete/{courseId}")
    public ResponseEntity<Void> deleteSurveyInCourse(@PathVariable String courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (!courseOpt.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        Course course = courseOpt.get();
        course.setExitSurvey(new ExitSurvey());
        course.setIsFormCreated(false);

        courseRepository.save(course);
        return ResponseEntity.ok().build();
    }

    // Get a course by id
    @GetMapping("/{id}")
    public ResponseEntity<Course> getACourse(@PathVariable String id) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        return courseOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    // Delete a course by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // Save responses to course survey
    @PutMapping("/update-responses/{id}")
    public ResponseEntity<String> saveResponsesToCourseSurvey(@PathVariable String id,
            @RequestBody SurveyResponseRequest request) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (!courseOpt.isPresent()) {
            return ResponseEntity.status(404).body("Course not found.");
        }

        Course course = courseOpt.get();
        ExitSurvey survey = course.getExitSurvey();
        if (survey == null) {
            return ResponseEntity.status(404).body("Survey not found.");
        }

        for (Question submittedQuestion : request.getQuestions()) {
            Optional<Question> questionOpt = survey.getQuestions().stream()
                    .filter(q -> q.getId().equals(submittedQuestion.getId())).findFirst();
            if (questionOpt.isPresent()) {
                Question question = questionOpt.get();
                if (question.getResponses() == null) {
                    question.setResponses(new ArrayList<>());
                }
                question.getResponses().add(submittedQuestion.getResponse());
            }
        }

        survey.getSubmittedBy().add(request.getUserEmail());
        courseRepository.save(course);

        return ResponseEntity.ok("Survey responses saved successfully.");
    }
}
