package com.wbg.sums.service.impl;

import com.wbg.sums.dao.TypeofMeetingMapper;
import com.wbg.sums.entity.TypeofMeeting;
import com.wbg.sums.service.TypeofMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeofMeetingServiceImpl implements TypeofMeetingService {
    @Autowired
    private TypeofMeetingMapper typeofMeetingMapper;
    @Override
    public int deleteByPrimaryKey(Integer tId) {
        return typeofMeetingMapper.deleteByPrimaryKey(tId);
    }

    @Override
    public int count(String status) {
        return typeofMeetingMapper.count(status);
    }

    @Override
    public int insert(TypeofMeeting record) {
        return typeofMeetingMapper.insert(record);
    }

    @Override
    public TypeofMeeting selectByPrimaryKey(Integer tId) {
        return typeofMeetingMapper.selectByPrimaryKey(tId);
    }

    @Override
    public List<TypeofMeeting> selectAll() {
        return typeofMeetingMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TypeofMeeting record) {
        return typeofMeetingMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateStatus(int t_id, String status) {
        return typeofMeetingMapper.updateStatus(t_id,status);
    }

    @Override
    public List<TypeofMeeting> selectAllStatus(String status) {
        return null;
    }

    @Override
    public List<TypeofMeeting> iselectAllStatus(String status) {
        return typeofMeetingMapper.iselectAllStatus(status);
    }

    @Override
    public int updateSort(int t_id, int t_sort) {
        return typeofMeetingMapper.updateSort(t_id,t_sort);
    }

    @Override
    public List<TypeofMeeting> selectAllVague(String t_name) {
        return typeofMeetingMapper.selectAllVague(t_name);
    }
}
