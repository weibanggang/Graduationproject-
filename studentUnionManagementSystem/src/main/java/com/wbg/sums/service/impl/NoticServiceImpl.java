package com.wbg.sums.service.impl;

import com.wbg.sums.dao.NoticMapper;
import com.wbg.sums.entity.Notic;
import com.wbg.sums.service.NoticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticServiceImpl implements NoticService {
    @Autowired
    private NoticMapper noticMapper;
    @Override
    public int deleteByPrimaryKey(Integer nId) {
        return noticMapper.deleteByPrimaryKey(nId);
    }

    @Override
    public int count(String status) {
        return noticMapper.count(status);
    }

    @Override
    public int insert(Notic record) {
        return noticMapper.insert(record);
    }

    @Override
    public Notic selectByPrimaryKey(Integer nId) {
        return noticMapper.selectByPrimaryKey(nId);
    }

    @Override
    public List<Notic> selectAll() {
        return noticMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Notic record) {
        return noticMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Notic> selectTitlestatus(String n_title, String status) {
        return noticMapper.selectTitlestatus(n_title,status);
    }

    @Override
    public List<Notic> selectMidStatus(int m_id, String status) {
        return noticMapper.selectMidStatus(m_id,status);
    }

    @Override
    public List<Notic> iselectList(String status) {
        return noticMapper.iselectList(status);
    }

    @Override
    public int updateStatus(int nId, String status) {
        return noticMapper.updateStatus(nId,status);
    }
}
