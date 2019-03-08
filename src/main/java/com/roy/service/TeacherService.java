package com.roy.service;

import com.github.pagehelper.PageInfo;
import com.roy.model.HomeWork;
import com.roy.model.StuScore;
import com.roy.model.TeacCourse;
import com.roy.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//@Repository
public interface TeacherService {

    //根据id更新教师信息
    public boolean updateTeacherById(Teacher teacher);


    //根据老师id得到老师信息
    Teacher getTeacByTeacId(Long teacId);

    //根据teacCourseId去查学生成绩表，得到学生成绩表的记录的集合
    List<StuScore> getAllStuScore(Long teacCourseId);

    //遍历teanId对应的学生成绩对象,得到所需要的stuScore对象的集合
    List<StuScore> getAllNeedStuScore(Long teacCourseId);
    //分页信息
    PageInfo SearchAllNeedStuScoreByPageHelper(Integer pageIndex, Long teacCourseId);

    PageInfo searchStuScore(Integer pageIndex, Long stuId, Long teacCourseId);

    //课程名称和teacCourse
    List<TeacCourse> getCourseName(Long teacId);

    //根据teacCourseId去查学生成绩表，得到学生成绩表的记录的集合,遍历集合得到分数为优秀的人数
    Map<String, Integer> getPieData(Long teacCourseId);


    //插入homeWork记录
    Integer selectCourseAmount(Long teacCourseId);

    //插入homeWork记录
    boolean insertIntoHomeWork(HomeWork homeWork);
    //查询所有教师课程表的课程名称
    List<TeacCourse> getAllCourseName();


    //根据teacCourseId得到这门课的所有学生的成绩
    List<StuScore> getStuScoreByTeacCourseId(Long teacCourseId);

    //根据teacCourseId对应的List<StuScore>去得到名字和成绩的map对象
    Map<String,Integer> getMapNameandScore(Long teacCourseId);

    //对map对象进行排序
    List<Map.Entry<String, Integer>> Order(Map<String, Integer> map);
}
