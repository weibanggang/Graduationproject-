package com.wbg.sums.service.impl;

import com.wbg.sums.dao.ClassTableMapper;
import com.wbg.sums.entity.ClassTable;
import com.wbg.sums.entity.ClassTable;
import com.wbg.sums.service.ClassTableService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassTableServiceImpl implements ClassTableService {
    @Autowired
    private ClassTableMapper classTableMapper;

    @Override
    public int deleteByPrimaryKey(Integer cId) {
        return classTableMapper.deleteByPrimaryKey(cId);
    }

    @Override
    public int count(String status) {
        return classTableMapper.count(status);
    }

    @Override
    public int insert(ClassTable record) {
        return classTableMapper.insert(record);
    }

    @Override
    public ClassTable selectByPrimaryKey(Integer cId) {
        return classTableMapper.selectByPrimaryKey(cId);
    }

    @Override
    public List<ClassTable> selectAll() {
        return classTableMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ClassTable record) {
        return classTableMapper.updateByPrimaryKey(record);
    }


    @Override
    public int updateName(int j_id, String c_name) {
        return classTableMapper.updateName(j_id,c_name);
    }

    @Override
    public int updateStatus(int c_id, String status) {
        return classTableMapper.updateStatus(c_id,status);
    }

    @Override
    public List<ClassTable> selectByName(String c_headmaster_name) {
        return classTableMapper.selectByName(c_headmaster_name);
    }

    @Override
    public List<ClassTable> selectAllStatus(String status) {
        return classTableMapper.selectAllStatus(status);
    }

    @Override
    public List<ClassTable> iselectAllStatus(String status) {
        return classTableMapper.iselectAllStatus(status);
    }
}
