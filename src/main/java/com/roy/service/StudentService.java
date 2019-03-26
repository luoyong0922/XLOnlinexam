package com.roy.service;

import com.roy.model.HomeWork;
import com.roy.model.StuCourse;
import com.roy.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface StudentService {
    Student getStudentById(Long id);

    //根据学号查询学生信息
    Student selectByAccount(String stuNum);
    //更新学生信息
    boolean updateStudent(Student student);
    //查询学生课程记录
    List searchStuCourse(StuCourse stuCourse);
    //添加学生课程记录
    boolean addStuCourse(StuCourse stuCourse);

    List<HomeWork> getHomeWorkByTcId(Long teacCourseId);

    HomeWork getHomeWorkById(Long homeWorkId);
}
