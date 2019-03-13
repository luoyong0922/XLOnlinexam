package com.roy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.roy.model.PaperStandard;
import org.junit.Test;

import java.util.List;

public class testJson2Entity {

    @Test
    public void jsonToEntity(){
        String jsonStr = "{\"testType\": \"选择题\",\"testAmount\": \"5\", \"testValue\" : \"2\"}";
        PaperStandard paperStandard = JSON.parseObject(jsonStr,PaperStandard.class);
        System.out.println(paperStandard);
        //  PaperStandard{id=null, testType='选择题', testAmount=5, testValue=2, teacCourseId=null, testTime=null}
    }
    @Test
    public void jsonToArrayEntity(){
        String jsonStr = "{\"PaperStandard\":[{\"testType\": \"单选题\",\"testAmount\": \"5\", \"testValue\" : \"2\"},{\"testType\": \"填空题\",\"testAmount\": \"10\", \"testValue\" : \"1\"},{\"testType\": \"判断题\",\"testAmount\": \"10\", \"testValue\" : \"1\"},{\"testType\": \"多选题\",\"testAmount\": \"10\", \"testValue\" : \"1\"},{\"testType\": \"计算题\",\"testAmount\": \"10\", \"testValue\" : \"1\"},{\"testType\": \"主观题\",\"testAmount\": \"10\", \"testValue\" : \"1\"}]}";
        System.out.println(jsonStr.length());
//        JSONObject jsonObject = JSON.parseObject(jsonStr);
//        JSONArray paperStandard = jsonObject.getJSONArray("PaperStandard");
//        List<PaperStandard> paperStandards = JSON.parseObject(paperStandard.toJSONString(), new TypeReference<List<PaperStandard>>() { });
        List paperStandards = jsonToArrayObject(jsonStr,PaperStandard.class);

        System.out.println(paperStandards);
        // [PaperStandard{id=null, testType='选择题', testAmount=5, testValue=2, teacCourseId=null, testTime=null}, PaperStandard{id=null, testType='填空题', testAmount=10, testValue=1, teacCourseId=null, testTime=null}]
    }

    /**
     * 将数据库表PaperStandard中的json字符串转化为对象集合
     * @param jsonStr
     * @param clazz
     * @return
     */
    private List jsonToArrayObject(String jsonStr, Class clazz){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        String className = clazz.getSimpleName();
        JSONArray jsonArray = jsonObject.getJSONArray(className);
        List parseObjectList = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<List>() { });
        return parseObjectList;
    }
}
