package com.project.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "survey")
public class Survey {
    @Id
    private String id;

    @Field("questions")
    private List<Question> questions;

    private String title;
    private String description;

    @Field("submittedBy")
    private List<String> submittedBy;

    private String creationTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> updatedQuestions) {
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

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    // Getters and Setters

}
