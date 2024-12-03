package org.yougrow.backend.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.projection.Quiz.QuizDetail;
import org.yougrow.backend.entities.projection.Quiz.QuizShort;
import org.yougrow.backend.repositories.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;


    @Override
    public List<QuizShort> getAllQuizzesShort() {
        return quizRepository.findAllQuizzesShortBy();
    }

    @Override
    public Optional<QuizDetail> getQuizDetailById(Long id) {
        return quizRepository.findQuizDetailById(id);
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

}


