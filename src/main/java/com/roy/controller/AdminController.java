package com.roy.controller;


import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import com.roy.service.AdminService;
import com.roy.service.CourseService;
import com.roy.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/adminController")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private CourseService courseService;
    @Resource
    private LoginService loginService;

    //显示个人资料
    @RequestMapping(value = "showAdminMsg/{adminPhone}",method = RequestMethod.GET)
    public String showAdminMessage(@PathVariable("adminPhone")String phone,
                                     Model model){
        List<Admin> admins = loginService.selectByAccount(phone,3);
        if(admins.size() > 0){
            model.addAttribute("admin",admins.get(0));
            return "admin/showAdminMessage";
        }
        return "404";
    }
    //返回到管理员主页
    @RequestMapping("toAdminIndex")
    public String toAdminIndex(){
        return "admin/adminIndex";
    }
    /**
     * welcome
     * @return
     */
    @RequestMapping("/toWelcome")
    public String toWelcome(){
        return"admin/welcome";
    }

    //去更新管理员密码界面
    @RequestMapping(value = "toModifyAdminMsg/{phone}",method = RequestMethod.GET)
    public String toUpdateAdminPassword(@PathVariable("phone")String phone, Model model){
        List admins = loginService.selectByAccount(phone,3);
        if(admins.size() > 0){
            model.addAttribute("admin",admins.get(0));
            return "admin/updateAdminPassword";
        }
        return "404";
    }
    // 保存密码的修改
    @RequestMapping("updateAdminMsg")
    @ResponseBody
    public RespResult doModifyMessage(Admin admin){
        List admins = loginService.selectByAccount(admin.getAdminPhone(),3);
        if(admins.size() > 0) {
            Admin admin1 = (Admin) admins.get(0);
            if (admin.getOldPwd().equals(admin1.getAdminPassword())) {
                boolean result = adminService.updateAdmin(admin);
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


    /**
     * 跳转至教师课程修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toModifyTeacCourse/{tcid}")
    public String toModifyTeacCourse(Model model, @PathVariable("tcid") Long id){
        model.addAttribute("teaccourse",courseService.getTeacCourse(id));
        model.addAttribute("courses",courseService.getAllCourse());
        model.addAttribute("teachers",courseService.getAllTeacher());
        return "course/modifyTeacCourse";
    }

    /**
     * 修改教师课程信息
     * @param teacCourse
     * @return
     */
    @RequestMapping("doModifyTeacCourse")
    public String doModifyTeacCourse(TeacCourse teacCourse){
        boolean flag = courseService.modifyTeacCourse(teacCourse);
        if(flag) {
            return "redirect:/courseController/getCourseMessage?role=3";
        }else {
            return "500";
        }
    }

    /**
     * 分页查看老师信息
     * @param pageIndex
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("getAllTeachersByPage")
    public String doShowTeachersBypage(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "keywords",required =false,defaultValue ="") String keywords,
                                       Model model){

            PageInfo pageInfo =adminService.searchTeacherBykeywords(pageIndex,keywords,null);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("keywords",keywords);
            return "admin/showTeachers";
    }
    /**
     * 分页查看待审核老师信息
     * @param pageIndex
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("toApprovalTeachers")
    public String toApprovalTeachers(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "keywords",required =false,defaultValue ="") String keywords,
                                       Model model){

            PageInfo pageInfo = adminService.searchTeacherBykeywords(pageIndex,keywords,2);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("keywords", keywords);
            return "admin/approvalTeachers";

    }
    /**
     * 分页查看待审核学生信息
     * @param pageIndex
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("toApprovalStudents")
    public String toApprovalStudents(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                     @RequestParam(value = "keywords",required =false,defaultValue ="") String keywords,
                                     Model model){

        PageInfo pageInfo = adminService.searchStudentBykeywords(pageIndex,keywords,2);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keywords", keywords);
        return "admin/approvalStudents";

    }

    /**
     * 批量审核老师信息
     * @param ids
     * @param op
     * @return
     */
    @RequestMapping("doApprovalTeachers/{ids}/{op}")
    public String doApprovalTeachers(@PathVariable("ids") Long ids,
                                     @PathVariable("op") Integer op){

        System.out.println(ids+"---"+op);
        if(adminService.approvel(ids,op,2)) {//审核老师信息
            return "redirect:/adminController/toApprovalTeachers";
        }else {
            return "500";
        }
    }
    /**
     * 批量审核学生信息
     * @param ids
     * @param op
     * @return
     */
    @RequestMapping("doApprovalStudents/{ids}/{op}")
    public String doApprovalStudents(@PathVariable("ids") Long ids,
                                     @PathVariable("op") Integer op){

        if(adminService.approvel(ids,op,1)) {//审核学生信息
            return "redirect:/adminController/toApprovalStudents";
        }else {
            return "500";
        }
    }
    /**
     * 去老师的添加页面
     * @return
     */
    @RequestMapping("addteacher")
    public String ToAddTeacherPage(){
        return "admin/addteacher";
    }

    //添加老师
    @RequestMapping(value = "saveAddteac",method = RequestMethod.POST)
    public String doAddTeac(Teacher teacher){
        //System.out.println(user);
        if((!adminService.getTeacherByTeacWorknum(teacher.getTeacWorknum()))&&adminService.addTeacher(teacher)){
            return "redirect:getAllTeachersByPage";
        }
        return "redirect:saveAddteac";
    }

    //删除老师
    @RequestMapping("deleteTeacher")
    public String deleteTeacher(@RequestParam("id")Long id){
        if(adminService.deleteTeacher(id)){
            return "redirect:getAllTeachersByPage";
        }
        return "500";
    }

    //去老师的修改页面
    @RequestMapping("teacherUpdate/{teacId}")
    public ModelAndView ToTeacUpdatePage(@PathVariable("teacId") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("teacher",adminService.getTeacherByTeacId(id));
        System.out.println(adminService.getTeacherByTeacId(id));
        mav.setViewName("admin/modifyteacher");
        return mav;
    }

    //保存老师的修改
    @RequestMapping("saveTeacUpdate")
    public String doUpdateTeacher(Teacher teacher){
        if(adminService.updateTeacher(teacher)){
            return "redirect:getAllTeachersByPage";
        }
        return "500";
    }

    //老师的工号的验证
    @RequestMapping("teacajax")
    @ResponseBody
    public String teacAjax(@RequestParam("teacWorknum") String teacWorknum){
        String msg="Repeat";
        if(adminService.getTeacherByTeacWorknum(teacWorknum)){
            System.out.println("工号重复");

        }else {
            System.out.println("工号没有重复");
            msg ="Able";
        }
        return msg;
    }

    //学生分页
    @RequestMapping("getAllStudentsByPage")
    public String doShowStudentsBypage(@RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                       @RequestParam(value = "keywords",required =false,defaultValue ="") String keywords,
                                       Model model){

            PageInfo pageInfo=adminService.searchStudentBykeywords(pageIndex,keywords,null);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("keywords",keywords);
            return "admin/showStudents";
    }

    //去学生修改页面
    @RequestMapping("studentUpdate/{stuId}")
    public ModelAndView ToStuUpdatePage(@PathVariable("stuId") Long id){
        ModelAndView mav=new ModelAndView();
        mav.addObject("student",adminService.getStudentByStuId(id));
        mav.setViewName("admin/modifystudent");
        return mav;
    }
    //保存学生修改
    @RequestMapping("saveUpdate")
    public String doUpdateStudent(Student student){
        if(adminService.updateStudent(student)){
            return "redirect:getAllStudentsByPage";
        }
        return "500";
    }
    //删除学生
    @RequestMapping("deleteStudent")
    public String deleteStudent(@RequestParam("id")Long id){
        if(adminService.deleteStudent(id)){
            return "redirect:getAllStudentsByPage";
        }
        return "500";
    }
    //去学生添加页面
    @RequestMapping("addstudent")
    public String toAddStudentPage(){
        return "admin/addstudent";
    }

    //添加学生
    @RequestMapping("saveAdd")
    public String doAddStudent(Student student){
        //System.out.println(user);
        if((!adminService.getStudentByStuNum(student.getStuNum()))&&adminService.addStudent(student)){
            return "redirect:getAllStudentsByPage";
        }
        return "500";
    }

    //验证学号是否重复
    @RequestMapping("ajax")
    @ResponseBody
    public String stuAjax(@RequestParam("stuNum") String stuNum){
        String msg="Repeat";
        if(adminService.getStudentByStuNum(stuNum)){
            System.out.println("学号重复");
        }else {
            System.out.println("学号没有重复");
            msg = "Able";
        }
        return msg;
    }

    //管理员查看所有试卷
    @RequestMapping("adminSeeAllPapersBypage")
    public String doShowAllPapersBypage(@RequestParam(value = "pageIndex",required =false,defaultValue = "1") Integer pageIndex,
                                        @RequestParam(value = "courseName",required =false,defaultValue ="") String courseName,
                                        @RequestParam(value = "teacName",required =false,defaultValue ="") String teacName,
                                        Model model) throws IOException {
        PageInfo pageInfo=adminService.searchAllPapersByPageHelper(pageIndex,courseName,teacName);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("courseName",courseName);
        model.addAttribute("teacName",teacName);
        System.out.println(pageInfo);
        return "admin/allPapersBypage";

    }

}
