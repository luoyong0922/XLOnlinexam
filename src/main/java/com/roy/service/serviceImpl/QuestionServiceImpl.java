package com.roy.service.serviceImpl;

import com.roy.mapper.*;
import com.roy.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl {


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



    /**
     * 根据条件查询单选题
     * @param id
     * @param selId
     * @param tcId
     * @return
     */
    public List<Select> getSelectsSelective(Long id,String selId,Long tcId) {
        List<Select> selects = new ArrayList<>();
        SelectExample example = new SelectExample();
        SelectExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(selId != null){
            criteria.andSelectionIdEqualTo(selId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
       selects = selectDao.selectByExample(example);
        return selects;
    }


    /**
     * 根据条件查询多选题
     * @param id
     * @param mulId
     * @param tcId
     * @return
     */
    public List<MultiSelect> getMultiSelectsSelective(Long id, String mulId, Long tcId) {
        List<MultiSelect> multiSelects = new ArrayList<>();
        MultiSelectExample example = new MultiSelectExample();
        MultiSelectExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(mulId != null){
            criteria.andMultiSelectionIdEqualTo(mulId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
        multiSelects = multiSelectDao.selectByExample(example);
        return multiSelects;
    }
    /**
     * 根据条件查询填空题
     * @param id
     * @param fId
     * @param tcId
     * @return
     */
    public List<Fill> getFillsSelective(Long id, String fId, Long tcId) {
        List<Fill> fills = new ArrayList<>();
        FillExample example = new FillExample();
        FillExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(fId != null){
            criteria.andFillIdEqualTo(fId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
        fills = fillDao.selectByExample(example);
        return fills;
    }
    /**
     * 根据条件查询判断题
     * @param id
     * @param jId
     * @param tcId
     * @return
     */
    public List<Judge> getJudgesSelective(Long id, String jId, Long tcId) {
        List<Judge> judges = new ArrayList<>();
        JudgeExample example = new JudgeExample();
        JudgeExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(jId != null){
            criteria.andJudgeIdEqualTo(jId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
        judges = judgeDao.selectByExample(example);
        return judges;
    }
    /**
     * 根据条件查询计算题
     * @param id
     * @param cId
     * @param tcId
     * @return
     */
    public List<Calculate> getCalculatesSelective(Long id, String cId, Long tcId) {
        List<Calculate> calculates = new ArrayList<>();
        CalculateExample example = new CalculateExample();
        CalculateExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(cId != null){
            criteria.andCalculateIdEqualTo(cId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
        calculates = calculateDao.selectByExample(example);
        return calculates;
    }
    /**
     * 根据条件查询主观题
     * @param id
     * @param sId
     * @param tcId
     * @return
     */
    public List<Subject> getSubjectsSelective(Long id, String sId, Long tcId) {
        List<Subject> subjects = new ArrayList<>();
        SubjectExample example = new SubjectExample();
        SubjectExample.Criteria criteria = example.createCriteria();
        if(id != null){
            criteria.andIdEqualTo(id);
        }
        if(sId != null){
            criteria.andSubjectIdEqualTo(sId);
        }
        if(tcId != null){
            criteria.andTeacCourseIdEqualTo(tcId);
        }
        subjects = subjectDao.selectByExample(example);
        return subjects;
    }
}
