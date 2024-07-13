package com.project.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.server.entity.Question;
import com.project.server.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // Get all questions
    @GetMapping("/")
    public ResponseEntity<List<Question>> getQuestions(@RequestBody String id) {
        List<Question> questions = questionRepository.findBySurveyId(id);
        return ResponseEntity.ok(questions);
    }

    // Create new questions
    @PostMapping("/create")
    public ResponseEntity<List<Question>> createQuestions(@RequestBody List<Question> questions) {
        List<Question> createdQuestions = questionRepository.saveAll(questions);
        return ResponseEntity.ok(createdQuestions);
    }

    // Update a question
    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id, @RequestBody Question updatedQuestion) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            updatedQuestion.setId(id);
            Question savedQuestion = questionRepository.save(updatedQuestion);
            return ResponseEntity.ok(savedQuestion);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Delete a question
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        Optional<Question> question = questionRepository.findById(id);
        if (!question.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        questionRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
