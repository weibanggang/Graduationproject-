package com.wbg.sums.service.impl;

import com.wbg.sums.dao.JurisdictionMapper;
import com.wbg.sums.entity.Jurisdiction;
import com.wbg.sums.service.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JurisdictionServiceImpl implements JurisdictionService {
    @Autowired
    private JurisdictionMapper jurisdictionMapper;
    @Override
    public int deleteByPrimaryKey(Integer jId) {
        return jurisdictionMapper.deleteByPrimaryKey(jId);
    }

    @Override
    public int count(String status) {
        return jurisdictionMapper.count(status);
    }

    @Override
    public int insert(Jurisdiction record) {
        return jurisdictionMapper.insert(record);
    }

    @Override
    public Jurisdiction selectByPrimaryKey(Integer jId) {
        return jurisdictionMapper.selectByPrimaryKey(jId);
    }

    @Override
    public List<Jurisdiction> selectAll() {
        return jurisdictionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Jurisdiction record) {
        return jurisdictionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateName(int j_id, String j_name) {
        return jurisdictionMapper.updateName(j_id,j_name);
    }

    @Override
    public int updateStatus(int j_id, String status) {
        return jurisdictionMapper.updateStatus(j_id,status);
    }

    @Override
    public List<Jurisdiction> selectAllStatus(String status) {
        return jurisdictionMapper.selectAllStatus(status);
    }

    @Override
    public List<Jurisdiction> iselectAllStatus(String status) {
        return jurisdictionMapper.iselectAllStatus(status);
    }

    @Override
    public List<Jurisdiction> selectAllVague(String j_name) {
        return jurisdictionMapper.selectAllVague(j_name);
    }

}
