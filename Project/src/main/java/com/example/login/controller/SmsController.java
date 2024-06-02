package com.example.login.controller;


import com.example.login.entity.Sms;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @PostMapping("/sendCode")
    public void sms(@RequestBody Sms sms){
        int appid = 0;  //腾讯云短信提供的SDK AppID
        String appkey = "";  //腾讯云短信提供的AppKey
        int templateId = 0;  //国内短信——正文模板管理部分的短信ID
        String smsSign = "";  //国内短信——签名管理部分的内容
        try{
            String[] params = {sms.getCode(), Integer.toString(sms.getMinute())};
            SmsSingleSender smsSingleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = smsSingleSender.sendWithParam("86", sms.getPhonenumber(), templateId, params, smsSign, "", "");
            System.out.println(result);

        }catch (HTTPException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
