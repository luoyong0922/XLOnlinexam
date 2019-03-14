package com.roy.controller;
import com.github.pagehelper.PageInfo;
import com.roy.model.*;
import com.roy.service.CourseService;
import com.roy.service.PaperService;
import com.roy.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paperController")
public class PaperController {
    @Resource
    private PaperService paperService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;
    /**
     * 教师跳转到考试发布界面
     * @return
     */
    @RequestMapping("toPublicPaper")
    public String toPublicPaper(@RequestParam(value = "id",required = false) Long teaccourseid,
                                @RequestParam(value = "courseName",required = false) String courseName,
                                Model model, HttpSession session){
        Long id = (Long) session.getAttribute("id");
        List<TeacCourse> teacCourses = courseService.getTeacCoursesMessage(id,0L);
        model.addAttribute("teacCourses", teacCourses);
        model.addAttribute("role","teacher");
        if(teaccourseid != null) {
            model.addAttribute("teaccourseId", teaccourseid);
            model.addAttribute("courseName", courseName);
        }
        return "paper/publishPaper";
    }

    /**
     * 教师发布考试标准
     * @param
     * @return
     */
    @RequestMapping(value = "doPublicPaper",method = RequestMethod.POST)
    @ResponseBody
    public RespResult doPublishPaper(@RequestBody PaperStandard standard){
        //单选题，多选题，判断题，填空题，计算题，主观题
        RespResult respResult = new RespResult();
        boolean result = paperService.insertIntoPaperStandard(standard);
        if(result){
            respResult.setCode("success");
            respResult.setMessage("发布考试成功");
        }else {//新增失败
            respResult.setCode("default");
            respResult.setMessage("发布考试失败，请稍后重试！");
        }
        return respResult;
    }

    /**
     * 学生查看考试通知
     * @param teaccourseId
     * @param courseName
     * @param model
     * @return
     */
    //得到试卷的规格
    @RequestMapping("getPaperStandard")
    public String getPaperStandard(@RequestParam("tI") Long teaccourseId,
                                    @RequestParam("cN") String courseName,
                                    Model model){
        List<PaperStandard> paperStandards= paperService.getPaperStandard(teaccourseId);
        if(paperStandards.size()>0) {
            model.addAttribute("paperStandards", paperStandards);
        }
        model.addAttribute("teaccourseId",teaccourseId);
        model.addAttribute("courseName",courseName);
        return "student/toExam";
    }

    /**
     * 学生查看考试通知详情
     * @param teaccourseId
     * @param courseName
     * @param model
     * @return
     */
    @RequestMapping("showPaperStandard")
    public String showPaperStandard(@RequestParam("cN") String courseName,
                                    @RequestParam("pI") Long paperStandardId,
                                    Model model){
        Map paperStandards= paperService.getPaperStandardById(paperStandardId);
        if(paperStandards.size()>0) {
            model.addAttribute("standardMap", paperStandards);
        }
        model.addAttribute("courseName",courseName);
        return "paper/showPaperStandard";
    }


    /**
     * 学生开始考试或查看考试详情
     * @param teaccourseid
     * @param model
     * @return
     */
    @RequestMapping("intoTest/{testTime}")
    public String ToTest(@RequestParam("teaccourseid") Long teaccourseid,
                         @RequestParam("cN") String courseName,
                         @PathVariable Integer testTime,
                         Model model, HttpSession session){
        Long stuid = (Long) session.getAttribute("id");
        model.addAttribute("courseName",courseName);
        model.addAttribute("testTime",testTime);
        if(teaccourseid != null && !"".equals(teaccourseid) && stuid != null){
            List<Paper> papers = paperService.getPaperByIds(teaccourseid,stuid);
            if(papers.size() == 0) {//不存在该学生的考试记录
                model = paperService.createPaper(teaccourseid, stuid, model);
            }else {
                return "redirect:/paperController/toMarking/"+papers.get(0).getId();
            }
        }
        model.addAttribute(model);
        return "paper/paper";
    }

