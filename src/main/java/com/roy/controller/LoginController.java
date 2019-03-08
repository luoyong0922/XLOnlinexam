package com.roy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.roy.model.Admin;
import com.roy.model.RespResult;
import com.roy.model.Student;
import com.roy.model.Teacher;
import com.roy.service.CourseService;
import com.roy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Resource
    private CourseService courseService;
    /**
     * 404
     * @return
     */
    @RequestMapping("/to404")
    public String to404(){
        return"404";
    }
    /**
     * 500
     * @return
     */
    @RequestMapping("/to500")
    public String to500(){
        return"500";
    }
    /**
     * 跳转到系统主页面
     * @return
     */
    @RequestMapping("/toIndex")
    public String toIndex(){

        System.out.println("((    ))  ()        ");
        System.out.println(" ((  ))	  ()        ");
        System.out.println("  (())    ()        ");
        System.out.println(" ((  ))   ()        ");
        System.out.println("((    ))  ()()()()()");

        return"index";
    }

    /**
     * 跳转到关于我们页面
     * @return
     */
    @RequestMapping("/aboutUs")
    public String aboutUs(){
        return"about_us";
    }

    /**
     * 跳转到产品展示页面
     * @return
     */
    @RequestMapping("/productShow")
    public String productShow(){
        return"show";
    }
    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/toRegist")
    public String toRegist(){
        return"regist";
    }
    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/loginController/tologin")
    private String toLogin(){
        return "login";
    }

    /**
     * 验证登录
     * @param account
     * @param password
     * @param role 角色：1 学生，2 老师，3 管理员
     * @param validate  验证码
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/loginController/dologin")
    private String doLogin(@RequestParam String account,
                           @RequestParam String password,
                           @RequestParam int role,
                           @RequestParam String validate,
                           HttpSession session,Model model){
        String key = (String) session.getAttribute("strRandom");
        System.out.println(key);
        String msg = "账号或密码错误";
        if(validate != null && (key.toUpperCase()).equals(validate.toUpperCase())){
            if (1 == role) {
                List<Student> student = loginService.StudenLogin(account, password);
                if (student.size() > 0) {
                    Long id = student.get(0).getId();
                    String name = student.get(0).getStuName();
                    model.addAttribute("student", student);
                    session.setAttribute("name",student.get(0).getStuName());
                    session.setAttribute("id",student.get(0).getId());
                    session.setAttribute("num",student.get(0).getStuNum());
                    session.setAttribute("role","student");
                    //初始化课程信息
                    PageInfo pageInfo = courseService.getMyCoursesMessage(id,role,null,null, 0L);
                    System.out.println("查询课程："+pageInfo);
                    System.out.println("课程："+pageInfo.getList());
                    model.addAttribute("courses", pageInfo.getList());
                    return "student/studentIndex";
                }else {
                    model.addAttribute("msg", msg);
                }
            } else if (3 == role) {
                List<Admin> admin = loginService.AdminLogin(account, password);
                if (admin.size() > 0) {
                    model.addAttribute("admin", admin);
                    session.setAttribute("name",admin.get(0).getAdminName());
                    session.setAttribute("id",admin.get(0).getId());
                    session.setAttribute("num",admin.get(0).getAdminPhone());
                    session.setAttribute("role","admin");
                    return "admin/adminIndex";
                }else {
                    model.addAttribute("msg", msg);
                }
            } else if (2 == role) {
                List<Teacher> teacher = loginService.TeacherLogin(account, password);
                if (teacher.size() > 0) {
                    Long id = teacher.get(0).getId();
                    String name = teacher.get(0).getTeacName();
                    model.addAttribute("teacher", teacher);
                    session.setAttribute("name",name);
                    session.setAttribute("id",id);
                    session.setAttribute("num",teacher.get(0).getTeacWorknum());
                    session.setAttribute("role","teacher");
                    //初始化课程信息
                    PageInfo pageInfo = courseService.getMyCoursesMessage(id,role,null,name, 0L);
                    System.out.println("查询课程："+pageInfo);
                    System.out.println("课程："+pageInfo.getList());
                    model.addAttribute("courses", pageInfo.getList());
                    return "teacher/teacherIndex";
                }else {
                    model.addAttribute("msg", msg);
                }
            }
        }else {
            model.addAttribute("msg", "验证码不正确");
        }
        return "login";
    }

    /**
     * 跳转到找回密码页面
     * @return
     */
    @RequestMapping("/loginController/toFindPassword")
    public String FindPasswordPage(){
        return"findpassword";
    }

    /**
     * 找回密码
     * @param data JsonObject
     *  account 账号
     *  role  角色： 1 学生，2 老师， 3 管理员
     *  opration 操作：0 查询密保问题，1 比对密保回答
     *   answer 回答
     * @return
     */
    @RequestMapping(value = "/loginController/doFindPassword",
            method = RequestMethod.POST,produces="application/json;charset=utf-8;")
    @ResponseBody
    public RespResult doFindpassword(@RequestBody String data){
        String question = "";
        String key = "";
        String password = "";
        //解析json数据
        JSONObject json = JSON.parseObject(data);
        //账号
        String account = json.getString("account");
        //密保回答
        String answer = json.getString("answer");
        //操作：0 查询密保问题，1 比对密保回答
        Integer opration = json.getInteger("opration");
        //角色： 1 学生，2 老师， 3 管理员
        Integer actor = json.getInteger("role");

        boolean flag = false;
        RespResult respResult = new RespResult();
        //根据账号，角色查询对应信息
        List objects = loginService.selectByAccount(account,actor);
        if(objects.size() > 0) {//存在该角色
            Object object = objects.get(0);
            if (3 == actor) {
                Admin admin = (Admin) object;
                if (admin != null) {
                    question = admin.getAdminQuestion();
                    flag = true;
                    key = admin.getAdminKey();
                    password = admin.getAdminPassword();
                }
            } else if (2 == actor) {
                Teacher teacher = (Teacher) object;
                if (teacher != null) {
                    question = teacher.getTeacQuestion();
                    flag = true;
                    key = teacher.getTeacKey();
                    password = teacher.getTeacPassword();
                }
            } else if (1 == actor) {
                Student student = (Student) object;
                if (student != null) {
                    question = student.getStuQuestion();
                    flag = true;
                    key = student.getStuKey();
                    password = student.getStuPassword();
                }
            }else {
                question = "系统出错了！";
            }
        }else {
                question = "该账号不存在！";
        }
        if(flag){
            respResult.setCode("success");
        }else {
            respResult.setCode("fail");
        }
        //操作：0 返回密保问题，1 比对密保回答，如果正确返回密码
        if(opration == 0){//返回密保问题
            respResult.setMessage(question);
        }else if(opration == 1 && answer != null && !"".equals(answer)){
            if(answer.equals(key)){
                respResult.setCode("ok");
                respResult.setMessage(password);
            }else {
                respResult.setCode("fail");
                respResult.setMessage("密保回答错误！");
            }
        }
        System.out.println("找回密码："+flag+"--"+respResult);
        return respResult;
    }
    @RequestMapping("/loginController/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        loginService.logout(session,sessionStatus);
        //重定向跳转到系统首页
        return "redirect:/toIndex";
    }
    //去画密码的界面
    @RequestMapping("picturePwd")
    public String toPicturePwd(@RequestParam String account, @RequestParam int role,Model model){
        model.addAttribute("account",account);
        model.addAttribute("role",role);
        return "picturePwd";
    }
