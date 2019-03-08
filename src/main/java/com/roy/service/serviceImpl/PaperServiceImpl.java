package com.roy.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.roy.mapper.*;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.PaperService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

@Service("paperService")
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperStandardMapper paperStandardDao;
    @Resource
    private PaperMapper paperDao;
    @Resource
    private SelectMapper selectDao;
    @Resource
    private MultiSelectMapper multiSelectDao;
    @Resource
    private FillMapper fillDao;
    @Resource
    private CalculateMapper calculateDao;
    @Resource
    private SubjectMapper subjectDao;
    @Resource
    private JudgeMapper judgeDao;
    @Resource
    private CourseService courseService;
    @Resource
    private StuScoreMapper stuScoreDao;
    @Resource
    private QuestionServiceImpl questionService;

    //试卷标准的单选题
    int selCount = 0;
    String selIds;
    //试卷标准的填空题
    int fillCount = 0;
    String fillIds;
    //试卷标准的多选题
    int mutilCount = 0;
    String mutilIds;
    //试卷标准的判断题
    int judgeCount = 0;
    String judgeIds;
    //试卷标准的计算题
    int calculateCount = 0;
    String calculateIds;
    //试卷标准的主观题
    int subjectCount = 0;
    String subjectIds;

    /**
     * 根据教师课程id查询考试标准 （学生查看考试通知）
     * @param teac_course_id
     * @return
     */
    //得到对应课程的试卷标准
    @Override
    public List<PaperStandard> getPaperStandard(Long teac_course_id) {
        PaperStandardExample example = new PaperStandardExample();
        PaperStandardExample.Criteria criteria = example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teac_course_id);
        List<PaperStandard> paperStandards = paperStandardDao.selectByExample(example);
        return paperStandards;
    }

    @Override
    public List<Paper> getPaperByIds(Long teaccourseId, Long stuId) {
        PaperExample example = new PaperExample();
        PaperExample.Criteria criteria = example.createCriteria();
        criteria.andStuIdEqualTo(stuId);
        criteria.andTeacCourseIdEqualTo(teaccourseId);
        List<Paper> papers = paperDao.selectByExample(example);
        return papers;
    }


    /**
     * 根据teac_course_id得到对应课程的试卷标准
     * @param teac_course_id
     * @return
     */

    @Override
    public Map getPaperStandardMap(Long teac_course_id) {
        PaperStandardExample example = new PaperStandardExample();
        PaperStandardExample.Criteria criteria = example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teac_course_id);
        List<PaperStandard> paperStandards = paperStandardDao.selectByExample(example);
        Map map = new HashMap();
        for (int i = 0; i < paperStandards.size(); i++) {
            PaperStandard paperStandard = paperStandards.get(i);
            if ("单选题".equals(paperStandard.getTestType())) {
                map.put("selCount", paperStandard.getTestAmount());
                map.put("selVal", paperStandard.getTestValue());
            } else if ("填空题".equals(paperStandard.getTestType())) {
                map.put("fillCount", paperStandard.getTestAmount());
                map.put("fillVal", paperStandard.getTestValue());
            } else if ("多选题".equals(paperStandard.getTestType())) {
                map.put("mutilCount", paperStandard.getTestAmount());
                map.put("mutilVal", paperStandard.getTestValue());
            } else if ("判断题".equals(paperStandard.getTestType())) {
                map.put("judgeCount", paperStandard.getTestAmount());
                map.put("judgeVal", paperStandard.getTestValue());
            } else if ("计算题".equals(paperStandard.getTestType())) {
                map.put("calculateCount", paperStandard.getTestAmount());
                map.put("calculateVal", paperStandard.getTestValue());
            } else if ("主观题".equals(paperStandard.getTestType())) {
                map.put("subjectCount", paperStandard.getTestAmount());
                map.put("subjectVal", paperStandard.getTestValue());
            }
        }
        return map;
    }
    /**
     * 新增考试标准  （教师发布考试）
     * @param paperStandard
     * @return
     */
    @Override
    public boolean insertIntoPaperStandard(PaperStandard  paperStandard){
        int result = paperStandardDao.insertSelective(paperStandard);
        return result>0;
    }


    //根据examId去获取相对应的题型的集合
    private List getQuestionsByExamId(int examId) {
        if(examId == 1){
            List<MultiSelect> allMultiSelects=multiSelectDao.selectByExample(null);
            return allMultiSelects;
        }else if(examId==2){
            List<Select> allSelects=selectDao.selectByExample(null);
            return allSelects;
        }else if(examId==3){
            List<Judge> allJudges=judgeDao.selectByExample(null);
            return allJudges;
        }else if(examId==4){
            List<Fill> allFills=fillDao.selectByExample(null);
            return allFills;
        }else if(examId==5){
            List<Calculate> allCalculates=calculateDao.selectByExample(null);
            return allCalculates;
        }else if(examId==6){
            List<Subject> allSubjects=subjectDao.selectByExample(null);
            return allSubjects;
        }
        return null;
    }

    //得到所有的AdminViewQuestion
    private List<AdminViewQuestion> getAllAdminViewQuestions(Long teaccourseId){
        //所有题目
        List<AdminViewQuestion> allAdminViewQuestions=new ArrayList<>();
        //所有多选题
        List<MultiSelect> allMultiSelects=multiSelectDao.selectByExample(null);
        for(int i=0;i<allMultiSelects.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            MultiSelect multiSelect=allMultiSelects.get(i);
            System.out.println(i+";;"+multiSelect);
            adminViewQuestion.setQuestionType("多选题");
            adminViewQuestion.setTitle(multiSelect.getTitle());
            adminViewQuestion.setDificult(multiSelect.getDificult());
            adminViewQuestion.setId(multiSelect.getId());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse = courseService.getTeacCourse(multiSelect.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            // 根据课程id得到课程名字
            String courseName= courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            if(teaccourseId != null && teaccourseId != 0 ){//根据老师id查询题库
                if(teaccourseId == multiSelect.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }

        }

        //所有单选题
        List<Select> allSelects=this.getQuestionsByExamId(2);
        for(int i=0;i<allSelects.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            Select select=allSelects.get(i);
            adminViewQuestion.setQuestionType("单选题");
            adminViewQuestion.setDificult(select.getDificult());
            adminViewQuestion.setId(select.getId());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse=courseService.getTeacCourse(select.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            //根据课程id得到课程名字
            String courseName=courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            adminViewQuestion.setTitle(select.getTitle());
            if(teaccourseId != null && teaccourseId != 0){//根据老师id查询题库
                if(teaccourseId == select.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }
        }
        //所有判断题
        List<Judge> allJudges=this.getQuestionsByExamId(3);
        for(int i=0;i<allJudges.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            Judge judge=allJudges.get(i);
            adminViewQuestion.setQuestionType("判断题");
            adminViewQuestion.setDificult(judge.getDificult());
            adminViewQuestion.setId(judge.getId());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse=courseService.getTeacCourse(judge.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            //根据课程id得到课程名字
            String courseName=courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            adminViewQuestion.setTitle(judge.getTitle());
            if(teaccourseId != null && teaccourseId != 0){//根据老师id查询题库
                if(teaccourseId == judge.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }
        }


        //所有填空题
        List<Fill> allFills=this.getQuestionsByExamId(4);
        for(int i=0;i<allFills.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            Fill fill=allFills.get(i);
            adminViewQuestion.setQuestionType("填空题");
            adminViewQuestion.setDificult(fill.getDificult());
            adminViewQuestion.setId(fill.getId());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse=courseService.getTeacCourse(fill.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            //根据课程id得到课程名字
            String courseName=courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            adminViewQuestion.setTitle(fill.getTitle());
            if(teaccourseId != null && teaccourseId != 0){//根据老师id查询题库
                if(teaccourseId == fill.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }
        }
        //所有计算题
        List<Calculate> allCalculates=this.getQuestionsByExamId(5);
        for(int i=0;i<allCalculates.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            Calculate calculate=allCalculates.get(i);
            adminViewQuestion.setQuestionType("计算题");
            adminViewQuestion.setId(calculate.getId());
            adminViewQuestion.setDificult(calculate.getDificult());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse=courseService.getTeacCourse(calculate.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            //根据课程id得到课程名字
            String courseName=courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            adminViewQuestion.setTitle(calculate.getTitle());
            if(teaccourseId != null && teaccourseId != 0){//根据老师id查询题库
                if(teaccourseId == calculate.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }
        }
        //所有主观题
        List<Subject> allSubjects=this.getQuestionsByExamId(6);
        for(int i=0;i<allSubjects.size();i++){
            AdminViewQuestion adminViewQuestion=new AdminViewQuestion();
            Subject subject=allSubjects.get(i);
            adminViewQuestion.setQuestionType("主观题");
            adminViewQuestion.setId(subject.getId());
            adminViewQuestion.setDificult(subject.getDificult());
            //根据teacCourseId得到teacCourse记录
            TeacCourse teacCourse=courseService.getTeacCourse(subject.getTeacCourseId());
            //得到课程id
            Long courseId= teacCourse.getCourseId();
            //根据课程id得到课程名字
            String courseName=courseService.getCourseById(courseId).getCourseName();
            adminViewQuestion.setCourseName(courseName);
            adminViewQuestion.setTitle(subject.getTitle());
            if(teaccourseId != null && teaccourseId != 0){//根据老师id查询题库
                if(teaccourseId == subject.getTeacCourseId()){
                    allAdminViewQuestions.add(adminViewQuestion);
                }
            }else {//查询所有题库
                allAdminViewQuestions.add(adminViewQuestion);
            }
        }
        return allAdminViewQuestions;
    }




    //根据teacId得到所有的teac_course记录
    public List<TeacCourse> getTeacCourseByteacId(Long teacId){
        //所有teacCourse
        List<TeacCourse> allTeacCourse= courseService.getAllTeacCourses();
        System.out.println("allTeacCourse"+allTeacCourse.size());
        //teacId对应的teacCourse
        List<TeacCourse> teacCoursesByteacId=new ArrayList<>();
        for(int i=0;i<allTeacCourse.size();i++){
            TeacCourse teacCourse=allTeacCourse.get(i);
            if(teacCourse.getTeacId()==teacId) {
                teacCoursesByteacId.add(teacCourse);
            }
        }
        return teacCoursesByteacId;
    }


    //得到所有teacId对应的所有课程的题目,每个老师查看的题目
    @Override
    public List<TeacCourse> TeacherCourseQuestions(Long teacId) {

        //所有teacCourses
        List<TeacCourse> teacCourses = getTeacCourseByteacId(teacId);
        System.out.println("老师教的课程数"+teacCourses.size());
        //teacId对应的老师的课程和试题
        List<TeacCourse> teacCoursesQuestionByteacId = new ArrayList<>();
        for (int i = 0; i < teacCourses.size(); i++) {
            //所需记录的对象
            TeacCourse teacCourse1 = teacCourses.get(i);
            teacCourse1 = courseService.improveCourseMsg(teacCourse1);
            //得到课程id
            Long courseId= teacCourse1.getCourseId();
            //根据课程id得到课程名字
            Course course = courseService.getCourseById(courseId);
            // 得到teac_course_id
            Map<String, List> questionMap = getQuestionsMap(teacCourse1.getId());
            System.out.println("teacCourseId--"+teacCourse1.getId());
            //单选题
            List<Select> allSelects = questionMap.get("allSelects");
            if(allSelects.size() != 0) {
                for (int j = 0; j < allSelects.size(); j++) {
                    TeacCourse teacCourse = new TeacCourse();

                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());
                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    Select select = allSelects.get(j);
                    teacCourse.setId(select.getId());
                    teacCourse.setTitle(select.getTitle());
                    teacCourse.setQuestionType("单选题");
                    teacCourse.setDificult(select.getDificult());
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }

            //多选题
            List<MultiSelect> allmultiSelects = questionMap.get("allmultiSelects");
            if(allmultiSelects.size() != 0) {
                System.out.println("多选题"+teacCourse1);
                for (int x = 0; x < allmultiSelects.size(); x++) {
                    TeacCourse teacCourse = new TeacCourse();

                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    //设置courseName属性和课程编号
                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());

                    MultiSelect multiSelect = allmultiSelects.get(x);
                    teacCourse.setTitle(multiSelect.getTitle());
                    teacCourse.setId(multiSelect.getId());
                    teacCourse.setQuestionType("多选题");
                    teacCourse.setDificult(multiSelect.getDificult());
                    System.out.println(teacCourse1);
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }
            //判断题
            List<Judge> alljudges = questionMap.get("alljudges");
            if(alljudges.size() != 0) {
                for (int q = 0; q < alljudges.size(); q++) {
                    TeacCourse teacCourse = new TeacCourse();

                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    //设置courseName属性和课程编号
                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());
                    Judge judge = alljudges.get(q);
                    teacCourse.setTitle(judge.getTitle());
                    teacCourse.setId(judge.getId());
                    teacCourse.setQuestionType("判断题");
                    teacCourse.setDificult(judge.getDificult());
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }
            //填空题
            List<Fill> allfills = questionMap.get("allfills");
            if(allfills.size() != 0) {
                for (int w = 0; w < allfills.size(); w++) {
                    TeacCourse teacCourse = new TeacCourse();

                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    //设置courseName属性和课程编号
                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());
                    Fill fill = allfills.get(w);
                    teacCourse.setTitle(fill.getTitle());
                    teacCourse.setId(fill.getId());
                    teacCourse.setQuestionType("填空题");
                    teacCourse.setDificult(fill.getDificult());
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }
            // 计算题
            List<Calculate> allcalculates = questionMap.get("allcalculates");
            if(allcalculates.size() != 0) {
                for (int e = 0; e < allcalculates.size(); e++) {
                    TeacCourse teacCourse = new TeacCourse();
                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    //设置courseName属性和课程编号
                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());
                    Calculate calculate = allcalculates.get(e);
                    teacCourse.setTitle(calculate.getTitle());
                    teacCourse.setId(calculate.getId());
                    teacCourse.setQuestionType("计算题");
                    teacCourse.setDificult(calculate.getDificult());
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }
            //主观题
            List<Subject> allsubjects = questionMap.get("allsubjects");
            if(allsubjects.size() != 0) {
                for (int r = 0; r < allsubjects.size(); r++) {
                    TeacCourse teacCourse = new TeacCourse();
                    teacCourse.setCourseCredit(teacCourse1.getCourseCredit());
                    teacCourse.setTeacName(teacCourse1.getTeacName());
                    teacCourse.setStuId(teacCourse1.getStuId());

                    //设置courseName属性和课程编号
                    teacCourse.setCourseName(course.getCourseName());
                    teacCourse.setCourseNum(course.getCourseNum());

                    Subject subject = allsubjects.get(r);
                    teacCourse.setTitle(subject.getTitle());
                    teacCourse.setId(subject.getId());
                    teacCourse.setQuestionType("主观题");
                    teacCourse.setDificult(subject.getDificult());
                    teacCoursesQuestionByteacId.add(teacCourse);
                }
            }

        }
        return teacCoursesQuestionByteacId;
    }

    //得到存放所有teacCourseId对应题目的Map集合
    public Map<String,List> getQuestionsMap(Long teacCourseId){
        //存放所有题目
        Map<String,List> questionMap=new HashMap<>();
        //根据teac_course_id去查找对应的题目
        //单选题
        SelectExample selexample=new SelectExample();
        SelectExample.Criteria selcriteria=selexample.createCriteria();
        selcriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<Select> allSelects=selectDao.selectByExample(selexample);
        if(allSelects!=null) {
            questionMap.put("allSelects",allSelects);
        }else {
            questionMap.put("allSelects",new ArrayList());
        }
        //多选题
        MultiSelectExample mulexample=new MultiSelectExample();
        MultiSelectExample.Criteria mulcriteria=mulexample.createCriteria();
        mulcriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<MultiSelect> allmultiSelects=multiSelectDao.selectByExample(mulexample);
        if(allmultiSelects!=null) {
            questionMap.put("allmultiSelects",allmultiSelects);
        }else {
            questionMap.put("allmultiSelects",new ArrayList());
        }
        //判断题
        JudgeExample judgeExample=new JudgeExample();
        JudgeExample.Criteria judgecriteria=judgeExample.createCriteria();
        judgecriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<Judge> alljudges=judgeDao.selectByExample(judgeExample);
        if(alljudges!=null) {
            questionMap.put("alljudges",alljudges);
        }else {
            questionMap.put("alljudges",new ArrayList());
        }
        //填空题
        FillExample fillExample=new FillExample();
        FillExample.Criteria fillcriteria=fillExample.createCriteria();
        fillcriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<Fill> allfills=fillDao.selectByExample(fillExample);
        if(allfills!=null) {
            questionMap.put("allfills",allfills);
        }else {
            questionMap.put("allfills",new ArrayList());
        }
        //计算题
        CalculateExample calculateExample=new CalculateExample();
        CalculateExample.Criteria calculatecriteria=calculateExample.createCriteria();
        calculatecriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<Calculate> allcalculates=calculateDao.selectByExample(calculateExample);
        if(allcalculates!=null) {
            questionMap.put("allcalculates",allcalculates);
        }else {
            questionMap.put("allcalculates",new ArrayList());
        }
        //主观题
        SubjectExample subjectExample=new SubjectExample();
        SubjectExample.Criteria subjectcriteria=subjectExample.createCriteria();
        subjectcriteria.andTeacCourseIdEqualTo(teacCourseId);
        List<Subject> allsubjects=subjectDao.selectByExample(subjectExample);
        if(allsubjects!=null) {
            questionMap.put("allsubjects",allsubjects);
        }else {
            questionMap.put("allsubjects",new ArrayList());
        }
        return questionMap;
    }

    /**
     * 老师查看题库根据courseName，questionType，teacCourseId
     * @param pageIndex
     * @param courseName
     * @param questionType
     * @return
     */
    @Override
    public PageInfo SearchTeacherViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String questionType,Long id, Long teaccourseId) {
        List<TeacCourse> teacCourses = new ArrayList<>();
        //老师查看题库
        teacCourses = this.TeacherCourseQuestions(id);
        List<TeacCourse> courses = new ArrayList<>();

        for(int i=0;i<teacCourses.size();i++) {
            TeacCourse course = teacCourses.get(i);
            //courseName，questionType都不为空
            if(!"".equals(courseName) && courseName != null && !"".equals(questionType) && questionType != null){
                if (course.getCourseName().equals(courseName) && course.getQuestionType().equals(questionType)) {
                //  if (course.getId().equals(teaccourseId) && course.getCourseName().equals(courseName) && course.getQuestionType().equals(questionType)) {
                    courses.add(course);
                }
            }
            //courseName不为空，questionType为空
            else if(!"".equals(courseName) && courseName != null && ("".equals(questionType) || questionType == null)){
                if (course.getCourseName().equals(courseName)) {
                    courses.add(course);
                }
            }
            //courseName为空，questionType不为空
            else if(("".equals(courseName) || courseName == null ) && !"".equals(questionType) && questionType != null){
                if (course.getQuestionType().equals(questionType)) {
                    courses.add(course);
                }
            }else {//courseName，questionType都为空

                    courses.add(course);

            }
        }
        //PageHelper.startPage(pageIndex,5);
        PageInfo pageInfo=new PageInfo(courses,5);
        return pageInfo;
    }

    /**
     * 根据 题目id集合 和 题目类型 批量删除 题目
     * @param questionIds
     * @param questionType
     * @return
     */

    @Override
    public int deleteQuestion(List<Long> questionIds, String questionType) {
        if("多选题".equals(questionType)) {
            MultiSelectExample example=new MultiSelectExample();
            MultiSelectExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return multiSelectDao.deleteByExample(example);
        }else if("单选题".equals(questionType)) {
            SelectExample example=new SelectExample();
            SelectExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return selectDao.deleteByExample(example);
        }else if("判断题".equals(questionType)) {
            JudgeExample example=new JudgeExample();
            JudgeExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return judgeDao.deleteByExample(example);
        }else if("填空题".equals(questionType)) {
            FillExample example=new FillExample();
            FillExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return fillDao.deleteByExample(example);
        }else if("计算题".equals(questionType)) {
            CalculateExample example=new CalculateExample();
            CalculateExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return calculateDao.deleteByExample(example);
        }else if("主观题".equals(questionType)) {
            SubjectExample example=new SubjectExample();
            SubjectExample.Criteria criteria=example.createCriteria();
            criteria.andIdIn(questionIds);
            return subjectDao.deleteByExample(example);
        }
        return 0;
    }

    /**
     * 管理员查看题库
     * 根据courseName，questionType分页的信息
     * @param pageIndex
     * @param courseName
     * @param questionType
     * @param teaccourseId
     * @return
     */
    @Override
    public PageInfo SearchAdminViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String questionType, Long teaccourseId) {
        List<AdminViewQuestion> adminViewQuestions = new ArrayList<>();
       //管理员查看题库
            adminViewQuestions  = this.getAllAdminViewQuestions(teaccourseId);

        List<AdminViewQuestion> adminViewQuestions1=new ArrayList<>();

        for(int i=0;i<adminViewQuestions.size();i++) {
            AdminViewQuestion adminViewQuestion = adminViewQuestions.get(i);
            //courseName，questionType都不为空
            if(!"".equals(courseName) && courseName != null && !"".equals(questionType) && questionType != null){
                if (adminViewQuestion.getCourseName().equals(courseName) && adminViewQuestion.getQuestionType().equals(questionType)) {
                    adminViewQuestions1.add(adminViewQuestion);
                }
            }
            //courseName不为空，questionType为空
            else if(!"".equals(courseName) && courseName != null && ("".equals(questionType) || questionType == null)){
                if (adminViewQuestion.getCourseName().equals(courseName)) {
                    adminViewQuestions1.add(adminViewQuestion);
                }
            }
            //courseName为空，questionType不为空
            else if(("".equals(courseName) || courseName == null ) && !"".equals(questionType) && questionType != null){
                if (adminViewQuestion.getQuestionType().equals(questionType)) {
                    adminViewQuestions1.add(adminViewQuestion);
                }
            }else {//courseName，questionType都为空
                adminViewQuestions1.add(adminViewQuestion);
            }
        }
        //PageHelper.startPage(pageIndex,5);
        PageInfo pageInfo=new PageInfo(adminViewQuestions1,5);
        return pageInfo;
    }
    //根据题目类型和题目id去查题目
    @Override
    public Object SearchQuestionByTitleAndType(Long title,String questionType){
        if("多选题".equals(questionType)) {
            MultiSelectExample example=new MultiSelectExample();
            MultiSelectExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return multiSelectDao.selectByExample(example).get(0);
        }else if("单选题".equals(questionType)) {
            SelectExample example=new SelectExample();
            SelectExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return selectDao.selectByExample(example).get(0);
        }else if("判断题".equals(questionType)) {
            JudgeExample example=new JudgeExample();
            JudgeExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return judgeDao.selectByExample(example).get(0);
        }else if("填空题".equals(questionType)) {
            FillExample example=new FillExample();
            FillExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return fillDao.selectByExample(example).get(0);
        }else if("计算题".equals(questionType)) {
            CalculateExample example=new CalculateExample();
            CalculateExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return calculateDao.selectByExample(example).get(0);
        }else if("主观题".equals(questionType)) {
            SubjectExample example=new SubjectExample();
            SubjectExample.Criteria criteria=example.createCriteria();
            criteria.andIdEqualTo(title);
            return subjectDao.selectByExample(example).get(0);
        }
        return null;
    }

//////////////////////////////////////////

    /**
     * 生成试卷
     *
     * @param teaccourseid
     * @param stuid
     * @param model
     * @return
     */
    @Override
    public Model createPaper(@RequestParam("teaccourseid") Long teaccourseid,
                             @RequestParam("stuid") Long stuid, Model model) {

        Map paperStandardMap = getPaperStandardMap(teaccourseid);
        selCount = (int) paperStandardMap.get("selCount");
        fillCount = (int) paperStandardMap.get("fillCount");
        mutilCount = (int) paperStandardMap.get("mutilCount");
        judgeCount = (int) paperStandardMap.get("judgeCount");
        calculateCount = (int) paperStandardMap.get("calculateCount");
        subjectCount = (int) paperStandardMap.get("subjectCount");
        Map map = new HashMap();

        if (selCount > 0) {
            map = getSelect(teaccourseid, selCount);
            selIds = myTrim(map.get("randomselectIds").toString());
            model.addAttribute("selects", map.get("selects"));
        }

        if (fillCount > 0) {
            map = getFill(teaccourseid, fillCount);
            fillIds = myTrim(map.get("randomFillIds").toString());
            model.addAttribute("fills", map.get("fills"));
        }

        if (mutilCount > 0) {
            map = getMutilSelect(teaccourseid, mutilCount);
            mutilIds = myTrim(map.get("randomMutilIds").toString());
            model.addAttribute("multiSelects", map.get("multiSelects"));
        }

        if (judgeCount > 0) {
            map = getJudge(teaccourseid, judgeCount);
            judgeIds = myTrim(map.get("randomJudgeIds").toString());
            model.addAttribute("judges", map.get("judges"));
        }

        if (calculateCount > 0) {
            map = getCalculate(teaccourseid, calculateCount);
            calculateIds = myTrim(map.get("randomCalculateIds").toString());
            model.addAttribute("calculates", map.get("calculates"));
        }

        if (subjectCount > 0) {
            map = getSubject(teaccourseid, subjectCount);
            subjectIds = myTrim(map.get("randomSubjectIds").toString());
            model.addAttribute("subjects", map.get("subjects"));
        }

        Paper paper = new Paper();
        paper.setTeacCourseId(teaccourseid);
        paper.setStuId(stuid);
        paper.setSelectionIds(selIds);
        paper.setMultiSelectionIds(mutilIds);
        paper.setJudgeIds(judgeIds);
        paper.setFillIds(fillIds);
        paper.setCalculateIds(calculateIds);
        paper.setSubjectIds(subjectIds);
        paper.setTestTime(new Date());
        boolean flag = paperDao.insertSelective(paper) > 0;

        if (flag) {
            Long paperId = searchPaperByIds(null, teaccourseid, stuid).getId();
            System.out.println("试卷生成成功");
            model.addAttribute("paperid", paperId);
            model.addAttribute("stuId", stuid);
            model.addAttribute("teacCourseId", teaccourseid);
            System.out.println("CreatePaper():" + model);
            return model;
        } else {
            return null;
        }

    }

    /**
     * 更新试卷
     *学生交卷
     * @param paper
     * @return
     */
    @Override
    public boolean updatePaper(Paper paper) {

        return paperDao.updateByPrimaryKeySelective(paper) > 0;
    }

    /**
     * (教师批阅)显示学生试卷 或 学生查看试卷
     *
     * @param id 试卷id
     * @param score6 主观题成绩（如果为-1，表示还没有主观题成绩）
     * @return
     */
    @Override
    public Model toMarking(Long id, int score6, Model model,String role){

        Paper paper = paperDao.selectByPrimaryKey(id);
        model.addAttribute("standard",getPaperStandardMap(paper.getTeacCourseId()));
        System.out.println("standard"+getPaperStandardMap(paper.getTeacCourseId()));
        model.addAttribute("paperid",paper.getId());
        //试题
        String selectIds = paper.getSelectionIds();
        List<Select> selects = getPaper(selectIds,1);
        model.addAttribute("selects",selects);
        String fillIds = paper.getFillIds();
        List<Fill> fills = getPaper(fillIds,3);
        model.addAttribute("fills",fills);
        String mutilIds = paper.getMultiSelectionIds();
        List<MultiSelect> multiSelects = getPaper(mutilIds,2);
        model.addAttribute("multiSelects",multiSelects);
        String judgeIds = paper.getJudgeIds();
        List<Judge> judges = getPaper(judgeIds,4);
        model.addAttribute("judges",judges);
        String calculateIds = paper.getCalculateIds();
        List<Calculate> calculates = getPaper(calculateIds,5);
        model.addAttribute("calculates",calculates);
        String subjectIds = paper.getSubjectIds();
        List<Subject> subjects = getPaper(subjectIds,6);
        model.addAttribute("subjects",subjects);
        //回答
        String selectAns = paper.getSelectionAnswers();
        List<String> selectAnsList = Arrays.asList(selectAns.split(","));
        model.addAttribute("selectAnsList",selectAnsList);
        String mutilAns = paper.getMultiSelectionAnswers();
        List<String> mutilAnsList = Arrays.asList(mutilAns.split(","));
        model.addAttribute("mutilAnsList",mutilAnsList);
        String fillAns = paper.getFillAnswers();
        List<String> fillAnsList = Arrays.asList(fillAns.split(","));
        model.addAttribute("fillAnsList",fillAnsList);
        String judgeAns = paper.getJudgeAnswers();
        List<String> judgeAnsList = Arrays.asList(judgeAns.split(","));
        model.addAttribute("judgeAnsList",judgeAnsList);
        String calculateAns = paper.getCalculateAnswers();
        List<String> calculateAnsList = Arrays.asList(calculateAns.split(","));
        System.out.println("calculateAnsList"+calculateAnsList);
        model.addAttribute("calculateAnsList",calculateAnsList);
        List<String> subjectAnsList = new ArrayList<>();

        String[] subjectAnsArr={paper.getSubjectAnswer1(),paper.getSubjectAnswer2(),paper.getSubjectAnswer3(),paper.getSubjectAnswer4(),paper.getSubjectAnswer5()};
        for (int i=0;i<subjects.size();i++) {
            if(subjectAnsArr[i] != null && !"".equals(subjectAnsArr[i])) {
                subjectAnsList.add(subjectAnsArr[i]);
            }else {
                subjectAnsList.add("#");
            }
        }
        model.addAttribute("subjectAnsList",subjectAnsList);
        StuScore stuScore = new StuScore();
        //判断角色
        if("teacher".equals(role)){//老师阅卷
            //计算成绩
            Map map = doMarking(id, score6);
            stuScore = (StuScore) map.get("stuScore");
        }else{//学生或管理员查看试卷
            stuScore = getStuScoreByPaperId(id);
        }
        model.addAttribute("stuScore",stuScore);
        // System.out.println(model);
        return model;
    }

    private StuScore getStuScoreByPaperId(Long paperId){
        StuScoreExample example = new StuScoreExample();
        StuScoreExample.Criteria criteria = example.createCriteria();
        criteria.andPaperIdEqualTo(paperId);
        List stuScores = stuScoreDao.selectByExample(example);
        if(stuScores.size()>0){
            return (StuScore) stuScores.get(0);
        }
        return new StuScore();
    }
    /**
     * 根据试题IDs获取试题集合
     * @param ids
     * @param type : 1 单选题，2 多选题，3 填空题，4 判断题，5 计算题，6 主观题
     * @return
     */
    public List getPaper(String ids,int type){
        List list = new ArrayList();
        //试题id集合
        if(ids != null && !"".equals(ids)) {
            List<String> IdsList = Arrays.asList(ids.split(","));

            for (String selId : IdsList) {
                String d = selId.trim();
                if (type == 1) {//单选题
                    list.add(getSelectBysel_id(d));
                } else if (type == 2) {//多选题
                    list.add(getMultiSelectsByMultiId(d));
                } else if (type == 3) {//填空题
                    list.add(getFillByFillId(d));
                } else if (type == 4) {//判断题
                    list.add(getJudgeByJudgeId(d));
                } else if (type == 5) {//计算题
                    list.add(getCalculateByCalculateId(d));
                } else if (type == 6) {//主观题
                    list.add(getSubjectBySubjectId(d));
                }
            }
        }
        return list;
    }

    @Override
    public boolean addStuScoreSelective(Paper paper,int time) {
        StuScore stuScore = new StuScore();
        stuScore.setPaperId(paper.getId());
        stuScore.setStuId(paper.getStuId());
        stuScore.setTeacCourseId(paper.getTeacCourseId());
        stuScore.setTestTime(time);
        return stuScoreDao.insertSelective(stuScore) > 0;
    }

    /**
     * 添加单选题
     * @param select
     * @return
     */
    @Override
    public boolean addSelect(Select select) {
        int size = getQuestionsByExamId(2).size();
        Select obj = (Select) getQuestionsByExamId(2).get(size-1);
        select.setSelectionId("sel-"+(obj.getId()+1));
        int result = selectDao.insertSelective(select);
        return result > 0;
    }

    /**
     * 添加多选题
     * @param multiSelect
     * @return
     */
    @Override
    public boolean addMulSel(MultiSelect multiSelect) {
        int size = getQuestionsByExamId(1).size();
        MultiSelect obj = (MultiSelect) getQuestionsByExamId(1).get(size-1);
        multiSelect.setMultiSelectionId("ms-"+(obj.getId()+1));
        int result = multiSelectDao.insertSelective(multiSelect);
        return result > 0;
    }

    /**
     * 添加填空题
     * @param fill
     * @return
     */
    @Override
    public boolean addFill(Fill fill) {
        int size = getQuestionsByExamId(4).size();
        Fill obj = (Fill) getQuestionsByExamId(4).get(size-1);
        fill.setFillId("f-"+(obj.getId()+1));
        int result = fillDao.insertSelective(fill);
        return result > 0;
    }

    /**
     * 添加判断题
     * @param judge
     * @return
     */
    @Override
    public boolean addJudge(Judge judge) {
        int size = getQuestionsByExamId(3).size();
        Judge obj = (Judge) getQuestionsByExamId(3).get(size-1);
        judge.setJudgeId("j-"+(obj.getId()+1));
        int result = judgeDao.insertSelective(judge);
        return result > 0;
    }

    /**
     * 添加计算题
     * @param calculate
     * @return
     */
    @Override
    public boolean addCal(Calculate calculate) {
        int size = getQuestionsByExamId(5).size();
        Calculate calculate1 = (Calculate) getQuestionsByExamId(5).get(size-1);
        calculate.setCalculateId("c-"+(calculate1.getId()+1));
        int result = calculateDao.insertSelective(calculate);
        return result > 0;
    }

    /**
     * 添加主观题
     * @param subject
     * @return
     */
    @Override
    public boolean addSubject(Subject subject) {
        int size = getQuestionsByExamId(6).size();
        Subject obj = (Subject) getQuestionsByExamId(6).get(size-1);
        subject.setSubjectId("sub-"+(obj.getId()+1));
        int result = subjectDao.insertSelective(subject);
        return result > 0;
    }

    int score = 0;

    /**
     * 自动计算客观题成绩并记录到学生成绩表中
     * @param id 试卷ID
     * @param score6 主观题成绩（如果为-1，表示还没有主观题成绩）
     * @return score客观题成绩
     */
    @Override
    public Map doMarking(Long id,int score6){
        score = 0;
        //获取试卷
        Paper paper = paperDao.selectByPrimaryKey(id);
        //获取试卷规格
        Map paperStandardMap= getPaperStandardMap(paper.getTeacCourseId());
        int selValue = (int)paperStandardMap.get("selVal");
        int fillVal = (int)paperStandardMap.get("fillVal");
        int mutilVal = (int)paperStandardMap.get("mutilVal");
        int judgeVal = (int)paperStandardMap.get("judgeVal");
        int calculateVal = (int)paperStandardMap.get("calculateVal");
        //试题ids
        String selectIds = paper.getSelectionIds();
        String fillIds = paper.getFillIds();
        String mutilIds = paper.getMultiSelectionIds();
        String judgeIds = paper.getJudgeIds();
        String calculateIds = paper.getCalculateIds();
        //学生回答
        String selectAns = paper.getSelectionAnswers();
        String fillAns = paper.getFillAnswers();
        String mutilAns = paper.getMultiSelectionAnswers();
        String judgeAns = paper.getJudgeAnswers();
        String calculateAns = paper.getCalculateAnswers();
        StuScore stuScore = new StuScore();
        stuScore.setPaperId(id);
        //选题成绩(type: 1 单选题，2 多选题，3 填空题，4 判断题，5 计算题)
        int score1 = calScore(selectIds,selectAns,selValue,1);
        stuScore.setSelectionCount(score1);
        int score3 = calScore(fillIds,fillAns,fillVal,3);
        stuScore.setFillCount(score3);
        int score2 = calScore(mutilIds,mutilAns,mutilVal,2);
        stuScore.setMultiSelectionCount(score2);
        int score4 = calScore(judgeIds,judgeAns,judgeVal,4);
        stuScore.setJudgeCount(score4);
        int score5 = calScore(calculateIds,calculateAns,calculateVal,5);
        stuScore.setCalculateCount(score5);
        if(score6 == -1){
            score6 = 0;
        }
        stuScore.setSubjectCount(score6);
        score = score1+score2+score3+score4+score5+score6;

        stuScore.setScore(score);

        //记录学生成绩
        int result = 0;
        if(score6 == -1){//不存在记录
            result = stuScoreDao.insertSelective(stuScore);
        }else {//已存在记录
            StuScoreExample example = new StuScoreExample();
            StuScoreExample.Criteria criteria = example.createCriteria();
            criteria.andPaperIdEqualTo(id);
            result = stuScoreDao.updateByExampleSelective(stuScore,example);
        }
        Paper paper1 = new Paper();
        paper1.setId(id);
        paper1.setPapeState(1);
        paper1.setTotalScore(score);
        //更新试卷成绩
        result += paperDao.updateByPrimaryKeySelective(paper1);
        if(result > 1){
            System.out.println("记录成功");
        }else {
            System.out.println("记录失败");
        }
        Map map = new HashMap();
        map.put("stuScore",stuScore);
        System.out.println("成绩"+score);

        map.put("score",score);
        return map;
    }

    /**
     * 分题型计算成绩
     * @param ids 试题ID集合(String)
     * @param ans 学生回答集合(String)
     * @param Value 每题分值(int)
     * @param type 类型（int）: 1 单选题，2 多选题，3 填空题，4 判断题，5 计算题
     * @return
     */
    private int calScore(String ids,String ans,int Value,int type){
        score = 0;
        //试题id集合
        List<String> IdsList = new ArrayList<>();
        if(ids != null) {
           IdsList  = Arrays.asList(ids.split(","));
        }
        //试题答案集合
        List<String> KeyList = new ArrayList<>();
        List<String> AnsList = Arrays.asList(ans.split(","));
        //获取单选题答案集合
        String key = null;
        for (String selId : IdsList) {
            String d = selId.trim();
            if(type == 1) {//单选题
                key = getSelectBysel_id(d).getAnswer().trim();
            }else if(type == 2){//多选题
                key = getMultiSelectsByMultiId(d).getAnswer().trim();
            }else if(type == 3){//填空题
                key = getFillByFillId(d).getAnswer1().trim();
            }else if(type == 4){//判断题
                key = getJudgeByJudgeId(d).getAnswer().trim();
            }else if(type == 5){//计算题
                key = getCalculateByCalculateId(d).getAnswer1().trim();
            }
            KeyList.add(key);
        }
        System.out.println("回答集合"+AnsList);
        System.out.println("答案"+KeyList);
        for (int i=0;i<KeyList.size();i++) {
            if(KeyList.get(i).equals(AnsList.get(i))){
                score += Value;
            }
        }
        //清空集合
        KeyList.clear();
        System.out.println("calScore"+score);
        return score;
    }


    //得到所有这门课程的sel_ids
    @Override
    public List<String> selectAllSelectsIds(Long teaccourseid) {
        SelectExample example=new SelectExample();
        SelectExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Select>  selects=selectDao.selectByExample(example);
        List<String> selectIds=new ArrayList<>();
        for(int i=0;i<selects.size();i++){
            String id= selects.get(i).getSelectionId();
            selectIds.add(id);
        }
        return selectIds;
    }
    //根据sel_id去找试题
    @Override
    public Select getSelectBysel_id(String selId) {
        SelectExample example=new SelectExample();
        SelectExample.Criteria criteria=example.createCriteria();
        criteria.andSelectionIdEqualTo(selId);
        Select select=selectDao.selectByExample(example).get(0);
        return select;
    }
    //所有多项选择题的mutil_id
    @Override
    public List<String> selectAllMutilSelectsIds(Long teaccourseid) {
        MultiSelectExample example=new MultiSelectExample();
        MultiSelectExample .Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<MultiSelect> multiSelects=multiSelectDao.selectByExample(example);

        List<String> multiselectIds=new ArrayList<>();
        for(int i=0;i<multiSelects.size();i++){
            String id= multiSelects.get(i).getMultiSelectionId();
            multiselectIds.add(id);
        }
        return multiselectIds;
    }
    //获得多项选择题题目
    @Override
    public MultiSelect getMultiSelectsByMultiId(String multiId) {
        MultiSelectExample example=new MultiSelectExample();
        MultiSelectExample .Criteria criteria=example.createCriteria();
        criteria.andMultiSelectionIdEqualTo(multiId);
        MultiSelect multiSelect=multiSelectDao.selectByExample(example).get(0);
        return multiSelect;
    }
    //所有填空题的fill_id
    @Override
    public List<String> selectAllFillIds(Long teaccourseid) {
        FillExample example=new FillExample();
        FillExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Fill> fills=fillDao.selectByExample(example);
        List<String> fillIds=new ArrayList<>();
        for(int i=0;i<fills.size();i++){
            String fillId=fills.get(i).getFillId();
            fillIds.add(fillId);
        }
        return fillIds;
    }
    //根据fill_id得到填空题
    @Override
    public Fill getFillByFillId(String fillId) {
        FillExample example=new FillExample();
        FillExample.Criteria criteria=example.createCriteria();
        criteria.andFillIdEqualTo(fillId);
        Fill fill=fillDao.selectByExample(example).get(0);
        return fill;
    }
    //对应课程所有计算机的calculate_id

    @Override
    public List<String> selectAllCalculateIds(Long teaccourseid) {
        CalculateExample example=new  CalculateExample();
        CalculateExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Calculate> calculates=calculateDao.selectByExample(example);
        List<String> calculateIds=new ArrayList<>();
        for(int i=0;i<calculates.size();i++){
            String calculateId=calculates.get(i).getCalculateId();
            calculateIds.add(calculateId);
        }
        return calculateIds;
    }
    //根据calculate_id获得计算题
    @Override
    public Calculate getCalculateByCalculateId(String calculateId) {
        CalculateExample example=new CalculateExample();
        CalculateExample.Criteria criteria=example.createCriteria();
        criteria.andCalculateIdEqualTo(calculateId);
        Calculate calculate=calculateDao.selectByExample(example).get(0);
        return calculate;
    }
    //所有判断题的judgeId
    @Override
    public List<String> selectAllJudegeIds(Long teaccourseid) {
        JudgeExample example=new JudgeExample();
        JudgeExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Judge> judges=judgeDao.selectByExample(example);
        List<String> judgeIds=new ArrayList<>();
        for(int i=0;i<judges.size();i++){
            String judgeId=judges.get(i).getJudgeId();
            judgeIds.add(judgeId);
        }
        return judgeIds;
    }
    //根据judgeId取判断题
    @Override
    public Judge getJudgeByJudgeId(String judgeId) {
        JudgeExample example=new JudgeExample();
        JudgeExample.Criteria criteria=example.createCriteria();
        criteria.andJudgeIdEqualTo(judgeId);
        Judge judge= judgeDao.selectByExample(example).get(0);
        return judge;
    }
    //所有subject_id
    @Override
    public List<String> selectAllSubjectIds(Long teaccourseid) {
        SubjectExample example=new SubjectExample();
        SubjectExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Subject> subjects=subjectDao.selectByExample(example);
        List<String> subjectIds=new ArrayList<>();
        for(int i=0;i<subjects.size();i++){
            String subjectId=subjects.get(i).getSubjectId();
            subjectIds.add(subjectId);
        }
        return subjectIds;
    }
    //根据subject_id取subject题
    @Override
    public Subject getSubjectBySubjectId(String subjectId) {
        SubjectExample example=new SubjectExample();
        SubjectExample.Criteria criteria=example.createCriteria();
        criteria.andSubjectIdEqualTo(subjectId);
        Subject subject=subjectDao.selectByExample(example).get(0);
        return subject;
    }

    /**
     * 根据ID查询试卷信息
     * @param id 试卷ID
     * @param teaccourseid 教师课程ID
     * @param stuid 学生ID
     * @return
     */
    @Override
    public Paper searchPaperByIds(Long id,Long teaccourseid, Long stuid) {
        PaperExample paperExample = new PaperExample();
        PaperExample.Criteria criteria = paperExample.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }else {
            criteria.andTeacCourseIdEqualTo(teaccourseid);
            criteria.andStuIdEqualTo(stuid);
        }
        Paper paper = paperDao.selectByExample(paperExample).get(0);
        return paper;
    }


    /**
     * 去除首尾的[]
     * @param s
     * @return
     */
    public String myTrim(String s){
        int start=0,end=s.length()-1;

        while(start<=end&&s.charAt(start)=='['){
            start++;
        }
        while(start<=end&&s.charAt(end)==']'){
            end--;
        }
        return s.substring(start,end+1);
    }

    /**
     * 获取选择题
     * @param teaccourseid
     * @return
     */
    public Map getSelect(@RequestParam("teaccourseid") Long teaccourseid, int count){
        Map map = new HashMap();
        //最终随机生成的单选题的sel_id的集合
        List<Select> selects = new ArrayList<>();
        //对应课程的所有单选题的sek-id集合
        List<String> selectIds = selectAllSelectsIds(teaccourseid);

        List<String> randomselectIds = getRandomIds(selectIds,count);
        for(int i=0;i<randomselectIds.size();i++){
            Select select= getSelectBysel_id(randomselectIds.get(i));
            selects.add(select);
        }
        map.put("selects",selects);
        map.put("randomselectIds",randomselectIds);
        return map;
    }

    /**
     * 获取多选题
     * @param teaccourseid
     * @param count
     * @return
     */

    public Map getMutilSelect(@RequestParam("teaccourseid") Long teaccourseid,int count){
        Map map = new HashMap();
        //多项选择题
        //对应课程的所有多选题的multi-id集合
        List<String> multiselectIds= selectAllMutilSelectsIds(teaccourseid);
        // 生成随机试题集合
        List<String> randomMutilIds= getRandomIds(multiselectIds,count);
        //根据上面得到的随机生成的randomMutilIds去把多项选择题取出
        List<MultiSelect> multiSelects=new ArrayList<>();
        for(int i=0;i<randomMutilIds.size();i++){
            MultiSelect multiSelect= getMultiSelectsByMultiId(randomMutilIds.get(i));
            multiSelects.add(multiSelect);
        }
        map.put("multiSelects",multiSelects);
        map.put("randomMutilIds",randomMutilIds);
        return map;
    }

    /**
     * 获取填空题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getFill(@RequestParam("teaccourseid") Long teaccourseid,int count){
        Map map = new HashMap();
        //填空题
        //对应课程的所有填空题的fill-id集合
        List<String> fillIds= selectAllFillIds(teaccourseid);
        // 生成随机试题集合
        List<String> randomFillIds= getRandomIds(fillIds,count);
        //根据randomFillIds去取题目
        List<Fill> fills=new ArrayList<>();
        for(int i=0;i<randomFillIds.size();i++){
            Fill fill=  getFillByFillId(randomFillIds.get(i));
            fills.add(fill);
        }
        map.put("fills",fills);
        map.put("randomFillIds",randomFillIds);
        return map;
    }

    /**
     * 获取计算题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getCalculate(@RequestParam("teaccourseid") Long teaccourseid,int count){
        Map map = new HashMap();
        //计算题
        List<String> calculateIds= selectAllCalculateIds(teaccourseid);
        // 生成随机试题集合
        List<String> randomCalculateIds= getRandomIds(calculateIds,count);
        //根据randomCalculateIds去取题目
        List<Calculate> calculates=new ArrayList<>();
        for(int i=0;i<randomCalculateIds.size();i++){
            Calculate calculate= getCalculateByCalculateId(randomCalculateIds.get(i));
            calculates.add(calculate);
        }

        map.put("calculates",calculates);
        map.put("randomCalculateIds",randomCalculateIds);
        return map;
    }

    /**
     * 获取判断题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getJudge(@RequestParam("teaccourseid") Long teaccourseid,int count){
        Map map = new HashMap();

        // 判断题
        List<String> judgeIds = selectAllJudegeIds(teaccourseid);
        //生成随机试题集合
        List<String> randomJudgeIds = getRandomIds(judgeIds, count);
        //根据randomJudgeIds取判断题
        List<Judge> judges = new ArrayList<>();
        for (int i = 0; i < randomJudgeIds.size(); i++) {
            Judge judge = getJudgeByJudgeId(randomJudgeIds.get(i));
            judges.add(judge);
        }
        map.put("judges", judges);
        map.put("randomJudgeIds", randomJudgeIds);

        return map;
    }

    /**
     * 获取主观题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getSubject(@RequestParam("teaccourseid") Long teaccourseid, int count){
        Map map = new HashMap();
        //subject题
        List<String> subjectIds= selectAllSubjectIds(teaccourseid);
        // 生成随机试题集合
        List<String> randomSubjectIds = getRandomIds(subjectIds,count);
        //根据 randomSubjectIds取对应的试题
        List<Subject> subjects=new ArrayList<>();
        for(int i=0;i<randomSubjectIds.size();i++){
            Subject subject= getSubjectBySubjectId(randomSubjectIds.get(i));
            subjects.add(subject);
        }
        map.put("subjects",subjects);
        map.put("randomSubjectIds",randomSubjectIds);
        return map;
    }

    /**
     * 生成随机试题集合
     * @param Ids 符合条件的试题集合
     * @param count 待选取数
     * @return
     */
    private List<String> getRandomIds(List<String> Ids,int count){
        Random randomSubject = new Random();
        //随机生成的数
        List<Integer> index=new ArrayList<>();
        for(int i=0;i<count;i++){
            int item = randomSubject.nextInt(Ids.size()) + 1;
            boolean flag = false;
            for (int j = 0; j <index.size(); j++) {
                int a= index.get(j);
                if(item==a){
                    flag = true;
                }
            }
            if(flag){
                i--;
            }else {
                index.add(item);
            }
        }
        //根据随机生成的数，得到对应试题id
        List<String> randomIds=new ArrayList<>();
        for(int i=0;i< index.size();i++){
            int item= index.get(i)-1;
            String Id=Ids.get(item);
            randomIds.add(Id);
        }
        return randomIds;
    }




}
