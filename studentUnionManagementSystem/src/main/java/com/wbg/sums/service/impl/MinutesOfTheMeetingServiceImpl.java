package com.wbg.sums.service.impl;

import com.wbg.sums.dao.MinutesOfTheMeetingMapper;
import com.wbg.sums.entity.MinutesOfTheMeeting;
import com.wbg.sums.entity.Notic;
import com.wbg.sums.service.MinutesOfTheMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutesOfTheMeetingServiceImpl implements MinutesOfTheMeetingService {
    @Autowired
    private MinutesOfTheMeetingMapper minutesOfTheMeetingMapper;
    @Override
    public int deleteByPrimaryKey(Integer mId) {
        return minutesOfTheMeetingMapper.deleteByPrimaryKey(mId);
    }

    @Override
    public int count() {
        return minutesOfTheMeetingMapper.count();
    }

    @Override
    public int insert(MinutesOfTheMeeting record) {
        return minutesOfTheMeetingMapper.insert(record);
    }

    @Override
    public MinutesOfTheMeeting selectByPrimaryKey(Integer mId) {
        return minutesOfTheMeetingMapper.selectByPrimaryKey(mId);
    }

    @Override
    public List<MinutesOfTheMeeting> selectAll() {
        return minutesOfTheMeetingMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MinutesOfTheMeeting record) {
        return minutesOfTheMeetingMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<MinutesOfTheMeeting> selects(String mTitle, int dId, String beforeDate, String afterDate) {
        return minutesOfTheMeetingMapper.selects(mTitle,dId,beforeDate,afterDate);
    }

    @Override
    public int counts(String mTitle, int dId, String beforeDate, String afterDate) {
        return minutesOfTheMeetingMapper.counts(mTitle,dId,beforeDate,afterDate);
    }


}
