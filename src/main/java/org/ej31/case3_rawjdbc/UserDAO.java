package org.ej31.case3_rawjdbc;

import org.ej31.case3_rawjdbc.models.Department;
import org.ej31.case3_rawjdbc.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String url = "jdbc:mysql://localhost:3306/jeff_db";
    private final String user = "jeff";
    private final String password = "123123..";

    public void insertUser(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public void insertUserWithDepartment(User user) throws Exception {
        int user_id = -1;
        try (
                Connection connection = DriverManager.getConnection(url, this.user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users (name, email) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS     // 마지막으로 insert 된 row의 id 값 가져오는 설정 추가
                )) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {  // 앞서 insert 된 쿼리에서 생성 된 row의 id 값 가져오기
                if (generatedKeys.next()) {
                    user_id = generatedKeys.getInt(1);
                }
            }
        }
        try (
                Connection connection = DriverManager.getConnection(url, this.user, password);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO department (id_user, department_name) VALUES (?, ?)")) {
            if (user_id == -1) { // DB 연결 종료 된 경우, 쓰기 권한 없는 경우 등에 대비해서 방어코딩 🛡️
                throw new Exception("유저 아이디가 제대로 반영되지 않았음");
            }
            preparedStatement.setLong(1, user_id);
            preparedStatement.setString(2, user.getDepartment().getDepartmentName());
            preparedStatement.executeUpdate();
        }
    }

    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    users.add(new User(id, name, email));
                }
            }
        }
        return users;
    }

    public User getUserById(Long userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, email FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return new User(id, name, email);
                } else {
                    return null;
                }
            }
        }
    }

    public User getUserByIdWithDepartment(Long userId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection
                     .prepareStatement("""
                             SELECT u.id as id, name, email, d.department_name as department_name
                             FROM users u
                                 LEFT JOIN department d ON u.id = d.id_user
                             WHERE u.id = ?
                             """)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String department = resultSet.getString("department_name");
                    return new User(id, name, email, new Department(department));
                } else {
                    return null;
                }
            }
        }
    }

    public void updateUserEmail(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET email = ? WHERE name = ?")) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, this.user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE name = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
        }
    }
}
