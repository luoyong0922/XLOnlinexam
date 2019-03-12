package com.roy.controller;

import com.github.pagehelper.PageInfo;
import com.roy.model.TeacCourse;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/achievementController")
public class AchievementController {
    @Resource
    private TeacherService teacherService;


    //根据老师课程id查看学生成绩统计表
    //getAllstuScore
    @RequestMapping("getAllstuScore")
    public String getAllstuScore(@RequestParam(value = "pageIndex",required =false,defaultValue = "1") Integer pageIndex,
                                                   @RequestParam(value = "teacCourseId",required =false,defaultValue ="0") Long teacCourseId,
                                                   Model model, HttpSession session) throws IOException {

        List<TeacCourse> teacCourses=teacherService.getCourseName((Long) session.getAttribute("id"));
        if(teacCourseId == 0) {
            teacCourseId = teacCourses.get(0).getId();
        }
        PageInfo pageInfo = teacherService.SearchAllNeedStuScoreByPageHelper(pageIndex, teacCourseId);
        model.addAttribute("teacCourseId",teacCourseId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("teacCourses",teacCourses);
        System.out.println(pageInfo);
        return "teacher/allstuScore";

    }
    //饼图，根据成绩表teacCourseId
    @RequestMapping("pieByTeacCourseId")
    public String toShowPieByTeacCourseId(Long teacCourseId,Model model){
        Map<String,Integer> map=teacherService.getPieData(teacCourseId);
        model.addAttribute("map",map);
        System.out.println(map);
        return "teacher/pie";
    }

    //查看排名
    @RequestMapping("showGradeOrder/{courseName}")
    public String showGrageOrder(@PathVariable("courseName") String courseName,
                                 @RequestParam("teacCourseId") Long teacCourseId,Model model){
        Map<String,Integer> map=teacherService. getMapNameandScore(teacCourseId);
        //排序后
        List<Map.Entry<String, Integer>> infoIds =teacherService.Order(map);
        model.addAttribute("infoIds",infoIds);
        model.addAttribute("courseName",courseName);
        return "teacher/showGradeByStuScore";
    }


}
