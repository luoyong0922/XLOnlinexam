package com.roy.service;

import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

//@Repository
public interface PaperService {

    //新增考试规格信息
    public boolean insertIntoPaperStandard(PaperStandard p);
    //查询考试规格信息
    public List<PaperStandard> getPaperStandard(Long id);

    //根据教师课程ID和学生ID查询考试记录
    public List<Paper> getPaperByIds(Long teaccourseId,Long stuId);

    //得到所有teacId对应的所有课程的题目,每个老师查看的题目
    List<TeacCourse> TeacherCourseQuestions(Long teacId);

    //根据courseName，questionType分页的信息
    PageInfo SearchAdminViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String questionType, Long teaccourseId);

    //根据题目类型和题目id去查题目
    Object SearchQuestionByTitleAndType(Long title, String questionType);


    ///////////////////////////////////

    //得到对应课程的试卷标准
    Map getPaperStandardMap(Long teac_course_id);

    //老师阅卷
    boolean updatePaper(Paper paper);

    //学生开始考试
    public Model createPaper(@RequestParam("teaccourseid") Long teaccourseid,
                             @RequestParam("stuid") Long stuid, Model model);

    //得到所有这门课程的sel_ids
    List<String> selectAllSelectsIds(Long teaccourseid);

    //根据sel_id去找试题
    Select getSelectBysel_id(String selId);

    //所有多项选择题的mutil_id
    List<String> selectAllMutilSelectsIds(Long teaccourseid);

    //获得多项选择题题目
    MultiSelect getMultiSelectsByMultiId(String multiId);

    //所有填空题的fill_id
    List<String> selectAllFillIds(Long teaccourseid);

    //根据fill_id得到填空题
    Fill getFillByFillId(String fillId);

    List<String> selectAllCalculateIds(Long teaccourseid);

    //根据calculate_id获得计算题
    Calculate getCalculateByCalculateId(String calculateId);

    //所有判断题的judgeId
    List<String> selectAllJudegeIds(Long teaccourseid);

    //根据judgeId取判断题
    Judge getJudgeByJudgeId(String judgeId);

    //所有subject_id
    List<String> selectAllSubjectIds(Long teaccourseid);

    //根据subject_id取subject题
    Subject getSubjectBySubjectId(String subjectId);

    public Map getSelect(@RequestParam("teaccourseid") Long teaccourseid, int count);

    public Map getMutilSelect(@RequestParam("teaccourseid") Long teaccourseid,int count);

    public Map getFill(@RequestParam("teaccourseid") Long teaccourseid,int count);

    public Map getCalculate(@RequestParam("teaccourseid") Long teaccourseid,int count);

    public Map getJudge(@RequestParam("teaccourseid") Long teaccourseid,int count);

    public Map getSubject(@RequestParam("teaccourseid") Long teaccourseid,int count);

    public String myTrim(String s);

    //根据教师课程ID和学生ID查询试卷
    Paper searchPaperByIds(Long id,Long teaccourseid, Long stuid);

    Map doMarking(Long id,int score6);

    public Model toMarking(Long id,int score6, Model model, String role);

    //根据教师课程ID和学生ID查询试卷
    public List getPaper(String ids, int type);

    public boolean addStuScoreSelective(Paper paper,int time);
    //新增单选题
    public boolean addSelect(Select select);
    //新增多选题
    public boolean addMulSel(MultiSelect multiSelect);
    //新增填空题
    public boolean addFill(Fill fill);
    //新增判断题
    public boolean addJudge(Judge judge);
    //新增计算题
    public boolean addCal(Calculate calculate);
    //新增主观题
    public boolean addSubject(Subject subject);

    //根据课程名称，题目类型，教师课程id查询题库
    PageInfo SearchTeacherViewQuestionByCNameAndQType(Integer pageIndex, String courseName, String type, Long id, Long teacCourseId);


    int deleteQuestion(List<Long> questionIds, String questionType);
}
