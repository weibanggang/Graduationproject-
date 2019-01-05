package com.wbg.sums.service.impl;

import com.wbg.sums.dao.DepartmentalExcellenceRecordMapper;
import com.wbg.sums.entity.DepartmentalExcellenceRecord;
import com.wbg.sums.entity.Notic;
import com.wbg.sums.service.DepartmentalExcellenceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentalExcellenceRecordServiceImpl implements DepartmentalExcellenceRecordService {
   @Autowired
   private DepartmentalExcellenceRecordMapper departmentalExcellenceRecordMapper;
    @Override
    public int deleteByPrimaryKey(Integer dId) {
        return departmentalExcellenceRecordMapper.deleteByPrimaryKey(dId);
    }

    @Override
    public List<DepartmentalExcellenceRecord> selects(String dName, String beforeDate, String afterDate) {
        return departmentalExcellenceRecordMapper.selects(dName, beforeDate, afterDate);
    }

    @Override
    public int counts(String dName, String beforeDate, String afterDate) {
        return departmentalExcellenceRecordMapper.counts(dName, beforeDate, afterDate);
    }


    @Override
    public int insert(DepartmentalExcellenceRecord record) {
        return departmentalExcellenceRecordMapper.insert(record);
    }

    @Override
    public DepartmentalExcellenceRecord selectByPrimaryKey(Integer dId) {
        return departmentalExcellenceRecordMapper.selectByPrimaryKey(dId);
    }

    @Override
    public List<DepartmentalExcellenceRecord> selectAll() {
        return departmentalExcellenceRecordMapper.selectAll();
    }

    @Override
    public int count() {
        return departmentalExcellenceRecordMapper.count();
    }

    @Override
    public int updateByPrimaryKey(DepartmentalExcellenceRecord record) {
        return departmentalExcellenceRecordMapper.updateByPrimaryKey(record);
    }
}
