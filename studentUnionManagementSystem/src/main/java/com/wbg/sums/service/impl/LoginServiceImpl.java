package com.wbg.sums.service.impl;

import com.wbg.sums.dao.MemberInformationMapper;
import com.wbg.sums.dao.VerificationCodeMapper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.MemberInformation;
import com.wbg.sums.entity.VerificationCode;
import com.wbg.sums.service.LoginService;
import com.wbg.sums.service.SmsPhone;
import com.wbg.sums.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private MemberInformationMapper memberInformationMapper;
    @Autowired
    private VerificationCodeMapper verificationCodeMapper;
    @Autowired
    private SmsPhone smsPhone;
    @Override
    public MemberInformation getByMUser(String mUser) {
        return memberInformationMapper.getByMUser(mUser);
    }

    /**
     * 使用账户密码登录
     * @param mUser
     * @param mPassword
     * @return
     */
    @Override
    public Result goLogin(String mUser, String mPassword) {
        MemberInformation memberInformation = getByMUser(mUser);
        if(memberInformation == null){
            return Result.error("账号无效");
        }
        if(memberInformation.getmPassword().equals(mPassword)){
            return Result.successMessage("登录成功", JWTUtil.sign(mUser, mPassword));
        }
        return Result.error("用户名密码错误");
    }

    /**
     * 用手机+验证码登录
     * @param phone
     * @param yzm
     * @return
     */
    @Override
    public Result yzm(String phone, int yzm) {
        VerificationCode verificationCode = verificationCodeMapper.selectLogin(phone,yzm);
        if(verificationCode == null){
            return  Result.error("验证码错误");
        }
        //获取用户
        MemberInformation memberInformation = memberInformationMapper.yzm(phone);
        return Result.successMessage("登录成功", JWTUtil.sign(memberInformation.getmUser(), memberInformation.getmPassword()));
    }

    @Override
    public Result getYzm(String phone) {
        //获取用户
        MemberInformation memberInformation = memberInformationMapper.yzm(phone);
        if(memberInformation == null){
            return new Result().error("手机号码无效！");
        }
        return new Result().success(smsPhone.send(phone));
    }
    @Override
    public Result logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {

        }
        return Result.successMessage("退出成功");
    }
}
