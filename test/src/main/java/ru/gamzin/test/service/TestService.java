package ru.gamzin.test.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamzin.test.model.Test;
import ru.gamzin.test.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> findAll() {
        return testRepository.findAll();
    }

    public Optional<Test> findById(Long id) {
        return testRepository.findById(id);
    }

    public void save(Test test) {
        testRepository.save(test);
    }

    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }

    public List<Test> findByUser(User user) {
        return testRepository.findByUser(user);
    }
}
