package org.yougrow.backend.entities.projection.Category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"id", "name", "description", "color", "quizSize"})
public interface CategoryShort {
    Long getId();

    String getName();

    String getDescription();

    String getColor();

    @JsonProperty("quizSize")
    @Value("#{target.quizzes != null ? target.quizzes.size() : 0}")
    Integer getQuizSize();
}
