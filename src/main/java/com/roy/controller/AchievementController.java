package com.roy.controller;

import com.github.pagehelper.PageInfo;
import com.roy.mapper.CourseMapper;
import com.roy.model.TeacCourse;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/achievementController")
public class AchievementController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseMapper courseDao;

    //根据考试标准id查看学生成绩统计表
    @RequestMapping("getAllstuScore")
    public String getAllstuScore(@RequestParam(value = "pageIndex",required =false,defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "teacCourseId",required =false,defaultValue ="0") Long standardId,
                                 Model model, HttpSession session) throws IOException {
        String courseName = "课程名为空";
        if(standardId == 0) {
            List<TeacCourse> teacCourses=teacherService.getCourseName((Long) session.getAttribute("id"));
            standardId = teacCourses.get(0).getId();
        }else{
            courseName = courseDao.getCourseByStandardId(standardId).getCourseName();
        }
        PageInfo pageInfo = teacherService.SearchAllNeedStuScoreByPageHelper(pageIndex, standardId);
        model.addAttribute("teacCourseId",standardId);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("courseName",courseName);
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
    public String showGradeOrder(@PathVariable("courseName") String courseName,
                                 @RequestParam("teacCourseId") Long standardId,Model model){
        Map<String,Integer> map=teacherService. getMapNameandScore(standardId);
        //排序
        List<Map.Entry<String, Integer>> infoIds =teacherService.gradeOrder(map);
        Integer maxGrade = 0;
        if(infoIds.size()>0) {
           maxGrade = infoIds.get(0).getValue();
        }
        model.addAttribute("infoIds",infoIds);
        model.addAttribute("maxGrade",maxGrade);
        model.addAttribute("courseName",courseName);
        model.addAttribute("teacCourseId",standardId);
        return "teacher/showGradeByStuScore";
    }


}
