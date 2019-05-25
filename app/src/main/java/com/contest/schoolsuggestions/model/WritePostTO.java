package com.contest.schoolsuggestions.model;

public class WritePostTO {

    private Long userId;
    private String title;
    private String content;

    public WritePostTO(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}
