package org.ej31.case1_jdbctemplate.service;

import org.ej31.case1_jdbctemplate.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(String userName);
}