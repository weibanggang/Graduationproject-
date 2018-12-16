package com.wbg.sums.service.impl;

import com.wbg.sums.dao.PositionTypeMapper;
import com.wbg.sums.dto.MemberInfomationDto;
import com.wbg.sums.entity.PositionType;
import com.wbg.sums.service.PositionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionTypeServiceImpl implements PositionTypeService {
    @Autowired
    private PositionTypeMapper positionTypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer pId) {
        return positionTypeMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public int insert(PositionType record) {
        return positionTypeMapper.insert(record);
    }

    @Override
    public PositionType selectByPrimaryKey(Integer pId) {
        return positionTypeMapper.selectByPrimaryKey(pId);
    }

    @Override
    public List<PositionType> selectAll() {
        return positionTypeMapper.selectAll();
    }


    @Override
    public int count(String status) {
        return positionTypeMapper.count(status);
    }

    @Override
    public int updateByPrimaryKey(PositionType record) {
        return positionTypeMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatus(int p_id, String status) {
        return positionTypeMapper.updateStatus(p_id,status);
    }

    @Override
    public int updateSort(int p_id, int p_sort) {
        return positionTypeMapper.updateSort(p_id,p_sort);
    }

    @Override
    public List<PositionType> selectAllStatus(String status) {
        return positionTypeMapper.selectAllStatus(status);
    }

    @Override
    public List<PositionType> iselectAllStatus(String status) {
        return positionTypeMapper.iselectAllStatus(status);
    }

    @Override
    public List<PositionType> selectAllVague(String p_name) {
        return positionTypeMapper.selectAllVague(p_name);
    }
}
