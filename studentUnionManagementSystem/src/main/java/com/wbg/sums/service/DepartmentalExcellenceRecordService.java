package com.wbg.sums.service;

import com.wbg.sums.entity.DepartmentalExcellenceRecord;

import java.util.List;

public interface DepartmentalExcellenceRecordService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departmental_excellence_record
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer dId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departmental_excellence_record
     *
     * @mbg.generated
     */
    int insert(DepartmentalExcellenceRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departmental_excellence_record
     *
     * @mbg.generated
     */
    DepartmentalExcellenceRecord selectByPrimaryKey(Integer dId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departmental_excellence_record
     *
     * @mbg.generated
     */
    List<DepartmentalExcellenceRecord> selectAll();
    int count();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table departmental_excellence_record
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DepartmentalExcellenceRecord record);
    //根据部门、时间查询  List<DepartmentalExcellenceRecord> selectDidADate(did,date);
}