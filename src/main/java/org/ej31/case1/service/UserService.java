package org.ej31.case1.service;

import org.ej31.case1.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(String userName);
}