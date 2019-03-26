package com.roy.service;

import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface CourseService {

    TeacCourse getTeacCourse(Long id);

    Course getCourseById(Long id);

    Course getCourseByteacCourseId(Long tcId);

    Course getCourseIdByNameAndCredit(Course course);

    List<Teacher> getAllTeacher();

//    Teacher getTeacherById(Long id);

    List<Student> getAllStudent();

//    Student getStudentById(Long id);

    //新增课程
    public boolean addCourse(Course course);


    //查询课程表所有课程
    public List<Course> getAllCourse();

    boolean addTeacCourse(TeacCourse teacCourse);

    boolean addStuCourse(StuCourse stuCourse);

    //查询课程列表
    // public PageInfo searchCourseMessage(Integer pageIndex, Long id, int role);

    //修改课程
    public boolean modifyCourse(Course course);


    boolean modifyTeacCourse(TeacCourse course);

    //删除课程
    public int deleteCourse(List<Long> ids);

    //得到所有的teaccourse
    List<TeacCourse> getAllTeacCourses();

    //查询课程信息
    PageInfo getMyCoursesMessage(Long id, int role, String courseName, String teacName, Long tcid);


    List<TeacCourse> getStuCoursesMessage(Long stuId,Long tcid);

    List<TeacCourse> getTeacCoursesMessage(Long teacId,Long tcid);

    TeacCourse improveCourseMsg(TeacCourse teacCourse);

    //根据teacId得到所有的teac_course记录
    List<TeacCourse> getTeacCourseByteacId(Long teacId);

    int deleteTeacCourse(List<Long> tcId);

}
