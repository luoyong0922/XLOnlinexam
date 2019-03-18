package com.roy.service.serviceImpl;

import com.alibaba.fastjson.JSON;
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
     * 根据 教师课程id ,  testUnit 查询测评标准 （查看测评通知列表）
     * @param teac_course_id
     * @param testUnit
     * @return
     */
    @Override
    public List<PaperStandard> getPaperStandard(Long teac_course_id, Integer testUnit) {
        PaperStandardExample example = new PaperStandardExample();
        PaperStandardExample.Criteria criteria = example.createCriteria();
        if(testUnit != 0){
            criteria.andTestAmountEqualTo(testUnit);
        }
        criteria.andTeacCourseIdEqualTo(teac_course_id);
        List<PaperStandard> paperStandards = paperStandardDao.selectByExample(example);
        return paperStandards;
    }

    /**
     * 查看测评详情
     * @param id paperStandardId
     * @return
     */
    @Override
    public Map getPaperStandardById(Long id) {
        PaperStandard paperStandard = paperStandardDao.selectByPrimaryKey(id);
        List<PaperStandard> testType = jsonToArrayObject(paperStandard.getTestType(),PaperStandard.class);
        Map map = new HashMap();
        map.put("testAmount",paperStandard.getTestAmount());
        map.put("testValue",paperStandard.getTestValue());
        map.put("testTime",paperStandard.getTestTime());
        map.put("tcId",paperStandard.getTeacCourseId());
        map.put("id",paperStandard.getId());
        for (int i = 0; i < testType.size(); i++) {
            PaperStandard  standard = testType.get(i);
            String type = standard.getTestType();
            switch (type){
                case "单选题" :
                    map.put("selCount", standard.getTestAmount());
                    map.put("selVal", standard.getTestValue());
                    break;
                case "填空题":
                    map.put("fillCount", standard.getTestAmount());
                    map.put("fillVal", standard.getTestValue());
                    break;
                case "多选题":
                    map.put("mutilCount", standard.getTestAmount());
                    map.put("mutilVal", standard.getTestValue());
                    break;
                case "判断题":
                    map.put("judgeCount", standard.getTestAmount());
                    map.put("judgeVal", standard.getTestValue());
                    break;
                case "计算题":
                    map.put("calculateCount", standard.getTestAmount());
                    map.put("calculateVal", standard.getTestValue());
                    break;
                case "主观题":
                    map.put("subjectCount", standard.getTestAmount());
                    map.put("subjectVal", standard.getTestValue());
                    break;
                default:break;
            }
        }
        return map;
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
     * 根据 paperId 查询 Paper 信息
     * @param paperId
     * @return
     */
    @Override
    public Paper getPaperById(Long paperId) {
        Paper paper = paperDao.selectByPrimaryKey(paperId);
        return paper;
    }

    /**
     * 将数据库表PaperStandard中的json字符串转化为对象集合
     * @param jsonStr
     * @param clazz
     * @return
     */
    private List jsonToArrayObject(String jsonStr, Class clazz){
        String className = clazz.getSimpleName();
        List objectList = JSON.parseArray(JSON.parseObject(jsonStr).getString(className), clazz);
        return objectList;
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

    /**
     * 根据 examId, testUnit 去获取相对应的题型的集合
     * @param examId 试题类型
     * @param testUnit 所属单元
     * @return list 试题集合
     */
    private List getQuestionsByExamId(int examId, String testUnit) {
        if(examId == 1){
            if(!"0".equals(testUnit)){
                MultiSelectExample example=new MultiSelectExample();
                MultiSelectExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<MultiSelect> allMultiSelects=multiSelectDao.selectByExample(example);
                return allMultiSelects;
            }
            List<MultiSelect> allMultiSelects = multiSelectDao.selectByExample(null);
            return allMultiSelects;
        }else if(examId==2){
            if(!"0".equals(testUnit)){
                SelectExample example=new SelectExample();
                SelectExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<Select> allSelects=selectDao.selectByExample(example);
                return allSelects;
            }
            List<Select> allSelects=selectDao.selectByExample(null);
            return allSelects;
        }else if(examId==3){
            if(!"0".equals(testUnit)){
                JudgeExample example=new JudgeExample();
                JudgeExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<Judge> allJudges=judgeDao.selectByExample(example);
                return allJudges;
            }
            List<Judge> allJudges=judgeDao.selectByExample(null);
            return allJudges;
        }else if(examId==4){
            if(!"0".equals(testUnit)){
                FillExample example=new FillExample();
                FillExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<Fill> allFills=fillDao.selectByExample(example);
                return allFills;
            }
            List<Fill> allFills=fillDao.selectByExample(null);
            return allFills;
        }else if(examId==5){
            if(!"0".equals(testUnit)){
                CalculateExample example=new CalculateExample();
                CalculateExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<Calculate> allCalculates=calculateDao.selectByExample(example);
                return allCalculates;
            }
            List<Calculate> allCalculates=calculateDao.selectByExample(null);
            return allCalculates;
        }else if(examId==6){
            if(!"0".equals(testUnit)){
                SubjectExample example=new SubjectExample();
                SubjectExample.Criteria criteria=example.createCriteria();
                criteria.andDificultEqualTo(testUnit);
                List<Subject> allSubjects=subjectDao.selectByExample(example);
                return allSubjects;
            }
            List<Subject> allSubjects=subjectDao.selectByExample(null);
            return allSubjects;
        }
        return null;
    }

    //得到所有的AdminViewQuestion
    private List<AdminViewQuestion> getAllAdminViewQuestions(Long teaccourseId, String testUnit){
        //所有题目
        List<AdminViewQuestion> allAdminViewQuestions=new ArrayList<>();
        //所有多选题
        List<MultiSelect> allMultiSelects = this.getQuestionsByExamId(1, testUnit);
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
        List<Select> allSelects=this.getQuestionsByExamId(2, testUnit);
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
        List<Judge> allJudges=this.getQuestionsByExamId(3, testUnit);
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
        List<Fill> allFills=this.getQuestionsByExamId(4, testUnit);
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
        List<Calculate> allCalculates=this.getQuestionsByExamId(5, testUnit);
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
        List<Subject> allSubjects=this.getQuestionsByExamId(6, testUnit);
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
    public List<TeacCourse> TeacherCourseQuestions(Long teacId, String testUnit) {

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
            Map<String, List> questionMap = getQuestionsMap(teacCourse1.getId(), testUnit);
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
    public Map<String,List> getQuestionsMap(Long teacCourseId,String testUnit){
        //存放所有题目
        Map<String,List> questionMap=new HashMap<>();
        //根据teac_course_id去查找对应的题目
        //单选题
        SelectExample selexample=new SelectExample();
        SelectExample.Criteria selcriteria=selexample.createCriteria();
        selcriteria.andTeacCourseIdEqualTo(teacCourseId);
        if(!testUnit.equals("0")){
            selcriteria.andDificultEqualTo(testUnit);
        }
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
        if(!testUnit.equals("0")){
            mulcriteria.andDificultEqualTo(testUnit);
        }
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
        if(!testUnit.equals("0")){
            judgecriteria.andDificultEqualTo(testUnit);
        }
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
        if(!testUnit.equals("0")){
            fillcriteria.andDificultEqualTo(testUnit);
        }
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
        if(!testUnit.equals("0")){
            calculatecriteria.andDificultEqualTo(testUnit);
        }
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
        if(!testUnit.equals("0")){
            subjectcriteria.andDificultEqualTo(testUnit);
        }
        List<Subject> allsubjects=subjectDao.selectByExample(subjectExample);
        if(allsubjects!=null) {
            questionMap.put("allsubjects",allsubjects);
        }else {
            questionMap.put("allsubjects",new ArrayList());
        }
        return questionMap;
    }

    /**
     * 老师根据courseName，questionType，teacCourseId, dificult查看题库
     * @param pageIndex
     * @param courseName 课程名称
     * @param questionType 试题类型
     * @param id 老师Id
     * @param teaccourseId 教师课程Id
     * @param dificult 所属单元
     * @return
     */
    @Override
    public PageInfo SearchTeacherViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String questionType,Long id, Long teaccourseId, String dificult) {
        List<TeacCourse> teacCourses = new ArrayList<>();
        //老师查看题库
        teacCourses = this.TeacherCourseQuestions(id, dificult);
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
    public PageInfo SearchAdminViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String questionType, Long teaccourseId, String testUnit) {
        List<AdminViewQuestion> adminViewQuestions = new ArrayList<>();
        //管理员查看题库
        adminViewQuestions  = this.getAllAdminViewQuestions(teaccourseId, testUnit);

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
     * @param paperStandardId
     * @param stuid
     * @param model
     * @return
     */
    @Override
    public Model createPaper(@RequestParam("standardId") Long paperStandardId,
                             @RequestParam("stuid") Long stuid,
                             Model model) {

        Map paperStandardMap = getPaperStandardById(paperStandardId);
        selCount = (int) paperStandardMap.get("selCount");
        fillCount = (int) paperStandardMap.get("fillCount");
        mutilCount = (int) paperStandardMap.get("mutilCount");
        judgeCount = (int) paperStandardMap.get("judgeCount");
        calculateCount = (int) paperStandardMap.get("calculateCount");
        subjectCount = (int) paperStandardMap.get("subjectCount");
        Map map = new HashMap();
        String unit = String.valueOf(paperStandardMap.get("testAmount"));
        Long teaccourseid = getTeacCourseIdByPaperStandardId(paperStandardId);
        if (selCount > 0) {
            map = getSelect(teaccourseid, selCount, unit);
            selIds = myTrim(map.get("randomselectIds").toString());
            model.addAttribute("selects", map.get("selects"));
        }

        if (fillCount > 0) {
            map = getFill(teaccourseid, fillCount, unit);
            fillIds = myTrim(map.get("randomFillIds").toString());
            model.addAttribute("fills", map.get("fills"));
        }

        if (mutilCount > 0) {
            map = getMutilSelect(teaccourseid, mutilCount, unit);
            mutilIds = myTrim(map.get("randomMutilIds").toString());
            model.addAttribute("multiSelects", map.get("multiSelects"));
        }

        if (judgeCount > 0) {
            map = getJudge(teaccourseid, judgeCount, unit);
            judgeIds = myTrim(map.get("randomJudgeIds").toString());
            model.addAttribute("judges", map.get("judges"));
        }

        if (calculateCount > 0) {
            map = getCalculate(teaccourseid, calculateCount, unit);
            calculateIds = myTrim(map.get("randomCalculateIds").toString());
            model.addAttribute("calculates", map.get("calculates"));
        }

        if (subjectCount > 0) {
            map = getSubject(teaccourseid, subjectCount, unit);
            subjectIds = myTrim(map.get("randomSubjectIds").toString());
            model.addAttribute("subjects", map.get("subjects"));
        }

        Paper paper = new Paper();
        paper.setTeacCourseId(paperStandardId);
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
            Long paperId = searchPaperByIds(null, paperStandardId, stuid).getId();
            System.out.println("试卷生成成功");
            model.addAttribute("paperid", paperId);
            model.addAttribute("stuId", stuid);
            model.addAttribute("teacCourseId", paperStandardId);
            System.out.println("CreatePaper():" + model);
            return model;
        } else {
            return null;
        }

    }

    /**
     * 根据paperStandardId 查询 teacCourseId
     * @param standardId
     * @return
     */
    private Long getTeacCourseIdByPaperStandardId(Long standardId){
        PaperStandard paperStandard = paperStandardDao.selectByPrimaryKey(standardId);
        return paperStandard != null ? paperStandard.getTeacCourseId() : 1L;
    }
    /**
     * 更新试卷信息
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
        model.addAttribute("standard",getPaperStandardById(paper.getTeacCourseId()));
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
        return model;
    }

    /**
     * 根据试卷id查询学生成绩
     * @param paperId
     * @return
     */
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
        String testUnit = "0";
        int size = getQuestionsByExamId(2, testUnit).size();
        if(size > 0) {
            Select obj = (Select) getQuestionsByExamId(2, testUnit).get(size - 1);
            select.setSelectionId("sel-" + (obj.getId() + 1));
        }else {
            select.setSelectionId("sel-1");
        }
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
        String testUnit = "0";
        int size = getQuestionsByExamId(1, testUnit).size();
        if(size > 0) {
            MultiSelect obj = (MultiSelect) getQuestionsByExamId(1, testUnit).get(size - 1);
            multiSelect.setMultiSelectionId("ms-" + (obj.getId() + 1));
        }else {
            multiSelect.setMultiSelectionId("ms-1");
        }
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
        String testUnit = fill.getDificult();
        int size = getQuestionsByExamId(4, "0").size();
        if(size > 0) {
            Fill obj = (Fill) getQuestionsByExamId(4, "0").get(size - 1);
            fill.setFillId("f-" + (obj.getId() + 1));
        }else {
            fill.setFillId("f-1");
        }
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
        String testUnit = judge.getDificult();
        int size = getQuestionsByExamId(3, "0").size();
        if(size >0) {
            Judge obj = (Judge) getQuestionsByExamId(3, "0").get(size - 1);
            judge.setJudgeId("j-" + (obj.getId() + 1));
        }else {
            judge.setJudgeId("j-1");
        }
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
        String testUnit = calculate.getDificult();
        int size = getQuestionsByExamId(5, "0").size();
        if(size > 0) {
            Calculate calculate1 = (Calculate) getQuestionsByExamId(5, "0").get(size - 1);
            calculate.setCalculateId("c-" + (calculate1.getId() + 1));
        }else {
            calculate.setCalculateId("c-1");
        }
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
        String testUnit = subject.getDificult();
        int size = getQuestionsByExamId(6, "0").size();
        if(size > 0){
            Subject obj = (Subject) getQuestionsByExamId(6, "0").get(size-1);
            subject.setSubjectId("sub-"+(obj.getId()+1));
        }else {
            subject.setSubjectId("sub-1");
        }

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
        Map paperStandardMap= getPaperStandardById(paper.getTeacCourseId());
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


    //根据教师课程id查询所有sel_ids
    @Override
    public List<String> selectAllSelectsIds(Long teaccourseid, String unit) {
        SelectExample example=new SelectExample();
        SelectExample.Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Select>  selects=selectDao.selectByExample(example);
        List<String> selectIds=new ArrayList<>();
        for(int i=0;i<selects.size();i++){
            String id= selects.get(i).getSelectionId();
            selectIds.add(id);
        }
        return selectIds;
    }
    //根据sel_id查询试题
    @Override
    public Select getSelectBysel_id(String selId) {
        SelectExample example=new SelectExample();
        SelectExample.Criteria criteria=example.createCriteria();
        criteria.andSelectionIdEqualTo(selId);
        Select select=selectDao.selectByExample(example).get(0);
        return select;
    }
    //根据教师课程id查询所有多项选择题的mutil_id
    @Override
    public List<String> selectAllMutilSelectsIds(Long teaccourseid, String unit) {
        MultiSelectExample example=new MultiSelectExample();
        MultiSelectExample .Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<MultiSelect> multiSelects=multiSelectDao.selectByExample(example);

        List<String> multiselectIds=new ArrayList<>();
        for(int i=0;i<multiSelects.size();i++){
            String id= multiSelects.get(i).getMultiSelectionId();
            multiselectIds.add(id);
        }
        return multiselectIds;
    }
    //根据id 查询 多项选择题题目
    @Override
    public MultiSelect getMultiSelectsByMultiId(String multiId) {
        MultiSelectExample example=new MultiSelectExample();
        MultiSelectExample .Criteria criteria=example.createCriteria();
        criteria.andMultiSelectionIdEqualTo(multiId);
        MultiSelect multiSelect=multiSelectDao.selectByExample(example).get(0);
        return multiSelect;
    }
    //根据教师课程id查询所有填空题的fill_id
    @Override
    public List<String> selectAllFillIds(Long teaccourseid, String unit) {
        FillExample example=new FillExample();
        FillExample.Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Fill> fills=fillDao.selectByExample(example);
        List<String> fillIds=new ArrayList<>();
        for(int i=0;i<fills.size();i++){
            String fillId=fills.get(i).getFillId();
            fillIds.add(fillId);
        }
        return fillIds;
    }
    //根据fill_id查询填空题信息
    @Override
    public Fill getFillByFillId(String fillId) {
        FillExample example=new FillExample();
        FillExample.Criteria criteria=example.createCriteria();
        criteria.andFillIdEqualTo(fillId);
        Fill fill=fillDao.selectByExample(example).get(0);
        return fill;
    }
    //根据教师课程id查询所有计算题的calculate_id

    @Override
    public List<String> selectAllCalculateIds(Long teaccourseid, String unit) {
        CalculateExample example=new  CalculateExample();
        CalculateExample.Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Calculate> calculates=calculateDao.selectByExample(example);
        List<String> calculateIds=new ArrayList<>();
        for(int i=0;i<calculates.size();i++){
            String calculateId=calculates.get(i).getCalculateId();
            calculateIds.add(calculateId);
        }
        return calculateIds;
    }
    //根据calculate_id查询计算题信息
    @Override
    public Calculate getCalculateByCalculateId(String calculateId) {
        CalculateExample example=new CalculateExample();
        CalculateExample.Criteria criteria=example.createCriteria();
        criteria.andCalculateIdEqualTo(calculateId);
        Calculate calculate=calculateDao.selectByExample(example).get(0);
        return calculate;
    }
    //根据教师课程id查询所有判断题的judgeId
    @Override
    public List<String> selectAllJudegeIds(Long teaccourseid, String unit) {
        JudgeExample example=new JudgeExample();
        JudgeExample.Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Judge> judges=judgeDao.selectByExample(example);
        List<String> judgeIds=new ArrayList<>();
        for(int i=0;i<judges.size();i++){
            String judgeId=judges.get(i).getJudgeId();
            judgeIds.add(judgeId);
        }
        return judgeIds;
    }
    //根据judgeId查询判断题信息
    @Override
    public Judge getJudgeByJudgeId(String judgeId) {
        JudgeExample example=new JudgeExample();
        JudgeExample.Criteria criteria=example.createCriteria();
        criteria.andJudgeIdEqualTo(judgeId);
        Judge judge= judgeDao.selectByExample(example).get(0);
        return judge;
    }
    //根据教师课程id查询所有subject_id
    @Override
    public List<String> selectAllSubjectIds(Long teaccourseid, String unit) {
        SubjectExample example=new SubjectExample();
        SubjectExample.Criteria criteria=example.createCriteria();
        if(unit != null){
            criteria.andDificultEqualTo(unit);
        }
        criteria.andTeacCourseIdEqualTo(teaccourseid);
        List<Subject> subjects=subjectDao.selectByExample(example);
        List<String> subjectIds=new ArrayList<>();
        for(int i=0;i<subjects.size();i++){
            String subjectId=subjects.get(i).getSubjectId();
            subjectIds.add(subjectId);
        }
        return subjectIds;
    }
    //根据subject_id查询subject题目信息
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
     * 随机抽取选择题
     * @param teaccourseid
     * @return
     */
    public Map getSelect(@RequestParam("teaccourseid") Long teaccourseid, int count, String unit){
        Map map = new HashMap();
        //最终随机生成的单选题的sel_id的集合
        List<Select> selects = new ArrayList<>();
        //对应课程的所有单选题的sek-id集合
        List<String> selectIds = selectAllSelectsIds(teaccourseid, unit);

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
     * 随机抽取多选题
     * @param teaccourseid
     * @param count
     * @return
     */

    public Map getMutilSelect(@RequestParam("teaccourseid") Long teaccourseid,int count, String unit){
        Map map = new HashMap();
        //多项选择题
        //对应课程的所有多选题的multi-id集合
        List<String> multiselectIds= selectAllMutilSelectsIds(teaccourseid,unit);
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
     * 随机抽取填空题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getFill(@RequestParam("teaccourseid") Long teaccourseid,int count, String unit){
        Map map = new HashMap();
        //填空题
        //对应课程的所有填空题的fill-id集合
        List<String> fillIds= selectAllFillIds(teaccourseid, unit);
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
     * 随机抽取计算题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getCalculate(@RequestParam("teaccourseid") Long teaccourseid,int count, String unit){
        Map map = new HashMap();
        //计算题
        List<String> calculateIds= selectAllCalculateIds(teaccourseid , unit);
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
     * 随机抽取判断题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getJudge(@RequestParam("teaccourseid") Long teaccourseid,int count, String unit){
        Map map = new HashMap();

        // 判断题
        List<String> judgeIds = selectAllJudegeIds(teaccourseid , unit);
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
     * 随机抽取主观题
     * @param teaccourseid
     * @param count
     * @return
     */
    public Map getSubject(@RequestParam("teaccourseid") Long teaccourseid, int count, String unit){
        Map map = new HashMap();
        //subject题
        List<String> subjectIds= selectAllSubjectIds(teaccourseid , unit);
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
