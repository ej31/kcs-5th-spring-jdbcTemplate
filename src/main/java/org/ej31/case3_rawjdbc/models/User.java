package org.ej31.case3_rawjdbc.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Department department;

    public User(Long id, String name, String email, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}