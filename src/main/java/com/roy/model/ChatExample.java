package com.roy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public ChatExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
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
     * This method corresponds to the database table t_chat
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
     * This method corresponds to the database table t_chat
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_chat
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
     * This class corresponds to the database table t_chat
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

        public Criteria andSendIdIsNull() {
            addCriterion("send_id is null");
            return (Criteria) this;
        }

        public Criteria andSendIdIsNotNull() {
            addCriterion("send_id is not null");
            return (Criteria) this;
        }

        public Criteria andSendIdEqualTo(Long value) {
            addCriterion("send_id =", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotEqualTo(Long value) {
            addCriterion("send_id <>", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThan(Long value) {
            addCriterion("send_id >", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdGreaterThanOrEqualTo(Long value) {
            addCriterion("send_id >=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThan(Long value) {
            addCriterion("send_id <", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdLessThanOrEqualTo(Long value) {
            addCriterion("send_id <=", value, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdIn(List<Long> values) {
            addCriterion("send_id in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotIn(List<Long> values) {
            addCriterion("send_id not in", values, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdBetween(Long value1, Long value2) {
            addCriterion("send_id between", value1, value2, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendIdNotBetween(Long value1, Long value2) {
            addCriterion("send_id not between", value1, value2, "sendId");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNull() {
            addCriterion("send_name is null");
            return (Criteria) this;
        }

        public Criteria andSendNameIsNotNull() {
            addCriterion("send_name is not null");
            return (Criteria) this;
        }

        public Criteria andSendNameEqualTo(String value) {
            addCriterion("send_name =", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotEqualTo(String value) {
            addCriterion("send_name <>", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThan(String value) {
            addCriterion("send_name >", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameGreaterThanOrEqualTo(String value) {
            addCriterion("send_name >=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThan(String value) {
            addCriterion("send_name <", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLessThanOrEqualTo(String value) {
            addCriterion("send_name <=", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameLike(String value) {
            addCriterion("send_name like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotLike(String value) {
            addCriterion("send_name not like", value, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameIn(List<String> values) {
            addCriterion("send_name in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotIn(List<String> values) {
            addCriterion("send_name not in", values, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameBetween(String value1, String value2) {
            addCriterion("send_name between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendNameNotBetween(String value1, String value2) {
            addCriterion("send_name not between", value1, value2, "sendName");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("message like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("message not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsIsNull() {
            addCriterion("receive_ids is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsIsNotNull() {
            addCriterion("receive_ids is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsEqualTo(String value) {
            addCriterion("receive_ids =", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsNotEqualTo(String value) {
            addCriterion("receive_ids <>", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsGreaterThan(String value) {
            addCriterion("receive_ids >", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsGreaterThanOrEqualTo(String value) {
            addCriterion("receive_ids >=", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsLessThan(String value) {
            addCriterion("receive_ids <", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsLessThanOrEqualTo(String value) {
            addCriterion("receive_ids <=", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsLike(String value) {
            addCriterion("receive_ids like", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsNotLike(String value) {
            addCriterion("receive_ids not like", value, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsIn(List<String> values) {
            addCriterion("receive_ids in", values, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsNotIn(List<String> values) {
            addCriterion("receive_ids not in", values, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsBetween(String value1, String value2) {
            addCriterion("receive_ids between", value1, value2, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andReceiveIdsNotBetween(String value1, String value2) {
            addCriterion("receive_ids not between", value1, value2, "receiveIds");
            return (Criteria) this;
        }

        public Criteria andAttachIsNull() {
            addCriterion("attach is null");
            return (Criteria) this;
        }

        public Criteria andAttachIsNotNull() {
            addCriterion("attach is not null");
            return (Criteria) this;
        }

        public Criteria andAttachEqualTo(String value) {
            addCriterion("attach =", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachNotEqualTo(String value) {
            addCriterion("attach <>", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachGreaterThan(String value) {
            addCriterion("attach >", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachGreaterThanOrEqualTo(String value) {
            addCriterion("attach >=", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachLessThan(String value) {
            addCriterion("attach <", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachLessThanOrEqualTo(String value) {
            addCriterion("attach <=", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachLike(String value) {
            addCriterion("attach like", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachNotLike(String value) {
            addCriterion("attach not like", value, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachIn(List<String> values) {
            addCriterion("attach in", values, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachNotIn(List<String> values) {
            addCriterion("attach not in", values, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachBetween(String value1, String value2) {
            addCriterion("attach between", value1, value2, "attach");
            return (Criteria) this;
        }

        public Criteria andAttachNotBetween(String value1, String value2) {
            addCriterion("attach not between", value1, value2, "attach");
            return (Criteria) this;
        }

        public Criteria andTcidIsNull() {
            addCriterion("tcId is null");
            return (Criteria) this;
        }

        public Criteria andTcidIsNotNull() {
            addCriterion("tcId is not null");
            return (Criteria) this;
        }

        public Criteria andTcidEqualTo(Long value) {
            addCriterion("tcId =", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidNotEqualTo(Long value) {
            addCriterion("tcId <>", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidGreaterThan(Long value) {
            addCriterion("tcId >", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidGreaterThanOrEqualTo(Long value) {
            addCriterion("tcId >=", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidLessThan(Long value) {
            addCriterion("tcId <", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidLessThanOrEqualTo(Long value) {
            addCriterion("tcId <=", value, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidIn(List<Long> values) {
            addCriterion("tcId in", values, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidNotIn(List<Long> values) {
            addCriterion("tcId not in", values, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidBetween(Long value1, Long value2) {
            addCriterion("tcId between", value1, value2, "tcid");
            return (Criteria) this;
        }

        public Criteria andTcidNotBetween(Long value1, Long value2) {
            addCriterion("tcId not between", value1, value2, "tcid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_chat
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
     * This class corresponds to the database table t_chat
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