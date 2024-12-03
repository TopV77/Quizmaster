package org.yougrow.backend.services;

import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.projection.Quiz.QuizDetail;
import org.yougrow.backend.entities.projection.Quiz.QuizShort;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<QuizShort> getAllQuizzesShort();

    Optional<QuizDetail> getQuizDetailById(Long id);

    Optional<Quiz> getQuizById(Long id);

    Quiz addQuiz(Quiz quiz);

    Quiz updateQuiz(Quiz quiz);

    void deleteQuiz(Long id);

}
