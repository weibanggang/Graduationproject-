package com.wbg.sums.service.impl;

import com.wbg.sums.dao.MemberInformationMapper;
import com.wbg.sums.dto.MemberInfomationDto;
import com.wbg.sums.entity.MemberInformation;
import com.wbg.sums.service.MemberInformationService;
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
    public int updatePassword(String m_user, String m_password) {
        return memberInformationMapper.updatePassword(m_user,m_password);
    }

    @Override
    public List<MemberInformation> selectList(String m_sex, int c_id, int d_id, int p_id, int e_id, String status) {
        return memberInformationMapper.selectList(m_sex,c_id,d_id,p_id,e_id,status);
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


}
