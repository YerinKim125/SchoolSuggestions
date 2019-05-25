package com.contest.schoolsuggestions.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private Long id;
    private String name;
    private String studentInfo;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentInfo() {
        return studentInfo;
    }
}
