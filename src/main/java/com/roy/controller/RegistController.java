package com.roy.controller;
import com.roy.model.Student;
import com.roy.model.Teacher;
import com.roy.service.RegistService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/registController")
public class RegistController {
    @Resource
    private RegistService registService;

    /**
     * 学生注册
     * @return
     */
    @RequestMapping("/studentRegist")
    public String studentRegist(Student student){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(student.getStuPassword());
        student.setStuPassword(encode);
        boolean flag = registService.studentRegist(student);
        if(flag){//注册成功
            return "redirect:/toIndex";
        }else{//注册失败
            return "redirect:/500";
        }

    }

    /**
     * 老师注册
     * @return
     */
    @RequestMapping("/teacherRegist")
    public String teacherRegist(Teacher teacher){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(teacher.getTeacPassword());
        teacher.setTeacPassword(encode);
        boolean flag = registService.teacherRegist(teacher);
        if(flag){//注册成功
            return "redirect:/toIndex";
        }else{//注册失败
            return "redirect:/500";
        }
    }

}
