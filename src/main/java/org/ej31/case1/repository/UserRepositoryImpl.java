package org.ej31.case1.repository;

import org.ej31.case1.mapper.UserRowMapper;
import org.ej31.case1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    @Transactional
    public void addUser(User user) {
        String sql = "INSERT INTO user (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail());
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getId());
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
