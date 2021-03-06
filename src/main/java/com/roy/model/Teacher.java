package com.roy.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Teacher implements UserDetails {
// public class Teacher implements Serializable {

    //旧密码
    private String oldPwd;

    public Teacher(Long id, String password) {
        this.id = id;
        this.teacPassword = new BCryptPasswordEncoder().encode(password);
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_worknum
     *
     * @mbg.generated
     */
    private String teacWorknum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_phone
     *
     * @mbg.generated
     */
    private String teacPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_gender
     *
     * @mbg.generated
     */
    private String teacGender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_name
     *
     * @mbg.generated
     */
    private String teacName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_state
     *
     * @mbg.generated
     */
    private Integer teacState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_birth
     *
     * @mbg.generated
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date teacBirth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_question
     *
     * @mbg.generated
     */
    private String teacQuestion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_key
     *
     * @mbg.generated
     */
    private String teacKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_teacher.teac_password
     *
     * @mbg.generated
     */
    private String teacPassword;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.id
     *
     * @return the value of t_teacher.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.id
     *
     * @param id the value for t_teacher.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_worknum
     *
     * @return the value of t_teacher.teac_worknum
     *
     * @mbg.generated
     */
    public String getTeacWorknum() {
        return teacWorknum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_worknum
     *
     * @param teacWorknum the value for t_teacher.teac_worknum
     *
     * @mbg.generated
     */
    public void setTeacWorknum(String teacWorknum) {
        this.teacWorknum = teacWorknum == null ? null : teacWorknum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_phone
     *
     * @return the value of t_teacher.teac_phone
     *
     * @mbg.generated
     */
    public String getTeacPhone() {
        return teacPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_phone
     *
     * @param teacPhone the value for t_teacher.teac_phone
     *
     * @mbg.generated
     */
    public void setTeacPhone(String teacPhone) {
        this.teacPhone = teacPhone == null ? null : teacPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_gender
     *
     * @return the value of t_teacher.teac_gender
     *
     * @mbg.generated
     */
    public String getTeacGender() {
        return teacGender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_gender
     *
     * @param teacGender the value for t_teacher.teac_gender
     *
     * @mbg.generated
     */
    public void setTeacGender(String teacGender) {
        this.teacGender = teacGender == null ? null : teacGender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_name
     *
     * @return the value of t_teacher.teac_name
     *
     * @mbg.generated
     */
    public String getTeacName() {
        return teacName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_name
     *
     * @param teacName the value for t_teacher.teac_name
     *
     * @mbg.generated
     */
    public void setTeacName(String teacName) {
        this.teacName = teacName == null ? null : teacName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_state
     *
     * @return the value of t_teacher.teac_state
     *
     * @mbg.generated
     */
    public Integer getTeacState() {
        return teacState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_state
     *
     * @param teacState the value for t_teacher.teac_state
     *
     * @mbg.generated
     */
    public void setTeacState(Integer teacState) {
        this.teacState = teacState;

    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_birth
     *
     * @return the value of t_teacher.teac_birth
     *
     * @mbg.generated
     */
    public Date getTeacBirth() {
        return teacBirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_birth
     *
     * @param teacBirth the value for t_teacher.teac_birth
     *
     * @mbg.generated
     */
    public void setTeacBirth(Date teacBirth) {
        this.teacBirth = teacBirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_question
     *
     * @return the value of t_teacher.teac_question
     *
     * @mbg.generated
     */
    public String getTeacQuestion() {
        return teacQuestion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_question
     *
     * @param teacQuestion the value for t_teacher.teac_question
     *
     * @mbg.generated
     */
    public void setTeacQuestion(String teacQuestion) {
        this.teacQuestion = teacQuestion == null ? null : teacQuestion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_key
     *
     * @return the value of t_teacher.teac_key
     *
     * @mbg.generated
     */
    public String getTeacKey() {
        return teacKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_key
     *
     * @param teacKey the value for t_teacher.teac_key
     *
     * @mbg.generated
     */
    public void setTeacKey(String teacKey) {
        this.teacKey = teacKey == null ? null : teacKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_teacher.teac_password
     *
     * @return the value of t_teacher.teac_password
     *
     * @mbg.generated
     */
    public String getTeacPassword() {
        return teacPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_teacher.teac_password
     *
     * @param teacPassword the value for t_teacher.teac_password
     *
     * @mbg.generated
     */
    public void setTeacPassword(String teacPassword) {
        this.teacPassword = teacPassword == null ? null : teacPassword.trim();
    }

    public Teacher() {
    }

    public Teacher(String oldPwd, Long id, String teacWorknum, String teacPassword) {
        this.oldPwd = oldPwd;
        this.id = id;
        this.teacWorknum = teacWorknum;
        this.teacPassword = teacPassword;
    }

    public Teacher(String teacWorknum, String teacPhone,
                   String teacGender, String teacName, Date teacBirth,
                   String teacPassword) {
        this.teacWorknum = teacWorknum;
        this.teacPhone = teacPhone;
        this.teacGender = teacGender;
        this.teacName = teacName;
        this.teacBirth = teacBirth;
        this.teacPassword = teacPassword;
    }

    public Teacher(String oldPwd, Long id, String teacWorknum, String teacPhone, String teacGender,
                   String teacName, Integer teacState, Date teacBirth, String teacQuestion,
                   String teacKey, String teacPassword) {
        this.oldPwd = oldPwd;
        this.id = id;
        this.teacWorknum = teacWorknum;
        this.teacPhone = teacPhone;
        this.teacGender = teacGender;
        this.teacName = teacName;
        this.teacState = teacState;
        this.teacBirth = teacBirth;
        this.teacQuestion = teacQuestion;
        this.teacKey = teacKey;
        this.teacPassword = teacPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "oldPwd='" + oldPwd + '\'' +
                ", id=" + id +
                ", teacWorknum='" + teacWorknum + '\'' +
                ", teacPhone='" + teacPhone + '\'' +
                ", teacGender='" + teacGender + '\'' +
                ", teacName='" + teacName + '\'' +
                ", teacState=" + teacState +
                ", teacBirth=" + teacBirth +
                ", teacQuestion='" + teacQuestion + '\'' +
                ", teacKey='" + teacKey + '\'' +
                ", teacPassword='" + teacPassword + '\'' +
                '}';
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();//GrantedAuthority是security提供的权限类，
        List<Role> roles2 = new ArrayList<>();
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_TEACHER");
        role.setNameZh("老师");
        roles2.add(role);
        for (Role role1 : roles2) {
            authorities.add(new SimpleGrantedAuthority(role1.getName()));
        }
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return teacPassword;
    }

    @Override
    public String getUsername() {
        return teacWorknum;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}