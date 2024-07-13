package com.project.server.entity;

import java.util.List;

public class SurveyResponseRequest {
    private List<Question> questions;
    private String userEmail;
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getters and setters
}
