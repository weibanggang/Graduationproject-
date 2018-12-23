package com.wbg.sums.web.aspect;

import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.Jurisdiction;
import com.wbg.sums.service.JurisdictionService;
import com.wbg.sums.service.MemberInformationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;

@Component
@Aspect
@EnableAspectJAutoProxy
public class jurisdictionAdvice {

    @Autowired
    HttpSession httpSession;
    //用户
    @Autowired
    private MemberInformationService memberInformationService;
    //权限
    @Autowired
    private JurisdictionService jurisdictionService;

    public Result readOnly(ProceedingJoinPoint pj) throws Throwable {

             return (Result) pj.proceed();

  /*  //方法
        String method = pj.getSignature().getName();
        System.out.println(method);
        //获取用户
       String user = (String) httpSession.getAttribute("user");
       //模拟updateByPrimaryKey
       if(method.equals("updateByPrimaryKey")){
           user="100010003";
       }else {
           user="100010004";
       }
        //查询根据用户查询权限
       int jid = memberInformationService.selectJid(user);
        //获取权限
        System.out.println(jid);
        Jurisdiction jurisdiction = jurisdictionService.selectByPrimaryKey(jid);
        System.out.println(jurisdiction);
        //如果是最高管理
        if(jid == 1){
            try {
                return (Result) pj.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }//如果是中级管理 并方法是修改
        else if( jid == 2 && method.equals("updateByPrimaryKey")){
            try {
                return (Result) pj.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
            return new Result().error("权限不足");*/

    }
}
