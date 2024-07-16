package com.project.server.entity;

import java.util.List;

import lombok.Data;

@Data
public class SurveyUpdateRequest {
    private String courseId;
    private List<Question> questions;
    private String title;
    private String description;

}
