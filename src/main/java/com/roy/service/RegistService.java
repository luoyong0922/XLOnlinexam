package com.roy.service;

import com.roy.model.Student;
import com.roy.model.Teacher;
import org.springframework.stereotype.Repository;

//@Repository
public interface RegistService {
    boolean studentRegist(Student student);

    boolean teacherRegist(Teacher teacher);
}
