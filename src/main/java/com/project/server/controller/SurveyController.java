package com.project.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.server.entity.Question;
import com.project.server.entity.Survey;
import com.project.server.entity.SurveyResponse;
import com.project.server.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    // Get all surveys
    @GetMapping("/")
    public ResponseEntity<List<Survey>> getSurveys() {
        List<Survey> surveys = surveyRepository.findAll();
        return ResponseEntity.ok(surveys);
    }

    // Get survey by ID
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable String id) {
        Optional<Survey> survey = surveyRepository.findById(id);
        if (survey.isPresent()) {
            return ResponseEntity.ok(survey.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update survey by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable String id, @RequestBody Survey updatedSurvey) {
        Optional<Survey> survey = surveyRepository.findById(id);
        if (survey.isPresent()) {
            updatedSurvey.setId(id);
            Survey savedSurvey = surveyRepository.save(updatedSurvey);
            return ResponseEntity.ok(savedSurvey);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Save responses to the survey
    @PutMapping("/update-responses/{id}")
    public ResponseEntity<Survey> saveResponsesToSurvey(@PathVariable String id, @RequestBody SurveyResponse request) {
        Optional<Survey> survey = surveyRepository.findById(id);
        if (!survey.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Survey existingSurvey = survey.get();
        List<Question> questions = request.getQuestions();

        // Check if all questions have responses
        boolean allQuestionsAnswered = questions.stream()
                .allMatch(q -> q.getResponses() != null && !q.getResponses().isEmpty());

        if (!allQuestionsAnswered) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Question> updatedQuestions = existingSurvey.getQuestions();
        for (Question originalQuestion : updatedQuestions) {
            for (Question submittedQuestion : questions) {
                if (originalQuestion.getId().equals(submittedQuestion.getId())) {
                    originalQuestion.getResponses().addAll(submittedQuestion.getResponses());
                }
            }
        }

        existingSurvey.setQuestions(updatedQuestions);
        existingSurvey.getSubmittedBy().add(request.getUserEmail());

        Survey updatedSurvey = surveyRepository.save(existingSurvey);
        return ResponseEntity.ok(updatedSurvey);
    }

    // Delete survey by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable String id) {
        Optional<Survey> survey = surveyRepository.findById(id);
        if (!survey.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        surveyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
