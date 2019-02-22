package com.wbg.sums.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.wbg.sums.dao.VerificationCodeMapper;
import com.wbg.sums.entity.VerificationCode;
import com.wbg.sums.service.SmsPhone;
import com.wbg.sums.service.VerificationCodeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
public class SmsPhoneImpl implements SmsPhone {

    @Autowired
    private VerificationCodeMapper verificationCodeMapper;
    // 短信应用SDK AppID
    private final int appid = 1400179734; // 1400开头
    // 短信应用SDK AppKey
    private final String appkey = "3a0bc264ef7caab1ecda60fef328b9b8";
    // 短信模板ID，需要在短信应用中申请
    private final int templateId = 269200; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

    @Override
    public  String send(String phoneNumbers) {
        int yzm = random();
        SmsSingleSenderResult result = null;
        try {
            String smsSign = "你的验证码为：" + yzm + "，请于2分钟内填写。如非本人操作，请忽略本短信。"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
             result = ssender.send(0, "86", phoneNumbers,
                    smsSign, "", "");
             if(result.result == 0){
                 VerificationCode vc = new VerificationCode();
                 vc.setvPhone(phoneNumbers);
                 vc.setYzm(yzm);
                 vc.setvType("dl");
                 verificationCodeMapper.insert(vc);
             }
        } catch (HTTPException e) {
            //System.out.println("HTTP响应码错误");
            return result.toString();
        } catch (JSONException e) {
            //System.out.println("json解析错误");
            return result.toString();
        } catch (IOException e) {
            //System.out.println(" 网络IO错误");
            return result.toString();
        }
        return result.toString();
    }

    private static int random() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
         int [] c = new int[6];
        for (int i = 0; i < 6; i++) {
            c[i] = r.nextInt(9)+1;
            sb.append(c[i]);
        }
        return Integer.parseInt(sb.toString());

    }

}
