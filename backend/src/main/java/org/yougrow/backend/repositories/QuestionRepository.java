package org.yougrow.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yougrow.backend.entities.Question;
import org.yougrow.backend.entities.projection.Question.QuestionDetail;
import org.yougrow.backend.entities.projection.Question.QuestionShort;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<QuestionShort> findAllQuestionsShortBy();

    Optional<QuestionDetail> findQuestionDetailById(Long id);
}
