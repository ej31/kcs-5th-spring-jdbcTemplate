package org.ej31.case3_rawjdbc.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
    private String departmentName;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    // ... getter/setter 추가
}