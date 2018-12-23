package com.wbg.sums.service.impl;

import com.wbg.sums.dao.FinancialManagementMapper;
import com.wbg.sums.entity.FinancialManagement;
import com.wbg.sums.service.FinancialManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialManagementServiceImpl implements FinancialManagementService {

    @Autowired
    private FinancialManagementMapper financialManagementMapper;

    @Override
    public int deleteByPrimaryKey(Integer fId) {
        return financialManagementMapper.deleteByPrimaryKey(fId);
    }

    @Override
    public BigDecimal selectMoney() {
        return financialManagementMapper.selectMoney();
    }

    @Override
    public int insert(FinancialManagement record) {

        //如果交易余额不大于0
        if(record.getfMoney().compareTo(new BigDecimal(0))<=0){
            return 0;
        }
        //获取数据库交易前余额
        BigDecimal money = financialManagementMapper.selectMoney();
        if(money == null){
            money = new BigDecimal(0);
        } //交易前余额
            record.setfFrontMoney(money);

        //设置交易后余额
        if("支出".equals(record.getfType())){
            //是支出的话，用交易前前去交易金额
                record.setfAfterMoney(money.subtract(record.getfMoney()));
        }else if ("收入".equals(record.getfType())){
            //是收入的话，用交易前加上交易金额
              record.setfAfterMoney(money.add(record.getfMoney()));
        }else{
            return 0;
        }
        return financialManagementMapper.insert(record);
    }
    @Override
    public int count() {
        return financialManagementMapper.count();
    }

    public static void main(String[] args) {
        BigDecimal money = null;
        BigDecimal money2 = new BigDecimal(100.00);
        if(money2==null){
            System.out.println( " asdsad");
        }
        System.out.println(money.compareTo(new BigDecimal(0))<=0);
    }
    @Override
    public FinancialManagement selectByPrimaryKey(Integer fId) {
        return financialManagementMapper.selectByPrimaryKey(fId);
    }

    @Override
    public List<FinancialManagement> selectAll() {
        return financialManagementMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(FinancialManagement record) {
        return financialManagementMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<FinancialManagement> selectGroupBymName() {
        return financialManagementMapper.selectGroupBymName();
    }
}
