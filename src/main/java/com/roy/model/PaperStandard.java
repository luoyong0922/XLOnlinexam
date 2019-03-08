package com.roy.model;

public class PaperStandard {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.test_type
     *
     * @mbg.generated
     */
    private String testType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.test_amount
     *
     * @mbg.generated
     */
    private Integer testAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.test_value
     *
     * @mbg.generated
     */
    private Integer testValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.teac_course_id
     *
     * @mbg.generated
     */
    private Long teacCourseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_paper_standard.test_time
     *
     * @mbg.generated
     */
    private Integer testTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.id
     *
     * @return the value of t_paper_standard.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.id
     *
     * @param id the value for t_paper_standard.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.test_type
     *
     * @return the value of t_paper_standard.test_type
     *
     * @mbg.generated
     */
    public String getTestType() {
        return testType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.test_type
     *
     * @param testType the value for t_paper_standard.test_type
     *
     * @mbg.generated
     */
    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.test_amount
     *
     * @return the value of t_paper_standard.test_amount
     *
     * @mbg.generated
     */
    public Integer getTestAmount() {
        return testAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.test_amount
     *
     * @param testAmount the value for t_paper_standard.test_amount
     *
     * @mbg.generated
     */
    public void setTestAmount(Integer testAmount) {
        this.testAmount = testAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.test_value
     *
     * @return the value of t_paper_standard.test_value
     *
     * @mbg.generated
     */
    public Integer getTestValue() {
        return testValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.test_value
     *
     * @param testValue the value for t_paper_standard.test_value
     *
     * @mbg.generated
     */
    public void setTestValue(Integer testValue) {
        this.testValue = testValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.teac_course_id
     *
     * @return the value of t_paper_standard.teac_course_id
     *
     * @mbg.generated
     */
    public Long getTeacCourseId() {
        return teacCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.teac_course_id
     *
     * @param teacCourseId the value for t_paper_standard.teac_course_id
     *
     * @mbg.generated
     */
    public void setTeacCourseId(Long teacCourseId) {
        this.teacCourseId = teacCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_paper_standard.test_time
     *
     * @return the value of t_paper_standard.test_time
     *
     * @mbg.generated
     */
    public Integer getTestTime() {
        return testTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_paper_standard.test_time
     *
     * @param testTime the value for t_paper_standard.test_time
     *
     * @mbg.generated
     */
    public void setTestTime(Integer testTime) {
        this.testTime = testTime;
    }

    public PaperStandard() {
    }

    public PaperStandard(Long id, String testType, Integer testAmount, Integer testValue, Long teacCourseId, Integer testTime) {
        this.id = id;
        this.testType = testType;
        this.testAmount = testAmount;
        this.testValue = testValue;
        this.teacCourseId = teacCourseId;
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "PaperStandard{" +
                "id=" + id +
                ", testType='" + testType + '\'' +
                ", testAmount=" + testAmount +
                ", testValue=" + testValue +
                ", teacCourseId=" + teacCourseId +
                ", testTime=" + testTime +
                '}';
    }
}