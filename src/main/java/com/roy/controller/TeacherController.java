package com.roy.controller;


import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import com.roy.service.LoginService;
import com.roy.service.PaperService;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacherController")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private LoginService loginService;
    @Resource
    private PaperService paperService;

    /**
     * 跳转至教师首页
     * @return
     */
    @GetMapping("toTeacherIndex")
    public String toTeacherIndex(){
        return "teacher/teacherIndex";
    }
    /**
     * welcome
     * @return
     */
    @GetMapping("/toWelcome")
    public String toWelcome(){
        return"teacher/welcome";
    }
    /**
     * 显示个人资料
     * @param teacWorknum
     * @param model
     * @return
     */
    //显示老师的资料
    @RequestMapping(value = "showTeacherMessage/{teacWorknum}",method = RequestMethod.GET)
    public String showTeacherMessage(@PathVariable("teacWorknum")String teacWorknum,
                                        Model model){
        List teacher = loginService.selectByAccount(teacWorknum,2);
        if(teacher.size() > 0){
            model.addAttribute("teacher",teacher.get(0));
            return "teacher/showTeacherMessage";
        }
        return "teacher/teacherIndex";
    }

    /**
     * 跳转到修改老师密码页面
     * @param teacWorknum
     * @param model
     * @return
     */
    @RequestMapping(value = "toModifyMessage/{teacWorknum}",method = RequestMethod.GET)
    public String toModifyMessage(@PathVariable("teacWorknum")String teacWorknum,
                                     Model model){
        List teacher = loginService.selectByAccount(teacWorknum,2);
        if(teacher.size() > 0){
            model.addAttribute("teacher",teacher.get(0));
            return "teacher/updateTeacherPassword";
        }
        return "teacher/teacherIndex";
    }

    /**
     * 修改密码
     * @param teacher
     * @return
     */
    @RequestMapping("modifyTeacherPassword")
    @ResponseBody
    public RespResult doModifyMessage(Teacher teacher){
        System.out.println(teacher);
        Teacher teacher1 = (Teacher) loginService.selectByAccount(teacher.getTeacWorknum(),2).get(0);
        if(teacher.getOldPwd().equals(teacher1.getTeacPassword())){
            boolean result = teacherService.updateTeacherById(teacher);
            if(result){
                return new RespResult("success", "修改成功!");
            }else{
                return new RespResult("fail", "修改失败!");
            }
        }else{
            return new RespResult("fail", "原密码错误!");
        }
    }

    //去到发布作业的页面
    @RequestMapping("toPublicHomewWork")
    public String toPublicHomeWork(@RequestParam("tcid") Long teacCourseId,@RequestParam("cname")String courseName,Model model){
        model.addAttribute("teacCourseId",teacCourseId);
        model.addAttribute("courseName",courseName);
        return "course/publishHomeworkPage";
    }
    //发布作业
    @RequestMapping("publicHomeWork")
    public String doPublicHomeWork(HomeWork homeWork){
        Date date=new Date();
        homeWork.setDate(date);
        RespResult respResult = new RespResult();
        if(teacherService.insertIntoHomeWork(homeWork)){
            respResult.setMessage("添加失败，请稍后重试！");
        }
        return "redirect:/courseController/getCourseMessage?role=2";
    }


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



    //新增填空题
    @RequestMapping("addFill")
    public String doAddFill(Fill fill,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(fill);
        if(paperService.addFill(fill)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
    }

    //新增单选题
    @RequestMapping("addSel")
    public String doAddSelect(Select select,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(select);
        if(paperService.addSelect(select)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
    }
    //新增多选题
    @RequestMapping("addMulSel")
    public String doAddMulSel(MultiSelect multiSelect,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(multiSelect);
        if(paperService.addMulSel(multiSelect)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
    }
    //新增判断题
    @RequestMapping("addJudge")
    public String doAddJudge(Judge judge,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(judge);
        if(paperService.addJudge(judge)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
    }
    //新增计算题
    @RequestMapping("addCal")
    public String doAddCal(Calculate calculate,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(calculate);
        if(paperService.addCal(calculate)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
    }
    //新增主观题
    @RequestMapping("addSubject")
    public String doAddQuestion(Subject subject,HttpSession session){
        Long id = (Long) session.getAttribute("id");
        System.out.println(subject);
        if(paperService.addSubject(subject)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        return "redirect:/paperController/getMyQuestions?id="+id;
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
