package com.roy.controller;
import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.LoginService;
import com.roy.service.StudentService;
import com.roy.service.TeacherService;
import com.roy.utils.UserUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/studentController")
public class StudentController {
    @Resource
    private StudentService studentService;
    @Resource
    private LoginService loginService;
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;

    //显示学生的资料
    //显示个人资料
    @RequestMapping(value = "showStudentMessage/{stuNum}",method = RequestMethod.GET)
    public String showStudentMessage(@PathVariable("stuNum")String stuNum,
                                     Model model){
        List<Student> student = loginService.selectByAccount(stuNum,1);
        if(student.size() > 0){
            model.addAttribute("student",student.get(0));
            System.out.println(student);
            return "student/showStudentMessage";
        }
        return "student/studentIndex";
    }
    //返回到学生主页
    @RequestMapping("toStudentIndex")
    public String toStudentIndex(){
        return "student/studentIndex";
    }
    //返回到学生主页
    @RequestMapping("toWelcome")
    public String toWelcome(){
        return "student/welcome";
    }
    //去更新学生界面
    @RequestMapping(value = "toModifyStudentMsg/{stuNum}",method = RequestMethod.GET)
    public String toUpdateStudentPassword(@PathVariable("stuNum")String stuNum, Model model){
        List student = loginService.selectByAccount(stuNum,1);
        if(student.size() > 0){
            model.addAttribute("student",student.get(0));
            return "student/updateStudentPassword";
        }
        return "404";
    }
    // 保存密码的修改
    @RequestMapping("updateStudentMsg")
    @ResponseBody
    public RespResult doModifyMessage(Student student){
        if(student != null){
            boolean flag = false;
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(student.getOldPwd() != null) {
                Student student0 = (Student)UserUtils.getCurrentUser();
                flag = encoder.matches(student.getOldPwd(), student0.getStuPassword());
            }
            if (flag || student.getStuPassword().length() == 60) {
                boolean result = studentService.updateStudent(student);
                if (result) {
                    return new RespResult("success", "修改成功!");
                } else {
                    return new RespResult("fail", "修改失败!");
                }
            } else {
                return new RespResult("fail", "原密码错误!");
            }
        }else {
            return new RespResult("fail", "请先登录!");
        }
    }
    //去学生选课界面
    @RequestMapping(value = "toSelectCourse")
    public String toUpdateStudentPassword(@RequestParam(value = "courseName",defaultValue = "")String courseName,
                                          @RequestParam(value = "teacName",defaultValue = "")String teacName,Model model) {

        PageInfo pageInfo = courseService.getMyCoursesMessage(1L, 3, courseName, teacName, 0L);
        System.out.println("查询课程：" + pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("courseName", courseName);
        model.addAttribute("teacName", teacName);
        return "course/selectCourse";
    }

    //学生选课
    @RequestMapping("selectCount")
    @ResponseBody
    public RespResult selectCount(StuCourse stuCourse) {
        if (stuCourse.getStuId() == null) {
            return new RespResult("fail", "请先登录!");
        } else {
            List courses = studentService.searchStuCourse(stuCourse);
            if (courses.size() > 0) {
                return new RespResult("fail", "你已选过该课程!");
            } else {
                if (studentService.addStuCourse(stuCourse)) {
                    return new RespResult("success", "选课成功!");
                }
            }
            return new RespResult("fail", "选课失败!");
        }
    }
    //查看学生成绩
    @RequestMapping("getStuScore")
    public String doShowTeacherViewQuestionsBypage(@RequestParam(value = "pageIndex",required =false,defaultValue = "1") Integer pageIndex,
                                                   @RequestParam(value = "teacCourseId",required =false,defaultValue ="0") Long teacCourseId,
                                                   Model model, HttpSession session) {

        Long stuId = (Long) session.getAttribute("id");
        if(teacCourseId == 0){
            teacCourseId = null;
        }
        //课程信息
        PageInfo pageInfo = courseService.getMyCoursesMessage(stuId,1,null,null, 0L);
        model.addAttribute("teacCourses", pageInfo.getList());
        //成绩信息
        PageInfo pageInfo2 = teacherService.searchStuScore(pageIndex,stuId,teacCourseId);
        model.addAttribute("pageInfo",pageInfo2);
        System.out.println(pageInfo2);
        return "student/myScore";

    }
    /**
     * 学生查看作业通知
     * @param teaccourseId
     * @param model
     * @return
     */
    @RequestMapping("getHomework")
    public String getHomework(@RequestParam("tcI") Long teaccourseId,
                              Model model){
        HomeWork homeWork = studentService.getHomeWorkByTcId(teaccourseId);
        model.addAttribute("homework",homeWork);
        System.out.println(homeWork);
        return "student/showHomework";
    }
}