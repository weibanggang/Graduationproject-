package com.wbg.sums.web.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.SmsVoiceVerifyCodeSender;
import com.github.qcloudsms.SmsVoiceVerifyCodeSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        // 短信应用SDK AppID
        int appid = 1400179734; // 1400开头
        //1008

    // 短信应用SDK AppKey
        String appkey = "3a0bc264ef7caab1ecda60fef328b9b8";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {"13160656062", "18776203778"};

        // 短信模板ID，需要在短信应用中申请
        int templateId = 269200; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请


        try {

            String smsSign = "你的验证码为：110111，请于2分钟内填写。如非本人操作，请忽略本短信。"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", phoneNumbers[1],
                    smsSign, "", "");

            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            System.out.println("HTTP响应码错误");
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            System.out.println("json解析错误");
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            System.out.println(" 网络IO错误");
        }
    }
}
