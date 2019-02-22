package com.wbg.sums.service;

import com.wbg.sums.util.Result;
import com.wbg.sums.entity.MemberInformation;

public interface LoginService {
    /**
     * 根据工作编号获取信息
     * @param mUser
     * @return
     */
    MemberInformation getByMUser(String mUser);
    /**
     * 根据账号密码获取信息
     * @param mUser
     * @param mPassword
     * @return
     */
    Result goLogin(String mUser,String mPassword);
    /**
     * 根据手机号码+验证码 获取信息
     * @param phone
     * @return
     */
    Result yzm(String phone, int yzm);
    /**
     * 根据手机号码 获取信息验证码
     * @param phone
     * @return
     */
    Result getYzm(String phone);
    /**
     * 退出登录
     */
    Result logout();

}
