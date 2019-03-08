package com.roy.model;

import java.util.Date;

public class PaperManage {
    private String courseName;
    private String teacName;
    private Date testTime;
    private String stuName;
    private int totalScore;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacName() {
        return teacName;
    }

    public void setTeacName(String teacName) {
        this.teacName = teacName;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
