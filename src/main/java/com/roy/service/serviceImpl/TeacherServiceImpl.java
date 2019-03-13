package com.roy.service.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.roy.mapper.*;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.StudentService;
import com.roy.service.TeacherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherDao;

    @Resource
    private StuScoreMapper stuScoreDao;
    @Resource
    private StuCourseMapper stuCourseDao;
    @Resource
    private CourseService courseService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacCourseMapper teacCourseDao;
    @Resource
    private PaperMapper paperDao;

    @Resource
    private HomeWorkMapper homeWorkDao;
    /**
     * 根据id更新老师信息
     * @param teacher
     * @return
     */
    @Override
    public boolean updateTeacherById(Teacher teacher) {
        if(teacher.getTeacPassword() != null && teacher.getTeacPassword().length() < 60){
            teacher.setTeacPassword(new BCryptPasswordEncoder().encode(teacher.getTeacPassword()));
        }
        int result = teacherDao.updateByPrimaryKeySelective(teacher);
        return result>0;
    }

    //根据老师id查询老师信息
    @Override
    public Teacher getTeacByTeacId(Long teacId){
        Teacher teacher=teacherDao.selectByPrimaryKey(teacId);
        return teacher;
    }
    //根据paperId查询试卷信息
    private Paper getPaperById(Long id){
        Paper paper = paperDao.selectByPrimaryKey(id);
        return paper;
    }
    //根据教师课程id查询学生成绩对象
    @Override
    public List<StuScore> getAllStuScore(Long teacCourseId) {
        StuScoreExample example=new StuScoreExample();
        StuScoreExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teacCourseId);
        List<StuScore> stuScores=stuScoreDao.selectByExample(example);
        return stuScores;
    }

    //遍历teacId对应的学生成绩对象,查询所需要的stuScore对象的集合
    @Override
    public List<StuScore> getAllNeedStuScore(Long teacCourseId){
        List<StuScore> stuScores=this.getAllStuScore(teacCourseId);
        System.out.println("stuScores"+stuScores);
        List<StuScore> stuScores1=new ArrayList<>();
        for(int i=0;i<stuScores.size();i++){
            StuScore stuScore=stuScores.get(i);
            String courseName= getCourseNameByteacCourseId( stuScore.getTeacCourseId()); //根据教师课程id去查询课程id--》课程名称
            System.out.println("courseName"+courseName);
            String stuName= getStuNameBystuId( stuScore.getStuId());  //根据学生id去查询学生姓名
            System.out.println("stuName"+stuName);
            stuScore.setCourseName(courseName);
            stuScore.setStuName(stuName);
            Integer state = getPaperById(stuScore.getPaperId()).getPapeState();
            stuScore.setPaperState(state);
            stuScores1.add(stuScore);
        }
        System.out.println("getAllNeedStuScore:"+stuScores1);
        return stuScores1;
    }
    /**
     * 根据学生id去查询学生姓名
     */
    public String getStuNameBystuId(Long id){
        Student student= studentService.getStudentById(id);
        String stuName=student.getStuName();
        return stuName;
    }
    /**
     * 根据teacCourseId去查询课程名称
     */
    public String getCourseNameByteacCourseId(Long teacCourseId){
        TeacCourse teacCourse = courseService.getTeacCourse(teacCourseId);

        String courseName=courseService.getCourseById(teacCourse.getCourseId()).getCourseName();
        System.out.println("courseName"+courseName);
        return courseName;
    }
    /**
     * 根据teacCourseId去查询老师姓名
     */
    private String getTeacNameByteacCourseId(Long teacCourseId){
        TeacCourse teacCourse = courseService.getTeacCourse(teacCourseId);

        String teacName=teacherDao.selectByPrimaryKey(teacCourse.getCourseId()).getTeacName();
        System.out.println("teacName"+teacName);
        return teacName;
    }
    /**
     * teacId对应课程的成绩的分页信息
     * @param pageIndex
     * @param teacCourseId
     * @return
     */
    @Override
    public PageInfo SearchAllNeedStuScoreByPageHelper(Integer pageIndex, Long teacCourseId) {
        List<StuScore> stuScores=this.getAllNeedStuScore(teacCourseId);
        System.out.println("stuScores++++++"+stuScores);
        // PageHelper.startPage(pageIndex,5);
        PageInfo pageInfo=new PageInfo(stuScores);
        System.out.println("teacId对应课程的成绩的分页信息"+pageInfo);
        return pageInfo;
    }

    /**
     * 学生查询自己的成绩
     * @param pageIndex
     * @param teacCourseId
     * @return
     */
    @Override
    public PageInfo searchStuScore(Integer pageIndex,Long stuId, Long teacCourseId) {
        List<StuScore> stuScores= new ArrayList<>();
        List<StuScore> scores = new ArrayList<>();
        StuScoreExample example = new StuScoreExample();
        StuScoreExample.Criteria criteria = example.createCriteria();
        if(teacCourseId != null){
            criteria.andTeacCourseIdEqualTo(teacCourseId);
        }
        if(stuId != null){
            criteria.andStuIdEqualTo(stuId);
        }
        stuScores = stuScoreDao.selectByExample(example);
        if(stuScores.size()>0) {
            for (int i = 0; i < stuScores.size(); i++) {
                StuScore stuScore = stuScores.get(i);
                Long tcId = stuScore.getTeacCourseId();
                String courseName = getCourseNameByteacCourseId(tcId);
                stuScore.setCourseName(courseName);
                String teacName = getTeacNameByteacCourseId(tcId);
                stuScore.setTeacName(teacName);
                scores.add(stuScore);
            }
        }

        // PageHelper.startPage(pageIndex,5);
        PageInfo pageInfo=new PageInfo(scores);
        System.out.println("学生课程成绩的分页信息"+pageInfo);
        return pageInfo;
    }

    //根据teacId去查询teacCourse中的记录
    List<TeacCourse> getTeacCourseByTeacId(Long teacId){
        TeacCourseExample example=new TeacCourseExample();
        TeacCourseExample.Criteria criteria=example.createCriteria();
        criteria.andTeacIdEqualTo(teacId);
        List<TeacCourse> teacCourses=teacCourseDao.selectByExample(example);
        return teacCourses;
    }
    //课程名称和teacCourseId
    @Override
    public  List<TeacCourse> getCourseName(Long teacId){
        List<TeacCourse> teacCourses = getTeacCourseByTeacId(teacId);
        List<TeacCourse> result = new ArrayList<>();
        for (TeacCourse t: teacCourses) {
            String courseName = getCourseNameByteacCourseId(t.getId());
            t.setCourseName(courseName);
            result.add(t);
        }
        return result;
    }

    //根据teacCourseId去查学生成绩表，查询学生成绩表的记录的集合,遍历集合查询分数为优秀的人数
    @Override
    public Map<String, Integer> getPieData(Long teacCourseId){
        Map<String,Integer> map=new LinkedHashMap<>();
        List<StuScore> stuScores=this.getAllStuScore(teacCourseId);
        List<StuScore>  hStuScores=new ArrayList<>();// <20
        List<StuScore>  gStuScores=new ArrayList<>();// 20~30
        List<StuScore>  fStuScores=new ArrayList<>();// 30~40
        List<StuScore>  eStuScores=new ArrayList<>();// 40~50
        List<StuScore>  dStuScores=new ArrayList<>();// 50~60
        List<StuScore>  cStuScores=new ArrayList<>();// 60~70
        List<StuScore>  bStuScores=new ArrayList<>();// 70~80
        List<StuScore>  aStuScores=new ArrayList<>();// 80~90
        List<StuScore>  sStuScores=new ArrayList<>();// >90
        for(int i=0;i<stuScores.size();i++){
            StuScore stuScore=stuScores.get(i);
            int score=stuScores.get(i).getScore();
            if( score < 20){
                hStuScores.add(stuScore);
            }else if(score>=20&&score<30){
                gStuScores.add(stuScore);
            }else if(score>=30&&score<40){
                fStuScores.add(stuScore);
            }else if(score>=40&&score<50){
                eStuScores.add(stuScore);
            }else if(score>=50&&score<60){
                dStuScores.add(stuScore);
            }else if(score>=60&&score<70){
                cStuScores.add(stuScore);
            }else if(score>=70&&score<80){
                bStuScores.add(stuScore);
            }else if(score>=80&&score<90){
                aStuScores.add(stuScore);
            }else if(score>=90){
                sStuScores.add(stuScore);
            }
        }
        int jige = cStuScores.size()+bStuScores.size()+aStuScores.size()+sStuScores.size();
        int bujige = dStuScores.size()+eStuScores.size()+fStuScores.size()+gStuScores.size()+hStuScores.size();
        Integer quekao = selectCourseAmount(teacCourseId)-jige-bujige;
        map.put("不及格(<60)",bujige);
        map.put("及格(>60)",jige);
        map.put("缺考",quekao);
        map.put("H(20-)",hStuScores.size());
        map.put("G(20~30)",gStuScores.size());
        map.put("F(30~40)",fStuScores.size());
        map.put("E(40~50)",eStuScores.size());
        map.put("D(50~60)",dStuScores.size());
        map.put("C(60~70)",cStuScores.size());
        map.put("B(70~80)",bStuScores.size());
        map.put("A(80~90)",aStuScores.size());
        map.put("S(90+)",sStuScores.size());
        map.put("Absent",quekao);
        return map;
    }
    //插入homeWork记录
    @Override
    public Integer selectCourseAmount(Long teacCourseId){
        StuCourseExample example = new StuCourseExample();
        StuCourseExample.Criteria criteria = example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teacCourseId);

        int result= (int) stuCourseDao.countByExample(example);
        return result;
    }

    //插入homeWork记录
    @Override
    public boolean insertIntoHomeWork(HomeWork homeWork){
        int result=homeWorkDao.insertSelective(homeWork);
        return result>0;
    }

    /**
     * 查询所有教师课程的课程名称
     * @return
     */
    @Override
    public List<TeacCourse> getAllCourseName() {
        List<TeacCourse> teacCourses = teacCourseDao.selectByExample(null);
        List<TeacCourse> result = new ArrayList<>();
        for(TeacCourse t : teacCourses){
            t.setCourseName(getCourseNameByteacCourseId(t.getId()));
            result.add(t);
        }
        return result;
    }

    //根据teacCourseId查询这门课的所有学生的成绩
    @Override
    public List<StuScore> getStuScoreByTeacCourseId(Long teacCourseId) {
        StuScoreExample example=new StuScoreExample();
        StuScoreExample.Criteria criteria=example.createCriteria();
        criteria.andTeacCourseIdEqualTo(teacCourseId);
        List<StuScore> stuScores=stuScoreDao.selectByExample(example);

        return stuScores;
    }
    //根据teacCourseId对应的List<StuScore>去查询名字和成绩的map对象
    @Override
    public Map<String,Integer> getMapNameandScore(Long teacCourseId){
        Map<String,Integer> map=new HashMap<>();
        List<StuScore> stuScores=this.getStuScoreByTeacCourseId(teacCourseId);
        for(int i=0;i<stuScores.size();i++){
            int score=stuScores.get(i).getScore();
            String stuName=getStuNameBystuId(stuScores.get(i).getStuId());
            map.put(stuName,score);
        }
        return map;
    }

    //对map对象进行排序
    @Override
    public List<Map.Entry<String, Integer>> gradeOrder(Map<String,Integer> map){
        List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        System.out.println("--------------排序前--------------");
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).toString();
            System.out.println(id);
        }
        // 排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        System.out.println("--------------排序后--------------");
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, Integer> ent = infoIds.get(i);
            System.out.println(ent.getKey() + "=" + ent.getValue());
        }
        return infoIds;
    }


}
