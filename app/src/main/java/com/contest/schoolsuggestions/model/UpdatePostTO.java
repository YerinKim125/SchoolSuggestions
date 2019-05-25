package com.contest.schoolsuggestions.model;

public class UpdatePostTO {

    private Integer agree;
    private Integer disagree;
    private String feedback;

    public UpdatePostTO(Integer agree, Integer disagree, String feedback) {
        this.agree = agree;
        this.disagree = disagree;
        this.feedback = feedback;
    }
}