package org.ej31.case1.service.impl;

import org.ej31.case1.model.User;
import org.ej31.case1.repository.UserRepository;
import org.ej31.case1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(String userName) {
        User user = new User();
        user.setName(userName);
        userRepository.addUser(user);
    }
}