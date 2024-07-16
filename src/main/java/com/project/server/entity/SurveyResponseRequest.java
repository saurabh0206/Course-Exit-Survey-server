package com.project.server.entity;

import java.util.List;

import lombok.Data;

@Data
public class SurveyResponseRequest {
    private List<Question> questions;
    private String userEmail;

}
