package ru.gamzin.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamzin.test.model.Test;
import ru.gamzin.test.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired private TestRepository testRepo;

    public Test save(Test test) {
        return testRepo.save(test);
    }

    public List<Test> findAll() {
        return testRepo.findAll();
    }

    public Optional<Test> findById(Long id) {
        return testRepo.findById(id);
    }

    public void deleteById(Long id) {
        testRepo.deleteById(id);
    }
}