//九宫格登录
    @RequestMapping("/loginController/doPicPwdLogin")
    private String doPwdLogin(@RequestParam String account,
                           @RequestParam String pwd,
                           @RequestParam int role,
                            HttpSession session, Model model){
        String msg = "账号或密码错误";
            if (1 == role) {
                List<Student> student = loginService.StudenLogin(account, pwd);
                if (student.size() > 0) {
                    Long id = student.get(0).getId();
                    String name = student.get(0).getStuName();
                    model.addAttribute("student", student);
                    session.setAttribute("name",student.get(0).getStuName());
                    session.setAttribute("id",student.get(0).getId());
                    session.setAttribute("num",student.get(0).getStuNum());
                    session.setAttribute("role","student");
                    PageInfo pageInfo = courseService.getMyCoursesMessage(id,role,null,null, 0L);
                    System.out.println("查询课程："+pageInfo);
                    System.out.println("课程："+pageInfo.getList());
                    model.addAttribute("courses", pageInfo.getList());
                    return "student/studentIndex";
                }
            } else if (3 == role) {
                List<Admin> admin = loginService.AdminLogin(account, pwd);
                if (admin.size() > 0) {
                    model.addAttribute("admin", admin);
                    session.setAttribute("name",admin.get(0).getAdminName());
                    session.setAttribute("id",admin.get(0).getId());
                    session.setAttribute("num",admin.get(0).getAdminPhone());
                    session.setAttribute("role","admin");
                    return "admin/adminIndex";
                }
            } else if (2 == role) {
                List<Teacher> teacher = loginService.TeacherLogin(account, pwd);
                if (teacher.size() > 0) {
                    Long id = teacher.get(0).getId();
                    String name = teacher.get(0).getTeacName();
                    model.addAttribute("teacher", teacher);
                    session.setAttribute("name",name);
                    session.setAttribute("id",id);
                    session.setAttribute("num",teacher.get(0).getTeacWorknum());
                    session.setAttribute("role","teacher");
                    PageInfo pageInfo = courseService.getMyCoursesMessage(id,role,null,name, 0L);
                    System.out.println("查询课程："+pageInfo);
                    System.out.println("课程："+pageInfo.getList());
                    model.addAttribute("courses", pageInfo.getList());
                    return "teacher/teacherIndex";
                }
            }

            model.addAttribute("account",account);
            model.addAttribute("role",role);
            return "redirect:/picturePwd";
    }

}
