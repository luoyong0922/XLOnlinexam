package com.roy.model;

public class AdminViewQuestion {

    private String courseName;

    private Long teacCourseId;

    private Long id;
    private String title;
    private String dificult;
    private String questionType;
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getTeacCourseId() {
        return teacCourseId;
    }

    public void setTeacCourseId(Long teacCourseId) {
        this.teacCourseId = teacCourseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDificult() {
        return dificult;
    }

    public void setDificult(String dificult) {
        this.dificult = dificult;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public AdminViewQuestion() {
    }

    public AdminViewQuestion(String courseName, Long teacCourseId, Long id, String title, String dificult, String questionType) {
        this.courseName = courseName;
        this.teacCourseId = teacCourseId;
        this.id = id;
        this.title = title;
        this.dificult = dificult;
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "AdminViewQuestion{" +
                "courseName='" + courseName + '\'' +
                ", teacCourseId=" + teacCourseId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", dificult='" + dificult + '\'' +
                ", questionType='" + questionType + '\'' +
                '}';
    }
}
