package ru.gamzin.test.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.model.Question;
import ru.gamzin.test.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    public void save(Answer answer) {
        answerRepository.save(answer);
    }


    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }

    public List<Answer> findByQuestion(Question question) {
        return answerRepository.findByQuestion(question);
    }
}
