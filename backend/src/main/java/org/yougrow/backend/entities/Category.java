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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Column(nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String authToken;

    private String color;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Quiz> quizzes;

    public Category() {
        this.authToken = generateAuthToken();
    }

    public Category(String name, String description, String password, String color) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.color = color;
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
