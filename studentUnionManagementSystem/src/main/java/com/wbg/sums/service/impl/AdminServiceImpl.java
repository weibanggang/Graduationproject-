package com.wbg.sums.service.impl;

import com.wbg.sums.dao.AdminMapper;
import com.wbg.sums.entity.Admin;
import com.wbg.sums.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int deleteByPrimaryKey(Integer aId) {

        return adminMapper.deleteByPrimaryKey(aId);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int count(String status) {
        return adminMapper.count(status);
    }

    @Override
    public Admin selectByPrimaryKey(Integer aId) {
        return selectByPrimaryKey(aId);
    }

    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updatePassword(String a_username, String a_password) {
        return adminMapper.updatePassword(a_username,a_password);
    }

    @Override
    public int selectUserName(String a_username) {
        return adminMapper.selectUserName(a_username);
    }

    @Override
    public int updateStatus(String a_username, String status) {
        return adminMapper.updateStatus(a_username,status);
    }

    @Override
    public List<Admin> selectAllVague(String a_username) {
        return adminMapper.selectAllVague(a_username);
    }

    @Override
    public List<Admin> selectAllStatus(String status) {
        return adminMapper.selectAllStatus(status);
    }
}
