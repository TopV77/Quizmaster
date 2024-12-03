package org.yougrow.backend.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Password {
    @NotBlank
    private String password;

    // Default constructor (required by Jackson)
    public Password() {}
}