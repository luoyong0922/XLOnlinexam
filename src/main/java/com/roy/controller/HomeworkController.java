package com.roy.controller;

import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.StudentService;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/homeworkController")
public class HomeworkController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private StudentService studentService;
    @Resource
    private CourseService courseService;

    /**
     * 查看作业通知列表
     * @param teaccourseId
     * @param model
     * @return
     */
    @RequestMapping("getHomework")
    public String getHomework(@RequestParam("tcId") Long teaccourseId,
                              Model model){
        List<HomeWork> homeWorks = studentService.getHomeWorkByTcId(teaccourseId);
        Course course = courseService.getCourseByteacCourseId(teaccourseId);
        String courseName = "";
        if(course != null) {
            courseName = course.getCourseName();
        }
        PageInfo pageInfo = new PageInfo(homeWorks);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("courseName", courseName);
        model.addAttribute("teaccourseId", teaccourseId);
        return "course/showHomework";
    }

    //老师发布作业
    @RequestMapping("publicHomeWork")
    public String doPublicHomeWork(HomeWork homeWork){
        if(teacherService.insertIntoHomeWork(homeWork)){
            return "redirect:/courseController/getCourseMessage?role=2";
        }
        return "500";
    }

    /**
     * 查看作业通知详情
     * @param homeWorkId
     * @return
     */
    @RequestMapping("showHomework/{hwId}")
    @ResponseBody
    public RespBean showHomework(@PathVariable("hwId") Long homeWorkId ){
        HomeWork homeWork = studentService.getHomeWorkById(homeWorkId);
        System.out.println(homeWork);
        if(homeWork != null) {
            return RespBean.ok("success", homeWork);
        }
        return RespBean.error("没有找到相关内容！");
    }

}
