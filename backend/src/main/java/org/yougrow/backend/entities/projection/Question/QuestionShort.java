package org.yougrow.backend.entities.projection.Question;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.yougrow.backend.entities.projection.IdAndName;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "question",
        "questionType",
        "isFavorite",
        "tags"
})
public interface QuestionShort {
    Long getId();

    String getQuestion();

    String getQuestionType();

    Boolean getIsFavorite();

    List<IdAndName> getTags();
}
