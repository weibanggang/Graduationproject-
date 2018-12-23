package com.wbg.sums.service.impl;

import com.wbg.sums.dao.PersonalMeritRecordMapper;
import com.wbg.sums.entity.PersonalMeritRecord;
import com.wbg.sums.service.PersonalMeritRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalMeritRecordServiceImpl implements PersonalMeritRecordService {
    @Autowired
    private PersonalMeritRecordMapper personalMeritRecordMapper;
    @Override
    public int deleteByPrimaryKey(Integer pId) {
        return personalMeritRecordMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public int insert(PersonalMeritRecord record) {
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
    public int count() {
        return personalMeritRecordMapper.count();
    }

    @Override
    public int updateByPrimaryKey(PersonalMeritRecord record) {
        return personalMeritRecordMapper.updateByPrimaryKey(record);
    }
}
