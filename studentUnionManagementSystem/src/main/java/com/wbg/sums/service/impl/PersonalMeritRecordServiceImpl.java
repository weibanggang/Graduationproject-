package com.wbg.sums.service.impl;

import com.wbg.sums.dao.PersonalMeritRecordMapper;
import com.wbg.sums.entity.DepartmentType;
import com.wbg.sums.entity.PersonalMeritRecord;
import com.wbg.sums.service.DepartmentTypeService;
import com.wbg.sums.service.PersonalMeritRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalMeritRecordServiceImpl implements PersonalMeritRecordService {
    @Autowired
    private PersonalMeritRecordMapper personalMeritRecordMapper;

    @Autowired
    private DepartmentTypeService departmentTypeService;

    @Override
    public int deleteByPrimaryKey(Integer pId) {
        return personalMeritRecordMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public int insert(PersonalMeritRecord record) {
        DepartmentType departmentType=new DepartmentType();
        departmentType.setdId(Integer.parseInt(record.getdName()));
        departmentType=departmentTypeService.selectByPrimaryKey(departmentType.getdId());
        record.setdName(departmentType.getdName());
        return personalMeritRecordMapper.insert(record);
    }

    @Override
    public PersonalMeritRecord selectByPrimaryKey(Integer pId) {
        return personalMeritRecordMapper.selectByPrimaryKey(pId);
    }
    @Override
    public List<PersonalMeritRecord> selectAll() {
        return personalMeritRecordMapper.selectAll();
    }

    @Override
    public int count(String lMName) {
        return personalMeritRecordMapper.count(lMName);
    }

    @Override
    public List<PersonalMeritRecord> selectName(String lMName) {
        return personalMeritRecordMapper.selectName(lMName);
    }

    @Override
    public List<PersonalMeritRecord> selects(String dName, String beforeDate, String afterDate) {
        return personalMeritRecordMapper.selects(dName,beforeDate,afterDate);
    }

    @Override
    public int counts(String dName, String beforeDate, String afterDate) {
        return personalMeritRecordMapper.counts(dName,beforeDate,afterDate);
    }

    @Override
    public int updateByPrimaryKey(PersonalMeritRecord record) {
        return personalMeritRecordMapper.updateByPrimaryKey(record);
    }
}
