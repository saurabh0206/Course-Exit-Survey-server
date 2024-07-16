package com.project.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "question")
@Data
public class Question {
	@Id
	private String id;

	private String question;
	private String type;

	private List<String> answerChoices;

	private String surveyId;
	private String userId;

	private List<Object> responses;

    public Object getResponse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResponse'");
    }

}
