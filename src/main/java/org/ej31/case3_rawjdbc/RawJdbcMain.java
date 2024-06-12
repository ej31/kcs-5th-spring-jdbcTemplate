package org.ej31.case3_rawjdbc;

import org.ej31.case3_rawjdbc.models.Department;
import org.ej31.case3_rawjdbc.models.User;

import java.sql.SQLException;
import java.util.List;


public class RawJdbcMain {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        try {
            // 데이터 삽입 예제
            User newUser = new User("홍길동", "hong@example.com");
            userDAO.insertUser(newUser);
            System.out.println("데이터가 삽입되었습니다.");

            // 데이터 조회 예제
            List<User> users = userDAO.getUsers();
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", 이름: " + user.getName() + ", 이메일: " + user.getEmail());

                // 시연을 위해 작성한 임시 로직 -> 마지막 유저를 받는다.
                newUser.setEmail(user.getEmail());
                newUser.setName(user.getName());
                newUser.setId(user.getId());
            }

            User userById = userDAO.getUserById(newUser.getId()); // 실제로는 request 를 통해 유저에게서 아이디를 받는다.
            System.out.println("ID: " + userById.getId() + ", 이름: " + userById.getName() + ", 이메일: " + userById.getEmail());

            // 데이터 수정 예제
            userById.setEmail("hong_gil_dong@example.com");
            userDAO.updateUserEmail(userById);
            System.out.println("데이터가 수정되었습니다.");

            // 데이터 삭제 예제
            // userDAO.deleteUser(newUser);
            // System.out.println("데이터가 삭제되었습니다.");

            User userWithDepartment = new User("홍길동", "hong@example.com", new Department("개발팀"));
            userDAO.insertUserWithDepartment(userWithDepartment);
            System.out.println(userWithDepartment.getDepartment().getDepartmentName());


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
