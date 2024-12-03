package org.yougrow.backend.entities.projection.Question;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.yougrow.backend.entities.projection.IdAndName;

import java.util.List;

@JsonPropertyOrder({
        "id",
        "question",
        "correctAnswer",
        "questionType",
        "answerA",
        "answerB",
        "answerC",
        "answerD",
        "quizzes",
        "isFavorite",
        "tags"
})
public interface QuestionDetail {
    Long getId();

    String getQuestion();

    String getQuestionType();

    String getCorrectAnswer();

    String getAnswerA();

    String getAnswerB();

    String getAnswerC();

    String getAnswerD();

    Boolean getIsFavorite();

    List<IdAndName> getTags();

    List<IdAndName> getQuizzes();
}
