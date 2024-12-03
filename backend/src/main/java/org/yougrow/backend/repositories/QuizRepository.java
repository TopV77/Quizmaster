package org.yougrow.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yougrow.backend.entities.Quiz;
import org.yougrow.backend.entities.projection.Quiz.QuizDetail;
import org.yougrow.backend.entities.projection.Quiz.QuizShort;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<QuizShort> findAllQuizzesShortBy();

    Optional<QuizDetail> findQuizDetailById(Long id);
}
