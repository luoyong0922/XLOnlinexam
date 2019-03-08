package com.roy.service;

import com.github.pagehelper.PageInfo;
import com.roy.model.Admin;
import com.roy.model.Paper;
import com.roy.model.Student;
import com.roy.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface AdminService {


    //关键字查询
    PageInfo searchTeacherBykeywords(Integer pageIndex,String keywords,Integer state);

    //所有老师的分页信息
    PageInfo searchTeacherByPageHelper(Integer pageIndex);

    //根据工号判断老师是否存在
    boolean getTeacherByTeacWorknum(String teacWorknum);

    //添加老师
    boolean addTeacher(Teacher teacher);

    //删除老师
    boolean deleteTeacher(Long teacId);

    //根据id获取老师
    Teacher getTeacherByTeacId(Long teacId);

    //更新老师
    boolean updateTeacher(Teacher teacher);

    //关键字查询学生
    PageInfo searchStudentBykeywords(Integer pageIndex,String keywords,Integer state);

    //学生的分页查询信息
    PageInfo searchStudentByPageHelper(Integer pageIndex);

    //根据id获得学生实体
    Student getStudentByStuId(Long stuId);

    //更新学生
    boolean updateStudent(Student student);

    //删除学生
    boolean deleteStudent(long stuId);

    //根据学号判断该学生是否存在
    boolean getStudentByStuNum(String stuNum);

    //添加学生
    boolean addStudent(Student student);
    //修改个人信息
    boolean updateAdmin(Admin admin);

    //得到所有试卷
    List<Paper> getAllPapers();

    PageInfo searchAllPapersByPageHelper(Integer pageIndex, String courseName, String teacName);

    boolean approvel(Long ids, Integer op, int i);
}
