package ru.gamzin.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gamzin.test.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTestId(Long testId);
}
