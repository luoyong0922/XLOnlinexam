package com.roy.controller;

import com.roy.model.*;
import com.roy.service.LoginService;
import com.roy.service.PaperService;
import com.roy.service.TeacherService;
import com.roy.utils.UserUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String toModifyMessage(@PathVariable("teacWorknum")String teacWorknum, Model model){
        List teacher = loginService.selectByAccount(teacWorknum,2);
        if(teacher.size() > 0){
            model.addAttribute("teacher",teacher.get(0));
            return "teacher/updateTeacherPassword";
        }
        return "404";
    }

    /**
     * 修改密码
     * @param teacher
     * @return
     */
    @RequestMapping("modifyTeacherMsg")
    @ResponseBody
    public RespResult doModifyMessage(Teacher teacher) {
        if (teacher != null) {
            boolean flag = false;
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (teacher.getOldPwd() != null) {
                Teacher teacher1 = (Teacher) UserUtils.getCurrentUser();
                flag = encoder.matches(teacher.getOldPwd(), teacher1.getTeacPassword());
            }
            if (flag || teacher.getTeacPassword().length() == 60) {
                boolean result = teacherService.updateTeacherById(teacher);
                if (result) {
                    return new RespResult("success", "修改成功!");
                } else {
                    return new RespResult("fail", "修改失败!");
                }
            } else {
                return new RespResult("fail", "原密码错误!");
            }
        } else {
            return new RespResult("fail", "请先登录!");
        }
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

}
