package com.roy.model;

import java.util.Date;

public class Chat {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.send_id
     *
     * @mbg.generated
     */
    private Long sendId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.send_name
     *
     * @mbg.generated
     */
    private String sendName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.send_time
     *
     * @mbg.generated
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.message
     *
     * @mbg.generated
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.receive_ids
     *
     * @mbg.generated
     */
    private String receiveIds;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.attach
     *
     * @mbg.generated
     */
    private String attach;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.tcId
     *
     * @mbg.generated
     */
    private Long tcid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.id
     *
     * @return the value of t_chat.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.id
     *
     * @param id the value for t_chat.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.send_id
     *
     * @return the value of t_chat.send_id
     *
     * @mbg.generated
     */
    public Long getSendId() {
        return sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.send_id
     *
     * @param sendId the value for t_chat.send_id
     *
     * @mbg.generated
     */
    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.send_name
     *
     * @return the value of t_chat.send_name
     *
     * @mbg.generated
     */
    public String getSendName() {
        return sendName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.send_name
     *
     * @param sendName the value for t_chat.send_name
     *
     * @mbg.generated
     */
    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.send_time
     *
     * @return the value of t_chat.send_time
     *
     * @mbg.generated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.send_time
     *
     * @param sendTime the value for t_chat.send_time
     *
     * @mbg.generated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.message
     *
     * @return the value of t_chat.message
     *
     * @mbg.generated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.message
     *
     * @param message the value for t_chat.message
     *
     * @mbg.generated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.status
     *
     * @return the value of t_chat.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.status
     *
     * @param status the value for t_chat.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.receive_ids
     *
     * @return the value of t_chat.receive_ids
     *
     * @mbg.generated
     */
    public String getReceiveIds() {
        return receiveIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.receive_ids
     *
     * @param receiveIds the value for t_chat.receive_ids
     *
     * @mbg.generated
     */
    public void setReceiveIds(String receiveIds) {
        this.receiveIds = receiveIds == null ? null : receiveIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.attach
     *
     * @return the value of t_chat.attach
     *
     * @mbg.generated
     */
    public String getAttach() {
        return attach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.attach
     *
     * @param attach the value for t_chat.attach
     *
     * @mbg.generated
     */
    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.tcId
     *
     * @return the value of t_chat.tcId
     *
     * @mbg.generated
     */
    public Long getTcid() {
        return tcid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.tcId
     *
     * @param tcid the value for t_chat.tcId
     *
     * @mbg.generated
     */
    public void setTcid(Long tcid) {
        this.tcid = tcid;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", sendId=" + sendId +
                ", sendName='" + sendName + '\'' +
                ", sendTime=" + sendTime +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", receiveIds='" + receiveIds + '\'' +
                ", attach='" + attach + '\'' +
                ", tcid=" + tcid +
                '}';
    }
}