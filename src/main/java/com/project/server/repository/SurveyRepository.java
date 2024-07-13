package com.project.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.server.entity.Survey;

public interface SurveyRepository extends MongoRepository<Survey, String> {
}
