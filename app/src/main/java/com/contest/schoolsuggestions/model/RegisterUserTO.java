package com.contest.schoolsuggestions.model;

public class RegisterUserTO {

    private String email;
    private String password;
    private String name;
    private String studentInfo;

    public RegisterUserTO(String email, String password, String name, String studentInfo) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.studentInfo = studentInfo;
    }
}
