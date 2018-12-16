package com.wbg.sums.service.impl;

import com.wbg.sums.dto.MemberInfomationDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class MemberInformationServiceImplTest {

    @Autowired
    private MemberInformationServiceImpl  memberInformationService;
    @Test
    public void deleteByPrimaryKey() {
        List<MemberInfomationDto> list=memberInformationService.selectTableAll("");
        for (MemberInfomationDto memberInfomationDto : list) {
            System.out.println(memberInfomationDto);
        }
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void selectTableAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void selectList() {
    }

    @Test
    public void selectListNameStatus() {
    }

    @Test
    public void updatePhoto() {
    }

    @Test
    public void updateStatus() {
    }
}