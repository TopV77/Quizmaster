package org.yougrow.backend.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token {
    @NotBlank
    private String token;

    // Default constructor (required by Jackson)
    public Token() {}
}