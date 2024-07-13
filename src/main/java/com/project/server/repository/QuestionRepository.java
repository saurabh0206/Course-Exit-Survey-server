package com.project.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.server.entity.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findBySurveyId(String id);
}
