package ru.gamzin.test.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamzin.test.model.Answer;
import ru.gamzin.test.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired private AnswerRepository answerRepo;

    public Answer save(Answer answer) {
        if (answer.isCorrect()) {
            List<Answer> existingAnswers = answerRepo.findByQuestionId(answer.getQuestion().getId());
            for (Answer existing : existingAnswers) {
                if (existing.isCorrect() && (answer.getId() == null || !existing.getId().equals(answer.getId()))) {
                    throw new IllegalStateException("Уже есть правильный ответ для этого вопроса");
                }
            }
        }
        return answerRepo.save(answer);
    }

    public Optional<Answer> findById(Long id) {
        return answerRepo.findById(id);
    }

    public List<Answer> getByQuestionId(Long questionId) {
        return answerRepo.findByQuestionId(questionId);
    }
}
