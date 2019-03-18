package com.roy.service.serviceImpl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roy.mapper.*;
import com.roy.model.*;
import com.roy.service.AdminService;
import com.roy.service.CourseService;
import com.roy.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminDao;
    @Resource
    private StudentMapper studentDao;
    @Resource
    private  TeacherMapper teacherDao;
    @Resource
    private PaperMapper paperDao;
    @Resource
    private CourseMapper courseDao;
    @Resource
    private TeacCourseMapper teacCourseDao;
    @Resource
    private CourseService courseService;
    @Resource
    private TeacherService teacherService;

    /**
     * 关键字查询老师
     * @param keywords
     * @return
     */
    @Override
    public PageInfo searchTeacherBykeywords(Integer pageIndex,String keywords,Integer state) {
        PageHelper.startPage(pageIndex,5);
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        if(state == null) {

        }else{
            criteria.andTeacStateEqualTo(state);
            String condition='%'+keywords+'%';
            criteria.andTeacNameLike(condition);
        }
        if(!"".equals(keywords)&& keywords != null && state == null){

            String condition='%'+keywords+'%';
            TeacherExample.Criteria criteria2=example.createCriteria();
            TeacherExample.Criteria criteria3=example.createCriteria();
            TeacherExample.Criteria criteria4=example.createCriteria();
            criteria.andTeacStateNotEqualTo(0);
            criteria.andTeacNameLike(condition);
            criteria2.andTeacWorknumLike(condition);
            criteria3.andTeacPhoneLike(condition);
            criteria4.andTeacGenderLike(condition);

            example.or(criteria2);
            example.or(criteria3);
            example.or(criteria4);
        }

        List<Teacher> teachers=teacherDao.selectByExample(example);
        PageInfo pageInfo=new PageInfo(teachers,5);
        return pageInfo;
    }

    /**
     * 所有老师的分页信息
     * @param pageIndex
     * @return
     */
    @Override
    public PageInfo searchTeacherByPageHelper(Integer pageIndex) {
        PageHelper.startPage(pageIndex,5);
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        criteria.andTeacStateNotEqualTo(0);
        List<Teacher> teachers=teacherDao.selectByExample(example);
        PageInfo pageInfo=new PageInfo(teachers,5);
        return pageInfo;
    }
    //根据工号判断老师是否存在
    @Override
    public boolean getTeacherByTeacWorknum(String teacWorknum) {
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        criteria.andTeacWorknumEqualTo(teacWorknum);
        criteria.andTeacStateNotEqualTo(0);
        Long result=teacherDao.countByExample(example);
        return result>0;
    }
    //添加老师
    @Override
    public boolean addTeacher(Teacher teacher) {
        int result=teacherDao.insertSelective(teacher);
        return result>0;
    }
    //删除老师
    @Override
    public boolean deleteTeacher(Long teacId) {
        Teacher teacher = new Teacher();
        teacher.setId(teacId);
        teacher.setTeacState(0);
        int result=teacherDao.updateByPrimaryKeySelective(teacher);
        return result>0;
    }
    //根据id获取老师
    @Override
    public Teacher getTeacherByTeacId(Long teacId) {
        Teacher teacher=teacherDao.selectByPrimaryKey(teacId);
        return teacher;
    }
    //更新老师
    @Override
    public boolean updateTeacher(Teacher teacher) {
        int result=teacherDao.updateByPrimaryKeySelective(teacher);
        return result>0;
    }
    //关键字查询学生
    @Override
    public PageInfo searchStudentBykeywords(Integer pageIndex, String keywords, Integer state) {
        PageHelper.startPage(pageIndex,5);
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        if(state == null) {
            criteria.andStuStateNotEqualTo(0);
        }else {
            criteria.andStuStateEqualTo(state);
            String condition='%'+keywords+'%';
            criteria.andStuNameLike(condition);
        }
        if(!"".equals(keywords)&& keywords != null && state == null){

            String condition='%'+keywords+'%';
            StudentExample.Criteria criteria6=example.createCriteria();
            StudentExample.Criteria criteria1=example.createCriteria();
            StudentExample.Criteria criteria2=example.createCriteria();
            StudentExample.Criteria criteria3=example.createCriteria();
            StudentExample.Criteria criteria4=example.createCriteria();
            StudentExample.Criteria criteria5=example.createCriteria();
            criteria.andStuAddressLike(condition);
            criteria1.andStuClassLike(condition);
            criteria2.andStuGenderLike(condition);
            criteria3.andStuNameLike(condition);
            criteria4.andStuNumLike(condition);
            criteria5.andStuPhoneLike(condition);
            example.or(criteria1);
            example.or(criteria2);
            example.or(criteria3);
            example.or(criteria4);
            example.or(criteria5);

        }

        List<Student> students=studentDao.selectByExample(example);
        PageInfo pageInfo=new PageInfo(students,5);
        return pageInfo;
    }


    //学生的分页查询信息
    @Override
    public PageInfo searchStudentByPageHelper(Integer pageIndex) {
        PageHelper.startPage(pageIndex,5);
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStuStateNotEqualTo(0);
        List<Student> students = studentDao.selectByExample(example);
        PageInfo pageInfo=new PageInfo(students,5);
        return pageInfo;
    }
    //根据id获得学生实体
    @Override
    public Student getStudentByStuId(Long stuId) {
        Student student= studentDao.selectByPrimaryKey(stuId);
        return student;
    }
    //更新学生
    @Override
    public boolean updateStudent(Student student) {
        int result=studentDao.updateByPrimaryKeySelective(student);
        return result>0;
    }
    //删除学生
    @Override
    public boolean deleteStudent(long stuId) {
        Student student = new Student();
        student.setId(stuId);
        student.setStuState(0);
        int result=studentDao.updateByPrimaryKeySelective(student);
        // int result=studentDao.deleteByPrimaryKey(stuId);
        return result>0;
    }
    //根据学号判断该学生是否存在
    @Override
    public boolean getStudentByStuNum(String stuNum) {
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStuNumEqualTo(stuNum);
        criteria.andStuStateNotEqualTo(0);
        Long result=studentDao.countByExample(example);
        return result>0;
    }
    //添加学生
    @Override
    public boolean addStudent(Student student) {
        int result= studentDao.insertSelective(student);
        return result>0;
    }

    /**
     * 修改admin信息
     * @param admin
     * @return
     */
    @Override
    public boolean updateAdmin(Admin admin) {
        if(admin.getAdminPassword() != null && admin.getAdminPassword().length() < 60){
            admin.setAdminPassword(new BCryptPasswordEncoder().encode(admin.getAdminPassword()));
        }
        int result = adminDao.updateByPrimaryKeySelective(admin);
        return result > 0;
    }

    //查询所有试卷
    @Override
    public List<Paper> getAllPapers(){
        List<Paper> papers=paperDao.selectByExample(null);
        return papers;
    }
    //根据试卷里的信息，去查询要显示的信息
    public List<Paper> getAllPapersWithNeedMeg(){
        List<Paper> allPapers=this.getAllPapers();
        List<Paper> neededPapers=new ArrayList<>();
        for(int i=0;i<allPapers.size();i++){
            Paper paper=allPapers.get(i);
            //根据stu_id去查询stu_name
            String stuName= getStudentByStuId(paper.getStuId()).getStuName();
            paper.setStuName(stuName);
            //根据standardId去查询老师姓名和课程名
            //根据standardId查询Course记录
            Course course = courseDao.getCourseByStandardId(paper.getTeacCourseId());
            String courseName= course.getCourseName();
            paper.setCourseName(courseName);
            Long tcID = courseDao.getTeacCourseIdByStandardId(paper.getTeacCourseId());
            TeacCourse teacCourse = teacCourseDao.selectByPrimaryKey(tcID);
            //教师id
            Long teacId=teacCourse.getTeacId();
            //根据teacId查询老师名字
            String teacName= teacherService.getTeacByTeacId(teacId).getTeacName();

            paper.setTeacName(teacName);
            neededPapers.add(paper);
        }
        return neededPapers;
    }
