package com.phone.hongzha.sms.listener.voip;

import com.alibaba.fastjson.JSONObject;
import com.phone.hongzha.sms.util.HttpClientUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashMap;
import java.util.Map;

public class YunHuListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("yunhu-head-url")
    private  final String urlHead="http://www.yunhu121.com/i/2117718731/?";
    private  final String loginUrl=urlHead+"m=passport&a=login";
    private  final String sndUrl=urlHead+"m=index&a=status";
    private  final String logOutUrl=urlHead+"?m=index&a=stop";
    private  final String startUrl=urlHead+"?m=index&a=index";
    private  String cookieStr="";
    private String phoneNum="18217337379";

    public YunHuListener(String phoneNum){
        this.phoneNum=phoneNum;
    }

    public  void run(){
        login();
        JSONObject jsonObject=JSONObject.parseObject(start());
        System.err.println("开始执行:"+jsonObject);
        boolean status=jsonObject.getBoolean("status");
        if(status){
            while(1==1){
                String sndResp=send();
                System.err.println("获取执行状态"+sndResp);
                try {
                    Thread.sleep(1000*30);
                } catch (InterruptedException e) {
                    System.err.println("休息30秒发生异常"+e.getMessage());
                }
            }

        }
    }

    public   String login(){
        Map loginMap=new HashMap<>();
        loginMap.put("username","diyuwumen");
        loginMap.put("password","123QWEasd");
        //1.登录
        CloseableHttpResponse response= HttpClientUtil.doPostResp(loginUrl,loginMap);

        //获取响应文本中的"Set-Cookie"值
         Header[] cookieHeader=response.getHeaders("Set-Cookie");
         for(int i=0;i<=cookieHeader.length-1;i++){
             String headValue=cookieHeader[i].getValue();
             String[] headValueArr=headValue.split(";");
             for(int j=0;j<=headValueArr.length-1;j++){
                 String cookiStr=headValueArr[j];
                 if(cookiStr.contains("yunsuo_session_verify") || cookiStr.contains("PHPSESSID") ||  cookiStr.contains("XRgOsjmember_auth")){
                     cookieStr=cookieStr+cookiStr+";";
                 }
             }
         }
         System.out.println("cookieStr======="+cookieStr);
        return cookieStr;
    }

    public   String start(){
        Map startMap=new HashMap<>();
        startMap.put("phone["+phoneNum+"]",phoneNum);
        startMap.put("show_type","1");
        startMap.put("call_type","1");
        String respData=HttpClientUtil.doPost(startUrl,startMap,cookieStr);
        JSONObject jsonObject=JSONObject.parseObject(respData);
        String codeStr=jsonObject.getString("code");
        if("400001".equals(codeStr)){
            stop();
        }
        respData=HttpClientUtil.doPost(startUrl,startMap,cookieStr);
        return respData;
    }

    public   String stop(){
        Map startMap=new HashMap<>();
        String respData=HttpClientUtil.doPost(logOutUrl,startMap,cookieStr);
        return respData;
    }



    public   String send(){
        Map startMap=new HashMap<>();
        startMap.put("show_type","1");
        startMap.put("call_type","1");
        String respData=HttpClientUtil.doPost(sndUrl,startMap,cookieStr);
        return respData;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        run();
    }
}
