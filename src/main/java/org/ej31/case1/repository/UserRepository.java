package org.ej31.case1.repository;

import org.ej31.case1.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<User> findAll();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}