//把需要显示的试卷信息分页

    @Override
    public PageInfo searchAllPapersByPageHelper(Integer pageIndex, String courseName, String teacName){
        //  PageHelper.startPage(pageIndex,5);
        List<Paper> allPapers=this.getAllPapersWithNeedMeg();
        List<Paper> allPapers1=new ArrayList<>();
        PageHelper.startPage(pageIndex,5);
        if(("".equals(courseName) || courseName == null) && ("".equals(teacName) || teacName == null)){
            //courseName，questionType都为空
            allPapers1 = allPapers;

        }else {
            for (int i = 0; i < allPapers.size(); i++) {
                Paper paper = allPapers.get(i);
                //courseName，questionType都不为空
                if (!"".equals(courseName) && courseName != null && !"".equals(teacName) && teacName != null) {
                    if (paper.getCourseName().equals(courseName) && paper.getTeacName().equals(teacName)) {
                        allPapers1.add(paper);
                    }
                }
                //courseName不为空，questionType为空
                else if (!"".equals(courseName) && courseName != null && ("".equals(teacName) || teacName == null)) {
                    if (paper.getCourseName().equals(courseName)) {
                        allPapers1.add(paper);
                    }
                }
                //courseName为空，questionType不为空
                else if(("".equals(courseName) || courseName == null) &&(!"".equals(teacName) && teacName != null)){
                    if (paper.getTeacName().equals(teacName)) {
                        allPapers1.add(paper);
                    }
                }
            }
        }

        PageInfo pageInfo=new PageInfo(allPapers1);
        System.out.println(pageInfo);
        return pageInfo;
    }

    /**
     * 审核学生/老师注册信息
     * @param ids
     * @param op
     * @param i 角色： 1 学生， 2 老师
     * @return
     */
    @Override
    public boolean approvel(Long ids, Integer op, int i) {
        if(i == 1){
            StudentExample example = new StudentExample();
            StudentExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(ids);
            Student student = new Student();
            student.setId(ids);
            student.setStuState(op);
            return  studentDao.updateByPrimaryKeySelective(student)>0;
        }else{
            TeacherExample example = new TeacherExample();
            TeacherExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(ids);
            Teacher teacher = new Teacher();
            teacher.setId(ids);
            teacher.setTeacState(op);
            return  teacherDao.updateByPrimaryKeySelective(teacher)>0;
        }
    }

}
