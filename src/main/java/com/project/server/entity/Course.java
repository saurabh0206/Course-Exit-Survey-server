package com.project.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {

    @Id
    private String id;
    private String teacherName;
    private String teacherEmail;
    private String courseCode;
    private String courseName;
    private List<String> enrolledStudents;
    private Boolean isFormCreated = false;
    private ExitSurvey exitSurvey;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Boolean getIsFormCreated() {
        return isFormCreated;
    }

    public void setIsFormCreated(Boolean isFormCreated) {
        this.isFormCreated = isFormCreated;
    }

    public ExitSurvey getExitSurvey() {
        return exitSurvey;
    }

    public void setExitSurvey(ExitSurvey exitSurvey) {
        this.exitSurvey = exitSurvey;
    }

    public static class ExitSurvey {
        private List<com.project.server.entity.Question> questions;
        private String title;
        private String description;
        private List<String> submittedBy;
        private String id;

        // Getters and setters

        public static class Question {
            private String id;
            private String question;
            private List<String> answerChoices;
            private List<String> responses;
            public String getId() {
                return id;
            }
            public void setId(String id) {
                this.id = id;
            }
            public String getQuestion() {
                return question;
            }
            public void setQuestion(String question) {
                this.question = question;
            }
            public List<String> getAnswerChoices() {
                return answerChoices;
            }
            public void setAnswerChoices(List<String> answerChoices) {
                this.answerChoices = answerChoices;
            }
            public List<String> getResponses() {
                return responses;
            }
            public void setResponses(List<String> responses) {
                this.responses = responses;
            }

            // Getters and setters
            
        }

        public List<com.project.server.entity.Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<com.project.server.entity.Question> updatedQuestions) {
            this.questions = updatedQuestions;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getSubmittedBy() {
            return submittedBy;
        }

        public void setSubmittedBy(List<String> submittedBy) {
            this.submittedBy = submittedBy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    
}
