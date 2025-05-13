package ru.gamzin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.model.Question;
import ru.gamzin.test.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired private QuestionRepository questionRepo;

    public Question save(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> getByTestId(Long testId) {
        return questionRepo.findByTestId(testId);
    }

    public Optional<Question> findById(Long id) {
        return questionRepo.findById(id);
    }

}
