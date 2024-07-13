package com.project.server.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "question")
public class Question {
	@Id
	private String id;

	private String question;
	private String type;

	private List<String> answerChoices;

	private String surveyId;
	private String userId;

	private List<Object> responses;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getAnswerChoices() {
		return answerChoices;
	}

	public void setAnswerChoices(List<String> answerChoices) {
		this.answerChoices = answerChoices;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Object> getResponses() {
		return responses;
	}

	public void setResponses(List<Object> responses) {
		this.responses = responses;
	}

	public Object getResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	// Getters and Setters
}
