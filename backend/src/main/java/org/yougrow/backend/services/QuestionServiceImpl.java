package org.yougrow.backend.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.yougrow.backend.entities.Question;
import org.yougrow.backend.entities.projection.Question.QuestionDetail;
import org.yougrow.backend.entities.projection.Question.QuestionShort;
import org.yougrow.backend.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionShort> getAllQuestionsShort() {
        return questionRepository.findAllQuestionsShortBy();
    }

    @Override
    public Optional<QuestionDetail> getQuestionDetailById(Long id) {
        return questionRepository.findQuestionDetailById(id);
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
