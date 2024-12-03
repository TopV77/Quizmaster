package org.yougrow.backend.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yougrow.backend.entities.Password;
import org.yougrow.backend.entities.Question;
import org.yougrow.backend.entities.Token;
import org.yougrow.backend.entities.projection.Question.QuestionDetail;
import org.yougrow.backend.entities.projection.Question.QuestionShort;
import org.yougrow.backend.services.QuestionService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<QuestionShort>> getAllQuestions() {
        return ok(questionService.getAllQuestionsShort());
    }

    //TODO Move to Repository Call
    @GetMapping("/favorites")
    public ResponseEntity<List<QuestionShort>> getAllFavoriteQuestions() {
        List<QuestionShort> filteredQuestions = questionService.getAllQuestionsShort().stream().filter(QuestionShort::getIsFavorite).toList();
        return ok(filteredQuestions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetail> getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionDetailById(id)
                .map(question -> ok(question))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question question) {
        if (question.getId() != null) {
            return badRequest().build();
        }

        question.setPassword(passwordEncoder.encode(question.getPassword()));
        Question savedQuestion = questionService.addQuestion(question);
        return ok(savedQuestion);
    }

    @PutMapping
    public ResponseEntity<Question> updateQuestion(@RequestBody @Valid Question question) {
        Optional<Question> existingQuestion = questionService.getQuestionById(question.getId());

        if (question.getId() == null || existingQuestion.isEmpty()) {
            return badRequest().build();
        }

        if (question.getPassword() == null) {
            question.setPassword(existingQuestion.get().getPassword());
        } else {
            question.setPassword(passwordEncoder.encode(question.getPassword()));
        }

        Question savedQuestion = questionService.updateQuestion(question);
        return ok(savedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ok().build();
    }

    @PostMapping("/{id}/check-password")
    public ResponseEntity<Map<String, String>> checkPassword(@PathVariable Long id, @RequestBody Password request) {
        Optional<Question> optionalQuestion = questionService.getQuestionById(id);
        if (optionalQuestion.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = passwordEncoder.matches(request.getPassword(), optionalQuestion.get().getPassword());

        if (matches) {
            return ok(Map.of("authToken", optionalQuestion.get().getAuthToken()));
        } else {
            return ok(null);
        }
    }

    @PostMapping("/{id}/check-token")
    public ResponseEntity<Boolean> checkToken(@PathVariable Long id, @RequestBody Token request) {
        Optional<Question> optionalQuestion = questionService.getQuestionById(id);
        if (optionalQuestion.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = Objects.equals(request.getToken(), optionalQuestion.get().getAuthToken());

        if (matches) {
            return ok(true);
        } else {
            return ok(false);
        }
    }

}
