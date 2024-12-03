package org.yougrow.backend.entities.projection.Quiz;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.yougrow.backend.entities.projection.IdAndName;
import org.yougrow.backend.entities.projection.IdAndQuestion;
import java.util.List;

@JsonPropertyOrder({"id", "name", "description", "isFavorite", "category", "questions"})
public interface QuizDetail {
    Long getId();

    String getName();

    String getDescription();

    Boolean getIsFavorite();

    IdAndName getCategory();

    List<IdAndQuestion> getQuestions();
}
