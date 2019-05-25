package com.contest.schoolsuggestions.model;

import java.io.Serializable;

public class PostInfo implements Serializable {

    private Long id;
    private String title;
    private String content;
    private Integer agree;
    private Integer disagree;
    private String feedback;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getDisagree() {
        return disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public PostInfo(PostInfoTO postInfoTO) {
        this.id = postInfoTO.getId();
        this.title = postInfoTO.getTitle();
        this.content = postInfoTO.getContent();
        this.agree = postInfoTO.getAgree();
        this.disagree = postInfoTO.getDisagree();
        this.feedback = postInfoTO.getFeedback();
    }
}
