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
    public int count(String name, String url) {
        return jurisdictionMapper.count(name,url);
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
    public int updateUrl(int j_id, String url) {
        return jurisdictionMapper.updateUrl(j_id,url);
    }

    @Override
    public List<Jurisdiction> selectName(String name) {
        return jurisdictionMapper.selectName(name);
    }

}
