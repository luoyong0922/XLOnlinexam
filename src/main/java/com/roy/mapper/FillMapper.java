package com.roy.mapper;

import com.roy.model.Fill;
import com.roy.model.FillExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

@Repository
public interface FillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    long countByExample(FillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int deleteByExample(FillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int insert(Fill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int insertSelective(Fill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    List<Fill> selectByExample(FillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    Fill selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Fill record, @Param("example") FillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Fill record, @Param("example") FillExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Fill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_fill
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Fill record);
}