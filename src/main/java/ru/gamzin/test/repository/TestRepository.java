package ru.gamzin.test.repository;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.gamzin.test.model.Test;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTitleContainingIgnoreCase(String title);
}
