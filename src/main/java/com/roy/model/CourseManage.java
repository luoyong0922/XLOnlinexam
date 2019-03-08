package com.roy.model;

import java.math.BigDecimal;
import java.util.Date;

public class CourseManage {

    private String courseNum;

    private String courseName;

    private BigDecimal courseCredit;

    private String teacName;

    private Long teacId;
    private Long teacCourseId;
    private Long courseId;

    private Date startTime;

    private Date endTime;

    public Long getTeacCourseId() {
        return teacCourseId;
    }

    public void setTeacCourseId(Long teacCourseId) {
        this.teacCourseId = teacCourseId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(BigDecimal courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getTeacName() {
        return teacName;
    }

    public void setTeacName(String teacName) {
        this.teacName = teacName;
    }

    public Long getTeacId() {
        return teacId;
    }

    public void setTeacId(Long teacId) {
        this.teacId = teacId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "CourseManage{" +
                "courseNum='" + courseNum + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                ", teacName='" + teacName + '\'' +
                ", teacId=" + teacId +
                ", teacCourseId=" + teacCourseId +
                ", courseId=" + courseId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
