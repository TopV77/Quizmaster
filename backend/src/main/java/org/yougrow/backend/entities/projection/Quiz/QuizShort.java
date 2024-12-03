package org.yougrow.backend.entities.projection.Quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;
import org.yougrow.backend.entities.projection.IdAndName;

@JsonPropertyOrder({"id", "name", "description", "isFavorite", "category", "questionSize"})
public interface QuizShort {
    Long getId();

    String getName();

    String getDescription();

    Boolean getIsFavorite();

    @JsonProperty("questionSize")
    @Value("#{target.questions != null ? target.questions.size() : 0}")
    Integer getQuestionSize();

    IdAndName getCategory();
}