package com.phone.hongzha.sms.voip;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.phone.hongzha.sms.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class YunHu {
    private static final String loginUrl="http://www.yunhu100.com/i/2117718731/?m=passport&a=login";
    private static final String sndUrl="http://www.yunhu100.com/i/2117718731/?m=index&a=status";
    private static final String logOutUrl="http://www.yunhu100.com/i/2117718731/?m=index&a=stop";
    private static final String startUrl="http://www.yunhu100.com/i/2117718731/?m=index&a=index";

    public static void main(String args[]){

        //2.请求
        JSONObject jsonObject=null;
        try{
            jsonObject=JSONObject.parseObject(start());
        }catch (JSONException jsonException){
            login();
        }
        jsonObject=JSONObject.parseObject(start());
        boolean status=jsonObject.getBoolean("status");
        System.err.println(jsonObject);
        if(status){
            String sndResp=HttpClientUtil.doPost(sndUrl);
            System.err.println(sndResp);
        }
    }

    public static  void login(){
        Map loginMap=new HashMap<>();
        loginMap.put("username","diyuwumen");
        loginMap.put("password","123QWEasd");
        //1.登录
         HttpClientUtil.doPost(loginUrl,loginMap);
    }

    public static  String start(){
        Map startMap=new HashMap<>();
        startMap.put("phone[18217337379]","18217337379");
        startMap.put("show_type","1");
        startMap.put("call_type","1");
        String respData=HttpClientUtil.doPost(startUrl,startMap);
        return respData;
    }

}
