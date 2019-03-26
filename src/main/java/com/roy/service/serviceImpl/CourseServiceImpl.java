package com.roy.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.roy.mapper.*;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseDao;
    @Resource
    private TeacherMapper teacherDao;
    @Resource
    private StudentMapper studentDao;
    @Resource
    private TeacCourseMapper teacCourseDao;
    @Resource
    private StuCourseMapper stuCourseDao;
    @Resource
    private TeacherService teacherService;

    /**
     * 查询课程表所有课程
     */
    @Override
    public List<Course> getAllCourse(){
        List<Course> courses = courseDao.selectByExample(null);
        return courses;
    }

    /**
     * 根据id查询教师课程表的课程信息
     * @param id 教师课程表id
     * @return
     */
    @Override
    public TeacCourse getTeacCourse(Long id){
        TeacCourse teaccourse = teacCourseDao.selectByPrimaryKey(id);
        return teaccourse;
    }

    /**
     * 根据课程id查询课程信息
     * @param id
     * @return
     */
    @Override
    public Course getCourseById(Long id) {
        Course course = courseDao.selectByPrimaryKey(id);
        return course;
    }

    /**
     * 根据教师课程id查询课程信息
     * @param tcId
     * @return
     */
    @Override
    public Course getCourseByteacCourseId(Long tcId) {
        Course course = courseDao.getCourseByteacCourseId(tcId);
        return course;
    }
    /**
     * 根据课程名称和学分查询课程信息
     * @param course
     * @return
     */
    @Override
    public Course getCourseIdByNameAndCredit(Course course) {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseNameEqualTo(course.getCourseName());
        criteria.andCourseCreditEqualTo(course.getCourseCredit());
        Course result = new Course();
        List<Course> courses = courseDao.selectByExample(example);
        if(courses.size()>0) {
            result = courses.get(0);
        }
        return result;
    }
    /**
     * 查询教师表所有老师
     * @return
     */
    @Override
    public List<Teacher> getAllTeacher(){
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        criteria.andTeacStateNotEqualTo(0);
        List<Teacher> teachers=teacherDao.selectByExample(example);
        return teachers;
    }

    /**
     * 查询学生表所有学生
     */
    @Override
    public List<Student> getAllStudent(){
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStuStateNotEqualTo(0);
        List<Student> students = studentDao.selectByExample(example);
        return students;
    }

    /**
     * 新增课程信息
     * @param course 课程对象
     * @return
     */
    @Override
    public boolean addCourse(Course course) {
        CourseExample example= new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseCreditEqualTo(course.getCourseCredit());
        criteria.andCourseNameEqualTo(course.getCourseName());
        boolean flag = courseDao.selectByExample(example).size() > 0;
        int result = 0;
        if(flag){//课程已存在
            result = 1;
        }else {//课程不存在
           // course.setCourseNum("c"+getAllCourse().size());
            result = courseDao.insertSelective(course);
        }

        return result>0;
    }
    /**
     * 新增教师课程信息
     * @param teacCourse 教师课程对象
     * @return
     */
    @Override
    public boolean addTeacCourse(TeacCourse teacCourse) {
        int result = teacCourseDao.insertSelective(teacCourse);
        return result>0;
    }
    /**
     * 新增学生课程信息
     * @param stuCourse 学生课程对象
     * @return
     */
    @Override
    public boolean addStuCourse(StuCourse stuCourse) {
        int result = stuCourseDao.insertSelective(stuCourse);
        return result>0;
    }

    /**
     * 修改课程信息
     * @param course 课程对象
     * @return
     */

    @Override
    public boolean modifyCourse(Course course) {
        int result = courseDao.updateByPrimaryKeySelective(course);
        return result>0;
    }
    /**
     * 修改教师课程信息
     * @param teaccourse 教师课程对象
     * @return
     */

    @Override
    public boolean modifyTeacCourse(TeacCourse teaccourse) {
        int result = teacCourseDao.updateByPrimaryKeySelective(teaccourse);
        return result>0;
    }
    /**
     * 批量删除课程
     * @param ids 课程ID集合
     * @return result  删除记录数量
     */
    @Override
    public int deleteCourse(List<Long> ids) {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        int result = courseDao.deleteByExample(example);
        return result;
    }

    //查询所有的teaccourse
    @Override
    public List<TeacCourse> getAllTeacCourses() {
        List<TeacCourse> teacCourses = teacCourseDao.selectByExample(null);
        System.out.println("课程总数量"+teacCourses.size());
        System.out.println("课程"+teacCourses);
        return teacCourses;
    }


    //查询所有课程信息
    private List<TeacCourse> getAllCoursesMessage(){

        List<TeacCourse> teacCourses=this.getAllTeacCourses();
        List<TeacCourse> teacCourse2=new ArrayList<>();
        TeacCourse teacCourse;
        System.out.println(teacCourses.size());
        for(int i=0;i<teacCourses.size();i++){
            teacCourse = teacCourses.get(i);
            teacCourse = improveCourseMsg(teacCourse);
            //通过课程id查询课程名字，和课程学分,课程编号
            Long courseId = teacCourse.getCourseId();
            Map map1 = this.getCourseNCNum(courseId);
            String courseName = (String) map1.get("courseName");
            BigDecimal courseCredit = (BigDecimal) map1.get("courseCredit");
            String courseNum = (String) map1.get("courseNum");
            teacCourse.setCourseName(courseName);
            teacCourse.setCourseCredit(courseCredit);
            teacCourse.setCourseNum(courseNum);

            //通过老师id查询老师名字
            String teacName = teacherService.getTeacByTeacId(teacCourse.getTeacId()).getTeacName();
            teacCourse.setTeacName(teacName);

            //通过教师课程id查询学生课程信息
            Map map = this.getStuCourseMessage(teacCourse.getId());
            String courseType = (String) map.get("courseType");
            Long stuId = (Long) map.get("stuId");
            teacCourse.setCourseType(courseType);
            teacCourse.setStuId(stuId);
            teacCourse2.add(teacCourse);
        }

        return teacCourse2;
    }

    /**
     * 根据教师课程ID查询
     * 课程类型：必修，选修  courseType
     * 学生ID   stuId
     * @param teaccourseId
     * @return
     */
    private Map getStuCourseMessage(Long teaccourseId){
        Map map = new HashMap();
        StuCourseExample example = new StuCourseExample();
        StuCourseExample.Criteria criteria = example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseId);

        String courseType = "";
        Long stuId = 0L;
        if(stuCourseDao.selectByExample(example).size()>0) {
            courseType = stuCourseDao.selectByExample(example).get(0).getCourseType();
            stuId = stuCourseDao.selectByExample(example).get(0).getStuId();
        }
        map.put("courseType",courseType);
        map.put("stuId",stuId);
        return map;
    }
    /**
     * 根据ID查询课程信息
     * @param id 教师/学生id
     * @param role 1 学生， 2 教师， 3 管理员
     * @param tcid
     * @return PageInfo  课程集合
     */
    @Override
    public PageInfo getMyCoursesMessage(Long id, int role, String courseName, String teacName, Long tcid){
        List<TeacCourse> teacCourse1= new ArrayList<>();
        if(role == 1){//学生课程信息
            if(id != null) {
                teacCourse1 = getStuCoursesMessage(id,tcid);
            }
        }
        if(role == 2){//老师课程信息
            if(id != null) {
                teacCourse1 = getTeacCoursesMessage(id,tcid);
            }
        }
        if(role == 3){//管理员查询全部课程
            teacCourse1 = this.getAllCoursesMessage();
        }

        List<TeacCourse> teacCourses = new ArrayList<>();
        if(("".equals(courseName) || courseName == null) && ("".equals(teacName) || teacName == null)){
            //courseName，teacName都为空
            teacCourses = teacCourse1;
        }else {
            for (int i = 0; i < teacCourse1.size(); i++) {
                TeacCourse course = teacCourse1.get(i);
                //courseName，teacName都不为空
                if (!"".equals(courseName) && courseName != null && !"".equals(teacName) && teacName != null) {
                    if (course.getCourseName().equals(courseName) && course.getTeacName().equals(teacName)) {
                        teacCourses.add(course);
                    }
                }
                //courseName不为空，teacName为空
                else if (!"".equals(courseName) && courseName != null && ("".equals(teacName) || teacName == null)) {
                    if (course.getCourseName().equals(courseName)) {
                        teacCourses.add(course);
                    }
                }
                //courseName为空，teacName不为空
                else if (("".equals(courseName) || courseName == null) && (!"".equals(teacName) && teacName != null)) {
                    if (course.getTeacName().equals(teacName)) {
                        teacCourses.add(course);
                    }
                }
            }
        }
        PageInfo pageInfo=new PageInfo(teacCourses);
        System.out.println("-------"+pageInfo);
        return pageInfo;
    }

    /**
     *
     * 根据课程ID去查询课程名称，课程学分，课程编号，
     * @param courseId
     * @return
     */
    private Map getCourseNCNum(Long courseId){
        Map map = new HashMap();
        CourseExample example=new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(courseId);
        String courseName = courseDao.selectByExample(example).get(0).getCourseName();
        BigDecimal courseCredit = courseDao.selectByExample(example).get(0).getCourseCredit();
        String courseNum = courseDao.selectByExample(example).get(0).getCourseNum();
        map.put("courseName",courseName);
        map.put("courseCredit",courseCredit);
        map.put("courseNum",courseNum);
        return map;
    }


    /**
     * 根据学生id查询课程信息
     * @param stuId
     * @return
     */
    @Override
    public List<TeacCourse> getStuCoursesMessage(Long stuId,Long tcid){
        StuCourseExample example = new StuCourseExample();
        StuCourseExample.Criteria criteria = example.createCriteria();
        criteria.andStuIdEqualTo(stuId);
        if(tcid != 0L){
            criteria.andTeacCourseIdEqualTo(tcid);
        }
        //根据学生id筛选出学生的课程
        List<StuCourse> stuCourses = stuCourseDao.selectByExample(example);
        List<TeacCourse> courseList = new ArrayList<>();
        //完善课程信息
        for(StuCourse course : stuCourses){
            TeacCourse teacCourse = teacCourseDao.selectByPrimaryKey(course.getTeacCourseId());
            teacCourse = improveCourseMsg(teacCourse);
            String courseType= course.getCourseType();
            teacCourse.setCourseType(courseType);
            teacCourse.setStuId(stuId);
            courseList.add(teacCourse);
        }
        System.out.println("学生课程信息："+courseList);
        return courseList;
    }
    /**
     * 根据老师id查询课程信息
     * @param teacId
     * @return
     */
    @Override
    public List<TeacCourse> getTeacCoursesMessage(Long teacId,Long tcid){
        TeacCourseExample example = new TeacCourseExample();
        TeacCourseExample.Criteria criteria = example.createCriteria();
        criteria.andTeacIdEqualTo(teacId);
        if(tcid != 0L){
            criteria.andIdEqualTo(tcid);
        }
        //根据老师id筛选出老师的课程
        List<TeacCourse> teacCourses = teacCourseDao.selectByExample(example);
        List<TeacCourse> courseList = new ArrayList<>();
        //完善课程信息
        for(TeacCourse course : teacCourses){
            course = improveCourseMsg(course);
            //通过教师课程id查询学生课程信息
            Map map = this.getStuCourseMessage(course.getId());
            String courseType = (String) map.get("courseType");
            Long stuId = (Long) map.get("stuId");
            course.setCourseType(courseType);
            course.setStuId(stuId);
            courseList.add(course);
        }
        System.out.println("教师课程信息："+courseList);
        return courseList;
    }
    /**
     * 根据课程ID完善课程信息：课程名称，课程学分，授课老师
     * @param teacCourse
     * @return
     */
    @Override
    public TeacCourse improveCourseMsg(TeacCourse teacCourse){
        //通过课程id查询课程名字，和课程学分
        String courseName = this.getCourseNameByCourseId(teacCourse.getCourseId());
        BigDecimal courseCredit = this.getCourseCreditByCourseId(teacCourse.getCourseId());
        teacCourse.setCourseName(courseName);
        teacCourse.setCourseCredit(courseCredit);
        System.out.println("=-=-=-=-="+teacCourse);
        //通过老师id查询老师名字
        String teacName = this.getTeacNameByTeacId(teacCourse.getTeacId());
        teacCourse.setTeacName(teacName);
        return teacCourse;
    }
    //根据课程id查询课程的名字
    private String getCourseNameByCourseId(Long courseId) {
        Course course=courseDao.selectByPrimaryKey(courseId);
        String courseName=course.getCourseName();
        return courseName;
    }
    //根据课程id查询课程的学分
    private BigDecimal getCourseCreditByCourseId(Long courseId){
        Course course=courseDao.selectByPrimaryKey(courseId);
        BigDecimal courseCredit=course.getCourseCredit();
        return courseCredit;
    }
    //根据老师id查询老师的名字
    private String getTeacNameByTeacId(Long teacId){
        Teacher teacher=teacherDao.selectByPrimaryKey(teacId);
        String teacName=teacher.getTeacName();
        return teacName;
    }

    //根据teacId查询所有的teac_course记录
    @Override
    public List<TeacCourse> getTeacCourseByteacId(Long teacId){
        //所有teacCourse
        List<TeacCourse> allTeacCourse= getAllTeacCourses();
        System.out.println("allTeacCourse"+allTeacCourse.size());
        //teacId对应的teacCourse
        List<TeacCourse> teacCoursesByteacId=new ArrayList<>();
        for(int i=0;i<allTeacCourse.size();i++){
            TeacCourse teacCourse=allTeacCourse.get(i);
            if(teacCourse.getTeacId() == teacId) {
                teacCoursesByteacId.add(teacCourse);
            }
        }
        return teacCoursesByteacId;
    }

    /**
     * 批量删除教师课程信息
     * @param tcId 教师课程集合
     * @return 删除的数量
     */
    @Override
    public int deleteTeacCourse(List<Long> tcId) {
        TeacCourseExample example = new TeacCourseExample();
        TeacCourseExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(tcId);
        return teacCourseDao.deleteByExample(example);
    }


}
