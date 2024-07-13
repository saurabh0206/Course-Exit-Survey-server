package com.project.server.entity;

import java.util.List;

public class SurveyResponse {
    private List<Question> questions;
    private String userEmail;

    // Getters and Setters

    public SurveyResponse(String title, String description, int size, int size2, String id, List<String> submittedBy) {
        //TODO Auto-generated constructor stub
    }

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
}
