package com.roy.service.serviceImpl;

import com.roy.mapper.StudentMapper;
import com.roy.mapper.TeacherMapper;
import com.roy.model.Student;
import com.roy.model.Teacher;
import com.roy.service.RegistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("registService")
public class RegistServiceImpl implements RegistService {
    @Resource
    private StudentMapper studentDao;
    @Resource
    private TeacherMapper teacherDao;

    @Override
    public boolean studentRegist(Student student) {
        student.setStuState(2);
        int result= studentDao.insertSelective(student);
        return result>0;
    }

    @Override
    public boolean teacherRegist(Teacher teacher) {
        teacher.setTeacState(2);
        int result=teacherDao.insertSelective(teacher);
        return result>0;
    }
}
