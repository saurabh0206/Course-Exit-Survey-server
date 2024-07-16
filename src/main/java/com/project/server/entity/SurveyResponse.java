package com.project.server.entity;

import java.util.List;

import lombok.Data;

@Data
public class SurveyResponse {
    public SurveyResponse(String title, String description, int size, int size2, String id, List<String> submittedBy) {
        //TODO Auto-generated constructor stub
    }
    private List<Question> questions;
    private String userEmail;

}
