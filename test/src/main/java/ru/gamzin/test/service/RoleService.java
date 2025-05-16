package ru.gamzin.test.service;

import ru.gamzin.test.model.Role;

import java.util.Optional;
import java.util.List;

public interface RoleService {
    Optional<Role> findByName(String name);
    List<Role> findAll();
}
