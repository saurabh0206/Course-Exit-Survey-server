package com.project.server.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

import java.util.List;

@Document(collection = "survey")
@Data
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

}
