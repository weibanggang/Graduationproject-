package com.wbg.sums.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: WBG
 * @description: 1、对没有登录的请求进行拦截
 * 2、Authorization 是前端放置在 headers 头文件中的登录标识，如果用户发起的请求是需要登录才能正常返回的，那么头文件中就必须存在该标识并携带有效值
 * @date: 2019/10/20 14:22
 */
@Component
public class JWTFilter extends BasicHttpAuthenticationFilter {

    // 登录标识
    private static String LOGIN_SIGN = "Authorization";

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 这里我们详细说明下返回是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)
     * 所以返回false拦截所有请求
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }
    /**
     * 检测用户是否登录
     * 检测header里面是否包含Authorization字段即可
     *  true的时候  就会进入executeLogin方法
     *  false的时候  就会进入onAccessDenied方法
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(LOGIN_SIGN);
        return authorization != null;
    }

    //未登录或者登录过期就会进入
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            //重定向主页
            res.sendRedirect("/login/verificationFailed");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * 检测header里面包含Authorization字段 进入
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)  {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader(LOGIN_SIGN);
        JWTToken token = new JWTToken(header);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(token);
        }catch (AuthenticationException e){
            //System.out.println(e);
            return false;
        }

        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


}
