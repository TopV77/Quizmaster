package org.yougrow.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String question;

    @Column(nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String authToken;

    private String questionType;

    @NotBlank
    private String correctAnswer;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private Boolean isFavorite = false;

    @ManyToMany
    @JsonIgnoreProperties("questions")
    private List<Quiz> quizzes;

    @ManyToMany
    @JsonIgnoreProperties("questions")
    private List<Tag> tags;

    public Question() {
        this.authToken = generateAuthToken();
    }

    public Question(String question, String password, String questionType, String correctAnswer, String answerA, String answerB, String answerC, String answerD, Boolean isFavorite) {
        this.question = question;
        this.password = password;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.isFavorite = isFavorite;
        this.authToken = generateAuthToken();
    }

    private String generateAuthToken() {
        return UUID.randomUUID().toString();
    }

    // Lifecycle Callback
    @PrePersist
    protected void onCreate() {
        if (this.authToken == null) {
            this.authToken = generateAuthToken();
        }
    }
}
