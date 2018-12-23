package com.wbg.sums.dao;

import com.wbg.sums.entity.FinancialManagement;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialManagementMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table financial_management
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer fId);

    int count();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table financial_management
     *
     * @mbg.generated
     */
    int insert(FinancialManagement record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table financial_management
     *
     * @mbg.generated
     */
    FinancialManagement selectByPrimaryKey(Integer fId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table financial_management
     *
     * @mbg.generated
     */
    //获取当前余额
    BigDecimal selectMoney();
    List<FinancialManagement> selectAll();
    //select m_name from FinancialManagement group by m_name
    //获取操作人
    List<FinancialManagement> selectGroupBymName();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table financial_management
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinancialManagement record);
    //添加交易类型、交易金额、时间、操作人、备注、文件路径(FinancialManagement);

}