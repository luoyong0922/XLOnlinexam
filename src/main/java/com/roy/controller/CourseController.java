package com.roy.controller;

import com.github.pagehelper.PageInfo;
import com.roy.model.Course;
import com.roy.model.TeacCourse;
import com.roy.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courseController")
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 跳转到添加课程界面
     * @return
     */
    @RequestMapping("toAddcourse")
    public String ToAddCourseage(Model model){
        model.addAttribute("teachers",courseService.getAllTeacher());
        return "course/addCourse";
    }

    /**
     * 添加课程
     * @param course
     * @return
     * @throws IOException
     */
    @RequestMapping("doAddCourse")
    public String doAddCourse(Course course, TeacCourse teacCourse){

        Long courseId = courseService.getCourseIdByNameAndCredit(course).getId();
        if(courseId != null){//存在该课程
            teacCourse.setCourseId(courseId);
        }else {
            if(courseService.addCourse(course)) {//新增课程记录
                List<Course> courses = courseService.getAllCourse();
                teacCourse.setCourseId((long)courses.get(courses.size() - 1).getId());
                System.out.println("课程添加成功！");
            }else {//新增课程失败，默认课程ID为0
                teacCourse.setCourseId(0L);
                System.out.println("课程添加失败！");
            }
        }
        courseService.addTeacCourse(teacCourse);//添加教师课程记录
        return "redirect:getCourseMessage?role=3";
    }
    /**
     * 删除教师课程记录
     * @param tcId 教师课程ID
     * @return
     */
    @RequestMapping("doDelTeacCourse/{tcId}")
    public String doDelTeacCourse(@PathVariable("tcId")Long tcId){
        List<Long> ids = new ArrayList<>();
        ids.add(tcId);
        int result = courseService.deleteTeacCourse(ids);
        if(result>0){
            System.out.println("共删除"+result+"门课程");
        }
        return "redirect:getCourseMessage?role=3";
    }

    /**
     * * 显示课程信息
     * @param  session 教师/学生 id
     * @param role  1：学生    2：教师    3：管理员
     * @param courseName
     * @param teacName
     * @param model
     * @param session
     * @return
     */

    //获取课程信息
    @RequestMapping("getCourseMessage")
    public String showCourseMessages(@RequestParam("role")int role,
                                     @RequestParam(value = "courseName",required = false,defaultValue = "") String courseName,
                                     @RequestParam(value = "teacName",required = false,defaultValue = "")String teacName,
                                     @RequestParam(value = "tcid",required = false,defaultValue = "0")Long tcid,
                                     @RequestParam(value = "opration",required = false,defaultValue = "0")Integer opration,
                                     Model model,
                                     HttpSession session){
        String view = "404";
        Long id = (Long) session.getAttribute("id");
        if(id == null){
            return "redirect:/loginController/tologin";
        }
        PageInfo pageInfo = courseService.getMyCoursesMessage(id,role,courseName,teacName,tcid);
        System.out.println("查询课程："+pageInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("courseName",courseName);
        model.addAttribute("teacName",teacName);
        model.addAttribute("opration",opration);
        if(role == 1) {//学生查询课程
            if(opration != 3) {
                view = "course/showStudentCourses";
            }else if (opration == 3){ // 查看讨论小组
                view = "chat/showGroups";
            }else {
                view = "404";
            }
        }else if(role == 2) {//教师查询课程
            if (opration == 3){ // 查看讨论小组
                view = "chat/showGroups";
            }else {
                view = "course/showTeacherCourses";
            }
        }else if(role == 3){//管理员查询课程
            view = "course/showCourses";
        }
        return view;
    }
}
