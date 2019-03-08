package com.roy.service;

import com.roy.model.Admin;
import com.roy.model.Student;
import com.roy.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

//@Repository
public interface LoginService {

    // 登录校验
    public List<Admin> AdminLogin(String adminPhone, String adminPassword);
    public List<Student> StudenLogin(String stuNum, String stuPassword);
    public List<Teacher> TeacherLogin(String teacWorknum, String teacPassword);
    /**
     * 找回密码
     * 根据账号查询信息
     * @param account 账号：学号  工号   手机号
     * @param role 角色：1 学生，2 教师，3 管理员
     * @return
     */
    public List selectByAccount(String account,int role);

    /**
     * 注销登录
     * @param session
     * @param sessionStatus
     * @return
     */
    public void logout(HttpSession session, SessionStatus sessionStatus);

}
