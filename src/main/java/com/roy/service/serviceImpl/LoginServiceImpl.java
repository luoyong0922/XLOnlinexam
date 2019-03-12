package com.roy.service.serviceImpl;

import com.roy.mapper.AdminMapper;
import com.roy.mapper.StudentMapper;
import com.roy.mapper.TeacherMapper;
import com.roy.model.*;
import com.roy.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Resource
    AdminMapper adminDao;
    @Resource
    StudentMapper studentDao;
    @Resource
    TeacherMapper teacherDao;

    @Override
    public List<Admin> AdminLogin(String adminPhone, String adminPassword) {
        AdminExample example=new AdminExample();
        AdminExample.Criteria criteria=example.createCriteria();

        criteria.andAdminPhoneEqualTo(adminPhone);
        if(adminPassword != null) {
            criteria.andAdminPasswordEqualTo(adminPassword);
        }
        List<Admin> admin = adminDao.selectByExample(example);
        return admin;
    }

    @Override
    public List<Student> StudenLogin(String stuNum, String stuPassword) {
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStuNumEqualTo(stuNum);
        if(stuPassword != null){
            criteria.andStuPasswordEqualTo(stuPassword);
        }
        List<Student> student=studentDao.selectByExample(example);
        return student;
    }

    @Override
    public List<Teacher> TeacherLogin(String teacWorknum, String teacPassword) {
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        criteria.andTeacWorknumEqualTo(teacWorknum);
        if(teacPassword != null) {
            criteria.andTeacPasswordEqualTo(teacPassword);
        }
        List<Teacher> teacher=teacherDao.selectByExample(example);
        return teacher;
    }

    /**
     * 找回密码
     * @param account 账号：学号  工号   手机号
     * @param role 角色：1 学生，2 教师，3 管理员
     * @return
     */
    @Override
    public List selectByAccount(String account,int role) {
        List result = new ArrayList();
        if(role == 1){//学生
            result = StudenLogin(account,null);
        }else if(role == 2){//老师
            result = TeacherLogin(account,null);
        }else if(role == 3){//管理员
            result = AdminLogin(account,null);
        }
        return result;
    }

    /**
     * 注销登录
     * @param session
     * @param sessionStatus
     */
    @Override
    public void logout(HttpSession session, SessionStatus sessionStatus){
        Long id = (Long) session.getAttribute("id");
        String name = (String) session.getAttribute("name");
        if(id != null){
            session.invalidate();
            sessionStatus.setComplete(); //让session过期
            System.out.println(name+"正在注销当前账户");
        }
    }

}
