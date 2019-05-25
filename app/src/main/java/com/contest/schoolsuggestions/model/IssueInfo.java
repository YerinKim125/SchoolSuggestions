package com.contest.schoolsuggestions.model;

import java.io.Serializable;

public class IssueInfo implements Serializable {

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IssueInfo(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
