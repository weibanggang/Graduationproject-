package com.wbg.sums.service.impl;

import com.wbg.sums.dao.LeaveRecordMapper;
import com.wbg.sums.entity.DepartmentType;
import com.wbg.sums.entity.LeaveRecord;
import com.wbg.sums.service.DepartmentTypeService;
import com.wbg.sums.service.LeaveRecordService;
import com.wbg.sums.web.DepartmentTypeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService {
    @Autowired
    private LeaveRecordMapper leaveRecordMapper;
    @Autowired
    private DepartmentTypeService departmentTypeService;
    @Override
    public int deleteByPrimaryKey(Integer lId) {
        return leaveRecordMapper.deleteByPrimaryKey(lId);
    }

    @Override
    public int count() {
        return leaveRecordMapper.count();
    }

    @Override
    public int insert(LeaveRecord record) {
        DepartmentType departmentType=new DepartmentType();
        departmentType.setdId(Integer.parseInt(record.getdName()));
         departmentType=departmentTypeService.selectByPrimaryKey(departmentType.getdId());
        record.setdName(departmentType.getdName());

        return leaveRecordMapper.insert(record);
    }

    @Override
    public LeaveRecord selectByPrimaryKey(Integer lId) {
        return leaveRecordMapper.selectByPrimaryKey(lId);
    }

    @Override
    public List<LeaveRecord> selectAll() {
        return leaveRecordMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(LeaveRecord record) {
        return leaveRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectName(String d_name, String p_date) {
        return leaveRecordMapper.selectName(d_name,p_date);
    }

    @Override
    public List<LeaveRecord> selectByMid(String m_user) {
        return leaveRecordMapper.selectByMid(m_user);
    }
}
