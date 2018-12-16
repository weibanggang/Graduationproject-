package com.wbg.sums.service.impl;

import com.wbg.sums.dao.WorkArrangementMapper;
import com.wbg.sums.entity.WorkArrangement;
import com.wbg.sums.service.WorkArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkArrangementServiceImpl implements WorkArrangementService {
    @Autowired
    private WorkArrangementMapper workArrangementMapper;
    @Override
    public int deleteByPrimaryKey(Integer wId) {
        return workArrangementMapper.deleteByPrimaryKey(wId);
    }

    @Override
    public int insert(WorkArrangement record) {
        return workArrangementMapper.insert(record);
    }

    @Override
    public WorkArrangement selectByPrimaryKey(Integer wId) {
        return workArrangementMapper.selectByPrimaryKey(wId);
    }

    @Override
    public int counts() {
        return workArrangementMapper.counts();
    }

    @Override
    public List<WorkArrangement> selectAll() {
        return workArrangementMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(WorkArrangement record) {
        return workArrangementMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WorkArrangement> selectAllVagueTitle(String wTitle) {
        return workArrangementMapper.selectAllVagueTitle(wTitle);
    }

    @Override
    public List<WorkArrangement> selectAllVagueName(int dId) {
        return workArrangementMapper.selectAllVagueName(dId);
    }
}
