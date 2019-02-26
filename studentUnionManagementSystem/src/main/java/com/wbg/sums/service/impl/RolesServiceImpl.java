package com.wbg.sums.service.impl;

import com.wbg.sums.dao.RolesMapper;
import com.wbg.sums.dto.RolesAndPermissions;
import com.wbg.sums.entity.Roles;
import com.wbg.sums.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;
    @Override
    public int deleteByPrimaryKey(Integer bId) {
        return rolesMapper.deleteByPrimaryKey(bId);
    }

    @Override
    public int insert(Roles record) {
        return rolesMapper.insert(record);
    }

    @Override
    public Roles selectByPrimaryKey(Integer bId) {
        return rolesMapper.selectByPrimaryKey(bId);
    }

    @Override
    public List<Roles> selectAll() {
        return rolesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Roles record) {
        return rolesMapper.updateByPrimaryKey(record);
    }

    @Override
    public int count() {
        return rolesMapper.count();
    }

    @Override
    public List<RolesAndPermissions> rolesAndpermissions(String rName) {
        return rolesMapper.rolesAndpermissions(rName);
    }

    @Override
    public int counts(String rName) {
        return rolesMapper.counts(rName);
    }
}