    /**
     * 根据主键更新试卷（学生交卷）
     * @param paper
     * @return
     */
    @RequestMapping("finishTest/{time}")
    public String finishTest(Paper paper,@PathVariable Integer time){
        boolean result = paperService.updatePaper(paper);
        if(result){
            boolean result2 = paperService.addStuScoreSelective(paper,time);
            if(result2) {
                System.out.println("交卷*********成功");
                return "redirect:/studentController/toStudentIndex";
            }
        }
        System.out.println("交卷*********失败");
        return "redirect:/studentController/toStudentIndex";


    }
    /**
     * 跳转到批阅试卷界面
     * @param id 试卷ID
     * @param model
     * @return
     */
    @RequestMapping("toMarking/{paperId}")
    public String toMarking(@PathVariable("paperId")Long id,
                            @RequestParam("courseName")String courseName,
                            @RequestParam(value = "testTime",required = false,defaultValue = "120")Integer testTime,Model model,HttpSession session){
        String role = (String) session.getAttribute("role");
        model = paperService.toMarking(id,-1,model,role);
        model.addAttribute("courseName",courseName);
        model.addAttribute("testTime",testTime);
        model.addAttribute(model);
        System.out.println("查看试卷详情:"+model);
        if("teacher".equals(role)){//老师查看试卷详情
            return "paper/paper3";
        }else{//学生或管理员查看试卷详情
            return "paper/paper2";
        }
    }
    /**
     * 批阅试卷
     * @param id 试卷ID
     * @param score6 主观题成绩
     */
    @RequestMapping("doMarking/{score6}")
    public String doMarking(@RequestParam("id") Long id,
                            @PathVariable("score6") int score6,
                            Model model,HttpSession session){
        String role = (String) session.getAttribute("role");
        paperService.toMarking(id,score6,model,role);
        return "redirect:/teacherController/getAllstuScore";

    }




    /**
     * 老师或管理员查看题库
     * @param session
     * @param pageIndex
     * @param courseName
     * @param questionType
     * @param teacCourseId
     * @param model
     * @return
     */
    @RequestMapping("getMyQuestions")
    public String showQuestions(HttpSession session,
                                @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                                @RequestParam(value = "courseName",required =false,defaultValue ="") String courseName,
                                @RequestParam(value = "questionType",required =false,defaultValue ="1") Integer questionType,
                                @RequestParam(value = "teacCourseId",required =false,defaultValue ="0") Long teacCourseId,
                                Model model){
        //题型： 1：多选题，2：单选题，3:判断题，4：填空题，5：计算题，6：主观题
        String type = "多选题";
        if(questionType == 1){

        }else if(questionType == 2){
            type = "单选题";
        }else if(questionType == 3){
            type = "判断题";
        }else if(questionType == 4){
            type = "填空题";
        }else if(questionType == 5){
            type = "计算题";
        }else if(questionType == 6){
            type = "主观题";
        }
        System.out.println("------------"+type);
        PageInfo pageInfo = new PageInfo();
        List<TeacCourse> teacCourses = new ArrayList<>();
        Long id = (Long) session.getAttribute("id");
        String role = (String) session.getAttribute("role");
        System.out.println(role);
        if("teacher".equals(role)) {//教师查询题库
            teacCourses = teacherService.getCourseName(id);
            pageInfo = paperService.SearchTeacherViewQuestionByCNameAndQType(pageIndex,courseName,type,id,teacCourseId);
        }else {//管理员查询题库
            teacCourses = teacherService.getAllCourseName();
            pageInfo = paperService.SearchAdminViewQuestionByCNameAndQType(pageIndex,courseName,type,teacCourseId);
        }
        if(teacCourseId == 0 && teacCourses.size()>0) {
            teacCourseId = teacCourses.get(0).getId();
        }
        model.addAttribute("teacCourseId",teacCourseId);
        model.addAttribute("teacCourses",teacCourses);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("courseName",courseName);
        model.addAttribute("questionType",questionType);
        System.out.println(pageInfo);
        if("admin".equals(role)) {//管理员
            return "admin/showQuestion";
        }else {//教师
            return "paper/addQuestion";
        }
    }

    //根据题目类型和题目去查题目
    @RequestMapping(value = "getQuestionAjax",method = RequestMethod.POST)
    @ResponseBody
    public Object GetQuestionAjax(@RequestBody AdminViewQuestion adminViewQuestion){

        Object object= paperService.SearchQuestionByTitleAndType(adminViewQuestion.getId(),adminViewQuestion.getQuestionType());
        if(object!=null){
            System.out.println("查询成功！");
            return object;
        }
        return null;
    }
    //根据题目类型和题目id去删除题目
    @RequestMapping(value = "deleteQuestion",method = RequestMethod.POST)
    @ResponseBody
    public RespResult deleteQuestion(@RequestBody AdminViewQuestion adminViewQuestion){

        List<Long> ids = new ArrayList<>();
        ids.add(adminViewQuestion.getId());
        int result= paperService.deleteQuestion(ids,adminViewQuestion.getQuestionType());
        if(result>0){
            return new RespResult("success","删除成功！");

        }else {
            return new RespResult("fail","删除失败！");
        }

    }

}
