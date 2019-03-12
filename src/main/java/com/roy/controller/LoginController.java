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
import com.roy.utils.UserUtils;
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
     * 403
     * @return
     */
    @GetMapping("/403")
    public String to403(){
        return"403";
    }
    /**
     * 404
     * @return
     */
    @GetMapping("/to404")
    public String to404(){
        return"404";
    }
    /**
     * 500
     * @return
     */
    @GetMapping("/to500")
    public String to500(){
        return"500";
    }
    /**
     * initLogin
     * @return
     */
    @GetMapping("/toInitLogin")
    public String toInitLogin(){
        return"initLogin";
    }
    /**
     * 跳转到系统主页面
     * @return
     */
    @GetMapping({"/", "/index", "/home","toIndex"})
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
    @GetMapping("/aboutUs")
    public String aboutUs(){
        return"about_us";
    }

    /**
     * 跳转到产品展示页面
     * @return
     */
    @GetMapping("/productShow")
    public String productShow(){
        return"show";
    }
    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/toRegist")
    public String toRegist(){
        return"regist";
    }
    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping("/loginController/tologin")
    private String toLogin(){
        return "login";
    }

    @GetMapping("/loginController/errorLogin")
    private String errorLogin(){
        return "errorLogin";
    }

    @RequestMapping("/loginController/dologin")
    private void doLogin(@RequestParam String account,@RequestParam String password){

    }
    /**
     * 验证登录
     *   role 角色：1 学生，2 老师，3 管理员
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/loginController/redirectPage")
    private String redirectPage(HttpSession session,Model model){
        Object object = UserUtils.getCurrentUser();
        System.out.println(object.getClass());
        Class clazz = object.getClass();
        if(clazz.equals(Student.class)){
            Student student = (Student) object;
            Long id = student.getId();
            String name = student.getStuName();
            model.addAttribute("student", student);
            session.setAttribute("name",student.getStuName());
            session.setAttribute("id",student.getId());
            session.setAttribute("num",student.getStuNum());
            session.setAttribute("role","student");
            //初始化课程信息
            PageInfo pageInfo = courseService.getMyCoursesMessage(id,1,null,null, 0L);
            System.out.println("查询课程："+pageInfo);
            model.addAttribute("courses", pageInfo.getList());
            System.out.println("课程："+pageInfo.getList());
            return "student/studentIndex";
        }else if (clazz.equals(Admin.class)) {
            Admin admin = (Admin) object;

            model.addAttribute("admin", admin);
            session.setAttribute("name",admin.getAdminName());
            session.setAttribute("id",admin.getId());
            session.setAttribute("num",admin.getAdminPhone());
            session.setAttribute("role","admin");
            return "admin/adminIndex";

        } else if (clazz.equals(Teacher.class)) {
            Teacher teacher = (Teacher) object;

            Long id = teacher.getId();
            String name = teacher.getTeacName();
            model.addAttribute("teacher", teacher);
            session.setAttribute("name",name);
            session.setAttribute("id",id);
            session.setAttribute("num",teacher.getTeacWorknum());
            session.setAttribute("role","teacher");
            //初始化课程信息
            PageInfo pageInfo = courseService.getMyCoursesMessage(id,2,null,name, 0L);
            System.out.println("查询课程："+pageInfo);
            System.out.println("课程："+pageInfo.getList());
            model.addAttribute("courses", pageInfo.getList());
            return "teacher/teacherIndex";
        }else {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }
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
        //用户id
        Long userId = 0L;

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
                    userId = admin.getId();
                }
            } else if (2 == actor) {
                Teacher teacher = (Teacher) object;
                if (teacher != null) {
                    question = teacher.getTeacQuestion();
                    flag = true;
                    key = teacher.getTeacKey();
                    userId = teacher.getId();
                }
            } else if (1 == actor) {
                Student student = (Student) object;
                if (student != null) {
                    question = student.getStuQuestion();
                    flag = true;
                    key = student.getStuKey();
                    userId = student.getId();
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
                // 初始化用户的密码：123456
                boolean isInitPassword = loginService.initPassword(actor,userId);
                if(isInitPassword) {
                    respResult.setCode("ok");
                }else {
                    respResult.setCode("fail");
                    respResult.setMessage("系统出错了！");
                }
            }else {
                respResult.setCode("fail");
                respResult.setMessage("密保回答错误！");
            }
        }
        System.out.println("找回密码："+flag+"--"+respResult);
        return respResult;
    }
    @GetMapping("/loginController/logout")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        loginService.logout(session,sessionStatus);
        //重定向跳转到系统首页
        return "redirect:/toIndex";
    }

}
