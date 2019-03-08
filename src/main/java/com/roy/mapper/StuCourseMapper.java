package com.roy.mapper;

import com.roy.model.StuCourse;
import com.roy.model.StuCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StuCourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    long countByExample(StuCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int deleteByExample(StuCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int insert(StuCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int insertSelective(StuCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    List<StuCourse> selectByExample(StuCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    StuCourse selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StuCourse record, @Param("example") StuCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StuCourse record, @Param("example") StuCourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StuCourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stu_course
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StuCourse record);
}