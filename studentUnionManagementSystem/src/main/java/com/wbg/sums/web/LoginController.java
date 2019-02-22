package com.wbg.sums.web;

import com.wbg.sums.entity.Roles;
import com.wbg.sums.util.Result;
import com.wbg.sums.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

   /**
     *  账号密码登录
     * @param name
     * @param password
     * @return
     */
   @RequestMapping("/auth")
   public Result authLogin(String name, String password) {
       return loginService.goLogin(name,password);
    }
    /**
     *  获取验证码
     * @param phone
     * @return
     */
    @GetMapping("/getyzm")
    public Result getYzm(String phone) {
        return loginService.getYzm(phone);
    }
    /**
     *  手机号码登录
     * @param phone
     * @return
     */
    @GetMapping("/yzm")
    public Result yzm(String phone, int yzm) {
        return loginService.yzm(phone,yzm);
    }

    /**
     * 退出
     */
    @GetMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }
    /**
     * 身份验证不通过
     */
    @GetMapping("/verificationFailed")
    public Result verificationFailed() {
        return Result.error(502,"身份验证不通过");
    }

}
