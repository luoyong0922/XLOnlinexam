package com.roy.service.serviceImpl;

import com.roy.mapper.AdminMapper;
import com.roy.mapper.StudentMapper;
import com.roy.mapper.TeacherMapper;
import com.roy.model.*;
import com.roy.service.LoginService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public List<Admin> adminLogin(String adminPhone, String adminPassword) {
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
    public List<Student> studenLogin(String stuNum, String stuPassword) {
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
    public List<Teacher> teacherLogin(String teacWorknum, String teacPassword) {
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
            result = studenLogin(account,null);
        }else if(role == 2){//老师
            result = teacherLogin(account,null);
        }else if(role == 3){//管理员
            result = adminLogin(account,null);
        }
        return result;
    }

    /**
     * 初始化密码：123456
     * @param id
     * @param role 角色：1 学生，2 教师，3 管理员
     * @return
     */
    @Override
    public boolean initPassword(int role, Long id) {
        boolean result = false;
        if(role == 1){//学生
            Student student = new Student(id,"123456");
            result = studentDao.updateByPrimaryKeySelective(student) > 0;
        }else if(role == 2){//老师
            Teacher teacher = new Teacher(id,"123456");
            result = teacherDao.updateByPrimaryKeySelective(teacher) > 0;
        }else if(role == 3){//管理员
            Admin admin = new Admin(id,"123456");
            result = adminDao.updateByPrimaryKeySelective(admin) >0;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List users = new ArrayList();
        users = adminLogin(s,null);
        if(users.size() > 0){
            return (Admin)users.get(0);
        }
        users = studenLogin(s,null);
        if(users.size() > 0){
            return (Student)users.get(0);
        }
        users = teacherLogin(s,null);
        if(users.size() > 0){
            return (Teacher)users.get(0);
        }
            throw new UsernameNotFoundException("用户名不对");

    }
}
