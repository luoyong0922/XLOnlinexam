package com.roy.mapper;

import com.roy.model.Select;
import com.roy.model.SelectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    long countByExample(SelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int deleteByExample(SelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int insert(Select record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int insertSelective(Select record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    List<Select> selectByExample(SelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    Select selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Select record, @Param("example") SelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Select record, @Param("example") SelectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Select record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_select
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Select record);
}