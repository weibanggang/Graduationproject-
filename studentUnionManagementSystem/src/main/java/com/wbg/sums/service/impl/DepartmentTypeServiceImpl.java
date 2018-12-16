package com.wbg.sums.service.impl;

import com.wbg.sums.dao.DepartmentTypeMapper;
import com.wbg.sums.entity.DepartmentType;
import com.wbg.sums.service.DepartmentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentTypeServiceImpl implements DepartmentTypeService {
    @Autowired
    private DepartmentTypeMapper DepartmentTypeMapper;
    @Override
    public int deleteByPrimaryKey(Integer dId) {
        return DepartmentTypeMapper.deleteByPrimaryKey(dId);
    }

    @Override
    public int count(String status) {
        return DepartmentTypeMapper.count(status);
    }

    @Override
    public int insert(DepartmentType record) {
        return DepartmentTypeMapper.insert(record);
    }

    @Override
    public DepartmentType selectByPrimaryKey(Integer dId) {
        return DepartmentTypeMapper.selectByPrimaryKey(dId);
    }

    @Override
    public List<DepartmentType> selectAll() {
        return DepartmentTypeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(DepartmentType record) {
        return DepartmentTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateSort(int d_id, int d_sort) {
        return DepartmentTypeMapper.updateSort(d_id,d_sort);
    }

    @Override
    public int updateStatus(int d_id, String status) {
        return DepartmentTypeMapper.updateStatus(d_id,status);
    }

    @Override
    public int selectName(String d_name) {
        return DepartmentTypeMapper.selectName(d_name);
    }

    @Override
    public List<DepartmentType> selectAllStatus(String status) {
        return DepartmentTypeMapper.selectAllStatus(status);
    }

    @Override
    public List<DepartmentType> selectAllVague(String d_name) {
        return DepartmentTypeMapper.selectAllVague(d_name);
    }

    @Override
    public List<DepartmentType> iselectAllStatus(String status) {
        return DepartmentTypeMapper.iselectAllStatus(status);
    }
}
