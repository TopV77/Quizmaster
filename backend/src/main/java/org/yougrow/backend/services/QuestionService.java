package org.yougrow.backend.services;

import org.yougrow.backend.entities.Question;
import org.yougrow.backend.entities.projection.Question.QuestionDetail;
import org.yougrow.backend.entities.projection.Question.QuestionShort;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionShort> getAllQuestionsShort();

    Optional<QuestionDetail> getQuestionDetailById(Long id);

    Optional<Question> getQuestionById(Long id);

    Question addQuestion(Question question);

    Question updateQuestion(Question question);

    void deleteQuestion(Long id);
}
