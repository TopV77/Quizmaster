package org.yougrow.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.yougrow.backend.entities.Password;
import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.Token;
import org.yougrow.backend.entities.projection.Quiz.QuizDetail;
import org.yougrow.backend.entities.projection.Quiz.QuizShort;
import org.yougrow.backend.services.QuizService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin("https://quizmaster-k1ej.onrender.com")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<QuizShort>> getAllQuizzes() {
        return ok(quizService.getAllQuizzesShort());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDetail> getQuizById(@PathVariable Long id) {
        return quizService.getQuizDetailById(id)
                .map(quiz -> ok(quiz))
                .orElse(badRequest().build());
    }

    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody @Valid Quiz quiz) {
        if (quiz.getId() != null) {
            return badRequest().build();
        }

        quiz.setPassword(passwordEncoder.encode(quiz.getPassword()));
        Quiz savedQuiz = quizService.addQuiz(quiz);
        return ok(savedQuiz);
    }

    @PutMapping
    public ResponseEntity<Quiz> updateQuiz(@RequestBody @Valid Quiz quiz) {

        Optional<Quiz> existingQuiz = quizService.getQuizById(quiz.getId());

        if (quiz.getId() == null || existingQuiz.isEmpty()) {
            return badRequest().build();
        }

        if (quiz.getPassword() == null) {
            quiz.setPassword(existingQuiz.get().getPassword());
        } else {
            quiz.setPassword(passwordEncoder.encode(quiz.getPassword()));
        }

        Quiz savedQuiz = quizService.updateQuiz(quiz);
        return ok(savedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ok().build();
    }

    @PostMapping("/{id}/check-password")
    public ResponseEntity<Map<String, String>> checkPassword(@PathVariable Long id, @RequestBody Password request) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(id);
        if (optionalQuiz.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = passwordEncoder.matches(request.getPassword(), optionalQuiz.get().getPassword());

        if (matches) {
            return ok(Map.of("authToken", optionalQuiz.get().getAuthToken()));
        } else {
            return ok(null);
        }
    }

    @PostMapping("/{id}/check-token")
    public ResponseEntity<Boolean> checkToken(@PathVariable Long id, @RequestBody Token request) {
        Optional<Quiz> optionalQuiz = quizService.getQuizById(id);
        if (optionalQuiz.isEmpty()) {
            return badRequest().build();
        }

        boolean matches = Objects.equals(request.getToken(), optionalQuiz.get().getAuthToken());

        if (matches) {
            return ok(true);
        } else {
            return ok(false);
        }
    }
}