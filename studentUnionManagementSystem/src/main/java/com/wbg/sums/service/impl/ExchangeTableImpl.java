package com.wbg.sums.service.impl;
import com.wbg.sums.dao.ExchangeTableMapper;
import com.wbg.sums.entity.ExchangeTable;
import com.wbg.sums.service.ExchangeTableService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeTableImpl implements ExchangeTableService {
    @Autowired
    private ExchangeTableMapper exchangeTableMapper;

    @Override
    public int deleteByPrimaryKey(Integer eId) {
        return exchangeTableMapper.deleteByPrimaryKey(eId);
    }

    @Override
    public int count(String status) {
        return exchangeTableMapper.count(status);
    }

    @Override
    public int insert(ExchangeTable record) {
        return exchangeTableMapper.insert(record);
    }

    @Override
    public ExchangeTable selectByPrimaryKey(Integer eId) {
        return exchangeTableMapper.selectByPrimaryKey(eId);
    }

    @Override
    public List<ExchangeTable> selectAll() {
        return exchangeTableMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ExchangeTable record) {
        return exchangeTableMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatus(int e_id, String status) {
        return exchangeTableMapper.updateStatus(e_id,status);
    }

    @Override
    public int updateSort(int e_id, int e_sort) {
        return exchangeTableMapper.updateSort(e_id,e_sort);
    }

    @Override
    public List<ExchangeTable> selectAllStatus(String status) {
        return exchangeTableMapper.selectAllStatus(status);
    }

    @Override
    public List<ExchangeTable> iselectAllStatus(String status) {
        return exchangeTableMapper.iselectAllStatus(status);
    }

    @Override
    public List<ExchangeTable> selectAllVague(String e_name) {
        return exchangeTableMapper.selectAllVague(e_name);
    }}
