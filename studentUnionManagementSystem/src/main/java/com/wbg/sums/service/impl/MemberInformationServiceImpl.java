package com.wbg.sums.service.impl;

import com.wbg.sums.dao.MemberInformationMapper;
import com.wbg.sums.dto.HomeReport;
import com.wbg.sums.dto.MemberInfomationDto;
import com.wbg.sums.entity.MemberInformation;
import com.wbg.sums.service.MemberInformationService;
import com.wbg.sums.shiro.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInformationServiceImpl implements MemberInformationService {
    @Autowired
    private MemberInformationMapper memberInformationMapper;
    @Override
    public int deleteByPrimaryKey(Integer mId) {
        return memberInformationMapper.deleteByPrimaryKey(mId);
    }

    @Override
    public int insert(MemberInformation record) {
        return memberInformationMapper.insert(record);
    }

    @Override
    public MemberInformation selectByPrimaryKey(Integer mId) {
        return memberInformationMapper.selectByPrimaryKey(mId);
    }

    @Override
    public int count(String status) {
        return memberInformationMapper.count(status);
    }

    @Override
    public List<MemberInformation> selectAll() {
        return memberInformationMapper.selectAll();
    }


    @Override
    public List<MemberInfomationDto> selectTableAll(String status) {
        return memberInformationMapper.selectTableAll(status);
    }

    @Override
    public int updateByPrimaryKey(MemberInformation record) {
        return memberInformationMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updatePassword(String mUser,String mPassword ,String xPasssword) {
        return memberInformationMapper.updatePassword(mUser,mPassword,xPasssword);
    }

    @Override
    public List<HomeReport> homeReport() {
        return memberInformationMapper.homeReport();
    }


    @Override
    public int counts(int cId, int dId, int pId, int eId, String status, String mName) {
        return memberInformationMapper.counts(cId,dId,pId,eId,status,mName);
    }

    @Override
    public List<MemberInformation> selects(int cId, int dId, int pId, int eId,  String status,String mName) {
        return memberInformationMapper.selects(cId,dId,pId,eId,status,mName);
    }

    @Override
    public List<MemberInformation> selectListNameStatus(String m_name, String status) {
        return memberInformationMapper.selectListNameStatus(m_name,status);
    }

    @Override
    public int updatePhoto(int m_id, String photo) {
        return memberInformationMapper.updatePhoto(m_id,photo);
    }

    @Override
    public int updateStatus(int m_id, String status) {
        return memberInformationMapper.updateStatus(m_id,status);
    }

    @Override
    public List<MemberInformation> iSelectName(int pId) {
        return memberInformationMapper.iSelectName(pId);
    }

    @Override
    public List<MemberInformation> iUserName(String mUser) {
        return memberInformationMapper.iUserName(mUser);
    }

    @Override
    public int selectRid(String mUser) {
        return memberInformationMapper.selectRid(mUser);
    }

    @Override
    public MemberInformation getByMUser(String mUser) {
        return memberInformationMapper.getByMUser(mUser);
    }

    @Override
    public MemberInformation goLogin(String mUser, String mPassword) {
        return memberInformationMapper.goLogin(mUser,mPassword);
    }

    @Override
    public MemberInformation yzm(String mPhone) {
        return memberInformationMapper.yzm(mPhone);
    }

}
