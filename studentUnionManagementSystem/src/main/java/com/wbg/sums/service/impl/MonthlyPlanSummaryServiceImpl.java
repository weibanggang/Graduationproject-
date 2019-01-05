package com.wbg.sums.service.impl;

import com.wbg.sums.dao.MonthlyPlanSummaryMapper;
import com.wbg.sums.entity.MonthlyPlanSummary;
import com.wbg.sums.service.MonthlyPlanSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonthlyPlanSummaryServiceImpl implements MonthlyPlanSummaryService {

    @Autowired
    private MonthlyPlanSummaryMapper monthlyPlanSummaryMapper;
    @Override
    public int deleteByPrimaryKey(Integer mId) {
        return monthlyPlanSummaryMapper.deleteByPrimaryKey(mId);
    }

    @Override
    public int count() {
        return monthlyPlanSummaryMapper.count();
    }

    @Override
    public int insert(MonthlyPlanSummary record) {
        return monthlyPlanSummaryMapper.insert(record);
    }

    @Override
    public MonthlyPlanSummary selectByPrimaryKey(Integer mId) {
        return monthlyPlanSummaryMapper.selectByPrimaryKey(mId);
    }

    @Override
    public List<MonthlyPlanSummary> selectAll() {
        return monthlyPlanSummaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MonthlyPlanSummary record) {
        return monthlyPlanSummaryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MonthlyPlanSummary> selectDate(String beforeDate, String afterDate) {
        return monthlyPlanSummaryMapper.selectDate(beforeDate,afterDate);
    }

    @Override
    public List<MonthlyPlanSummary> selectmName(String mName) {
        return monthlyPlanSummaryMapper.selectmName(mName);
    }

    @Override
    public List<MonthlyPlanSummary> selectdId(int dId) {
        return monthlyPlanSummaryMapper.selectdId(dId);
    }

    @Override
    public int counts(String beforeDate, String afterDate, String mName, int dId) {
        return monthlyPlanSummaryMapper.counts(beforeDate,afterDate,mName,dId);
    }



}
