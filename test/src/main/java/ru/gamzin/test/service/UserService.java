package ru.gamzin.test.service;

import ru.gamzin.test.model.User;

import java.util.Optional;
import java.util.List;

public interface UserService {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    void deleteById(Long id);

}
