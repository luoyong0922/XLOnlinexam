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
        System.out.println(teacCourses);
        if(teaccourseid != null) {
            model.addAttribute("teaccourseId", teaccourseid);
            model.addAttribute("courseName", courseName);
        }
        return "paper/publishPaper";
    }

    /**
     * 教师发布考试标准
     * @param data
     * @return
     */
    @RequestMapping(value = "doPublicPaper",method = RequestMethod.POST)
    @ResponseBody
    public RespResult dopublishPaper(@RequestBody String data){//数据被覆盖
        boolean result = false;
        //teaccourseid,testTime,类型，数量，分值......
        //单选题，多选题，判断题，填空题，计算题，主观题
        List<String> datas = Arrays.asList(data.split(","));
        RespResult respResult = new RespResult();
        if(paperService.getPaperStandard(Long.valueOf(datas.get(0).trim())).size()>0){//已存在记录
            return new RespResult("default","你已发布过该考试！");
        }
        if(datas.get(0).trim() == null || "".equals(datas.get(0).trim()) ||
                "#".equals(datas.get(1).trim())) {//数据不全，教师课程id为空或考试时长为空

            respResult.setCode("default");
            respResult.setMessage("请设置考试时长！");
        }else{
            //记录试题规格
            List<PaperStandard> paperStandards = new ArrayList<>();
            Long teacCourseId = Long.valueOf(datas.get(0).trim());
            Integer type = Integer.valueOf(datas.get(1).trim());
            int amount;
            int value;
            //获取所有题型对象
            for (int i = 2; i < datas.size();) {
                PaperStandard paperStandard = new PaperStandard();
                //设置试题规格属性
                paperStandard.setTeacCourseId(teacCourseId);
                paperStandard.setTestTime(type);
                //从第二项开始，每3项为一种题型的属性
                paperStandard.setTestType(datas.get(i));
                if ("#".equals(datas.get(i+1).trim())) {//默认为0
                    amount = 0;
                }else {
                    amount = Integer.valueOf(datas.get(i+1).trim());
                }
                if ("#".equals(datas.get(i+2).trim())) {//默认为0
                    value = 0;
                }else {
                    value = Integer.valueOf(datas.get(i+2).trim());
                }
                paperStandard.setTestAmount(amount);
                paperStandard.setTestValue(value);
                i = i+3;
                paperStandards.add(paperStandard);
            }
            //遍历集合，新增记录
            for (PaperStandard p: paperStandards) {
                result = paperService.insertIntoPaperStandard(p);
                System.out.println("publishPaperPage"+p);
                if(!result){//新增失败
                    respResult.setCode("default");
                    respResult.setMessage("发布考试失败，请稍后重试！");
                }
            }
        }
        if(result){
            respResult.setCode("success");
            respResult.setMessage("发布考试成功");
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
    public String showPaperStandard(@RequestParam("tI") Long teaccourseId,
                                    @RequestParam("cN") String courseName,
                                    Model model){
        List<PaperStandard> paperStandards= paperService.getPaperStandard(teaccourseId);
        if(paperStandards.size()>0) {
            model.addAttribute("paperStandards", paperStandards);
            model.addAttribute("testTime", paperStandards.get(0).getTestTime());
        }
        model.addAttribute("teaccourseId",teaccourseId);
        model.addAttribute("courseName",courseName);
        return "student/toExam";
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
