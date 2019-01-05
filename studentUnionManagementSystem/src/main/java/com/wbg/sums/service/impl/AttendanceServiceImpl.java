package com.wbg.sums.service.impl;

import com.wbg.sums.dao.AttendanceMapper;
import com.wbg.sums.entity.Attendance;
import com.wbg.sums.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public int deleteByPrimaryKey(Integer aId) {
        return attendanceMapper.deleteByPrimaryKey(aId);
    }

    @Override
    public int count() {
        return attendanceMapper.count();
    }

    @Override
    public int insert(Attendance record) {
        return attendanceMapper.insert(record);
    }

    @Override
    public Attendance selectByPrimaryKey(Integer aId) {
        return null;
    }

    @Override
    public List<Attendance> selectAll() {
        return attendanceMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Attendance record) {
        return attendanceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Attendance> selects(String aMName, String dName, String mName, String beforeDate, String afterDate) {
        return attendanceMapper.selects(aMName,dName,mName,beforeDate,afterDate);
    }
    @Override
    public int counts(String aMName, String dName, String mName, String beforeDate, String afterDate) {
        return attendanceMapper.counts(aMName,dName,mName,beforeDate,afterDate);
    }
}
