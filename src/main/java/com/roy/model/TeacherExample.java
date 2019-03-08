package com.roy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TeacherExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public TeacherExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumIsNull() {
            addCriterion("teac_worknum is null");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumIsNotNull() {
            addCriterion("teac_worknum is not null");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumEqualTo(String value) {
            addCriterion("teac_worknum =", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumNotEqualTo(String value) {
            addCriterion("teac_worknum <>", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumGreaterThan(String value) {
            addCriterion("teac_worknum >", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumGreaterThanOrEqualTo(String value) {
            addCriterion("teac_worknum >=", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumLessThan(String value) {
            addCriterion("teac_worknum <", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumLessThanOrEqualTo(String value) {
            addCriterion("teac_worknum <=", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumLike(String value) {
            addCriterion("teac_worknum like", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumNotLike(String value) {
            addCriterion("teac_worknum not like", value, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumIn(List<String> values) {
            addCriterion("teac_worknum in", values, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumNotIn(List<String> values) {
            addCriterion("teac_worknum not in", values, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumBetween(String value1, String value2) {
            addCriterion("teac_worknum between", value1, value2, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacWorknumNotBetween(String value1, String value2) {
            addCriterion("teac_worknum not between", value1, value2, "teacWorknum");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneIsNull() {
            addCriterion("teac_phone is null");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneIsNotNull() {
            addCriterion("teac_phone is not null");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneEqualTo(String value) {
            addCriterion("teac_phone =", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneNotEqualTo(String value) {
            addCriterion("teac_phone <>", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneGreaterThan(String value) {
            addCriterion("teac_phone >", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("teac_phone >=", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneLessThan(String value) {
            addCriterion("teac_phone <", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneLessThanOrEqualTo(String value) {
            addCriterion("teac_phone <=", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneLike(String value) {
            addCriterion("teac_phone like", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneNotLike(String value) {
            addCriterion("teac_phone not like", value, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneIn(List<String> values) {
            addCriterion("teac_phone in", values, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneNotIn(List<String> values) {
            addCriterion("teac_phone not in", values, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneBetween(String value1, String value2) {
            addCriterion("teac_phone between", value1, value2, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacPhoneNotBetween(String value1, String value2) {
            addCriterion("teac_phone not between", value1, value2, "teacPhone");
            return (Criteria) this;
        }

        public Criteria andTeacGenderIsNull() {
            addCriterion("teac_gender is null");
            return (Criteria) this;
        }

        public Criteria andTeacGenderIsNotNull() {
            addCriterion("teac_gender is not null");
            return (Criteria) this;
        }

        public Criteria andTeacGenderEqualTo(String value) {
            addCriterion("teac_gender =", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderNotEqualTo(String value) {
            addCriterion("teac_gender <>", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderGreaterThan(String value) {
            addCriterion("teac_gender >", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderGreaterThanOrEqualTo(String value) {
            addCriterion("teac_gender >=", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderLessThan(String value) {
            addCriterion("teac_gender <", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderLessThanOrEqualTo(String value) {
            addCriterion("teac_gender <=", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderLike(String value) {
            addCriterion("teac_gender like", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderNotLike(String value) {
            addCriterion("teac_gender not like", value, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderIn(List<String> values) {
            addCriterion("teac_gender in", values, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderNotIn(List<String> values) {
            addCriterion("teac_gender not in", values, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderBetween(String value1, String value2) {
            addCriterion("teac_gender between", value1, value2, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacGenderNotBetween(String value1, String value2) {
            addCriterion("teac_gender not between", value1, value2, "teacGender");
            return (Criteria) this;
        }

        public Criteria andTeacNameIsNull() {
            addCriterion("teac_name is null");
            return (Criteria) this;
        }

        public Criteria andTeacNameIsNotNull() {
            addCriterion("teac_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacNameEqualTo(String value) {
            addCriterion("teac_name =", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameNotEqualTo(String value) {
            addCriterion("teac_name <>", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameGreaterThan(String value) {
            addCriterion("teac_name >", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameGreaterThanOrEqualTo(String value) {
            addCriterion("teac_name >=", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameLessThan(String value) {
            addCriterion("teac_name <", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameLessThanOrEqualTo(String value) {
            addCriterion("teac_name <=", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameLike(String value) {
            addCriterion("teac_name like", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameNotLike(String value) {
            addCriterion("teac_name not like", value, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameIn(List<String> values) {
            addCriterion("teac_name in", values, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameNotIn(List<String> values) {
            addCriterion("teac_name not in", values, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameBetween(String value1, String value2) {
            addCriterion("teac_name between", value1, value2, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacNameNotBetween(String value1, String value2) {
            addCriterion("teac_name not between", value1, value2, "teacName");
            return (Criteria) this;
        }

        public Criteria andTeacStateIsNull() {
            addCriterion("teac_state is null");
            return (Criteria) this;
        }

        public Criteria andTeacStateIsNotNull() {
            addCriterion("teac_state is not null");
            return (Criteria) this;
        }

        public Criteria andTeacStateEqualTo(Integer value) {
            addCriterion("teac_state =", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateNotEqualTo(Integer value) {
            addCriterion("teac_state <>", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateGreaterThan(Integer value) {
            addCriterion("teac_state >", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("teac_state >=", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateLessThan(Integer value) {
            addCriterion("teac_state <", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateLessThanOrEqualTo(Integer value) {
            addCriterion("teac_state <=", value, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateIn(List<Integer> values) {
            addCriterion("teac_state in", values, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateNotIn(List<Integer> values) {
            addCriterion("teac_state not in", values, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateBetween(Integer value1, Integer value2) {
            addCriterion("teac_state between", value1, value2, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacStateNotBetween(Integer value1, Integer value2) {
            addCriterion("teac_state not between", value1, value2, "teacState");
            return (Criteria) this;
        }

        public Criteria andTeacBirthIsNull() {
            addCriterion("teac_birth is null");
            return (Criteria) this;
        }

        public Criteria andTeacBirthIsNotNull() {
            addCriterion("teac_birth is not null");
            return (Criteria) this;
        }

        public Criteria andTeacBirthEqualTo(Date value) {
            addCriterionForJDBCDate("teac_birth =", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthNotEqualTo(Date value) {
            addCriterionForJDBCDate("teac_birth <>", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthGreaterThan(Date value) {
            addCriterionForJDBCDate("teac_birth >", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("teac_birth >=", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthLessThan(Date value) {
            addCriterionForJDBCDate("teac_birth <", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("teac_birth <=", value, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthIn(List<Date> values) {
            addCriterionForJDBCDate("teac_birth in", values, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthNotIn(List<Date> values) {
            addCriterionForJDBCDate("teac_birth not in", values, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("teac_birth between", value1, value2, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacBirthNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("teac_birth not between", value1, value2, "teacBirth");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionIsNull() {
            addCriterion("teac_question is null");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionIsNotNull() {
            addCriterion("teac_question is not null");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionEqualTo(String value) {
            addCriterion("teac_question =", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionNotEqualTo(String value) {
            addCriterion("teac_question <>", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionGreaterThan(String value) {
            addCriterion("teac_question >", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("teac_question >=", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionLessThan(String value) {
            addCriterion("teac_question <", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionLessThanOrEqualTo(String value) {
            addCriterion("teac_question <=", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionLike(String value) {
            addCriterion("teac_question like", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionNotLike(String value) {
            addCriterion("teac_question not like", value, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionIn(List<String> values) {
            addCriterion("teac_question in", values, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionNotIn(List<String> values) {
            addCriterion("teac_question not in", values, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionBetween(String value1, String value2) {
            addCriterion("teac_question between", value1, value2, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacQuestionNotBetween(String value1, String value2) {
            addCriterion("teac_question not between", value1, value2, "teacQuestion");
            return (Criteria) this;
        }

        public Criteria andTeacKeyIsNull() {
            addCriterion("teac_key is null");
            return (Criteria) this;
        }

        public Criteria andTeacKeyIsNotNull() {
            addCriterion("teac_key is not null");
            return (Criteria) this;
        }

        public Criteria andTeacKeyEqualTo(String value) {
            addCriterion("teac_key =", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyNotEqualTo(String value) {
            addCriterion("teac_key <>", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyGreaterThan(String value) {
            addCriterion("teac_key >", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyGreaterThanOrEqualTo(String value) {
            addCriterion("teac_key >=", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyLessThan(String value) {
            addCriterion("teac_key <", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyLessThanOrEqualTo(String value) {
            addCriterion("teac_key <=", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyLike(String value) {
            addCriterion("teac_key like", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyNotLike(String value) {
            addCriterion("teac_key not like", value, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyIn(List<String> values) {
            addCriterion("teac_key in", values, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyNotIn(List<String> values) {
            addCriterion("teac_key not in", values, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyBetween(String value1, String value2) {
            addCriterion("teac_key between", value1, value2, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacKeyNotBetween(String value1, String value2) {
            addCriterion("teac_key not between", value1, value2, "teacKey");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordIsNull() {
            addCriterion("teac_password is null");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordIsNotNull() {
            addCriterion("teac_password is not null");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordEqualTo(String value) {
            addCriterion("teac_password =", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordNotEqualTo(String value) {
            addCriterion("teac_password <>", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordGreaterThan(String value) {
            addCriterion("teac_password >", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("teac_password >=", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordLessThan(String value) {
            addCriterion("teac_password <", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordLessThanOrEqualTo(String value) {
            addCriterion("teac_password <=", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordLike(String value) {
            addCriterion("teac_password like", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordNotLike(String value) {
            addCriterion("teac_password not like", value, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordIn(List<String> values) {
            addCriterion("teac_password in", values, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordNotIn(List<String> values) {
            addCriterion("teac_password not in", values, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordBetween(String value1, String value2) {
            addCriterion("teac_password between", value1, value2, "teacPassword");
            return (Criteria) this;
        }

        public Criteria andTeacPasswordNotBetween(String value1, String value2) {
            addCriterion("teac_password not between", value1, value2, "teacPassword");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_teacher
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_teacher
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}