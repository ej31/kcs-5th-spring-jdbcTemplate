package org.ej31.case3_rawjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RawJdbcMain {
    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://localhost:3306/jeff_db";
        String user = "jeff";
        String password = "123123..";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("데이터베이스에 연결되었습니다.");

            // 데이터 삽입 예제
            String insertSql = "INSERT INTO users (name, email) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, "홍길동");
            preparedStatement.setString(2, "hong@example.com");
            preparedStatement.executeUpdate();
            System.out.println("데이터가 삽입되었습니다.");

            // 데이터 조회 예제
            String selectSql = "SELECT * FROM users";
            preparedStatement = connection.prepareStatement(selectSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", 이름: " + name + ", 이메일: " + email);
            }

            // 데이터 수정 예제
            String updateSql = "UPDATE users SET email = ? WHERE name = ?";
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, "hong_gil_dong@example.com");
            preparedStatement.setString(2, "홍길동");
            preparedStatement.executeUpdate();
            System.out.println("데이터가 수정되었습니다.");

            // 데이터 삭제 예제
            String deleteSql = "DELETE FROM users WHERE name = ?";
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, "홍길동");
            preparedStatement.executeUpdate();
            System.out.println("데이터가 삭제되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
