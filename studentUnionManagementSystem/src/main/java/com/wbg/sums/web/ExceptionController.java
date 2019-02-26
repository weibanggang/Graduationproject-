package com.wbg.sums.web;

import com.wbg.sums.util.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    public boolean insufficientAuthority(ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            //重定向主页
            res.sendRedirect("/login/insufficientAuthority");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
