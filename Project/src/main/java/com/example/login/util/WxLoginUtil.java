package com.example.login.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.login.constant.SystemConstant;
import com.example.login.entity.OpenIdAndUnionId;
import cn.hutool.core.text.StrFormatter;

public class WxLoginUtil {

    public static OpenIdAndUnionId wxLogin(String code){
        String getTokenOpenId = StrFormatter.format("https://api.weixin.qq.com/sns/jscode2session?appid={}&secret={}&js_code={}&grant_type=authorization_code", SystemConstant.APPID, SystemConstant.APPSECRET, code);
        String data = HttpUtil.get(getTokenOpenId);
//        System.out.println(data);
        OpenIdAndUnionId openIdAndUnionId = JSONUtil.toBean(data, OpenIdAndUnionId.class);
        return openIdAndUnionId;
    }
}
