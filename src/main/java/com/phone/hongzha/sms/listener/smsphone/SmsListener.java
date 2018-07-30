package com.phone.hongzha.sms.listener.smsphone;

import com.phone.hongzha.sms.util.HttpClientUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author:luiz
 * @Date: 2018/7/30 11:13
 * @Descripton:
 * @Modify :
 **/
public class SmsListener implements ApplicationListener<ContextRefreshedEvent> {


    private String phoneNum;
    public SmsListener(String phoneNum){
        this.phoneNum=phoneNum;
    }

    public void run(){

        for (ReqUrlEnum reqUrlEnum:ReqUrlEnum.values()){
            String postType= reqUrlEnum.getPostType();
            String postUrl= reqUrlEnum.getUrlStr();
            String postParam= reqUrlEnum.getParamValue();
            if("POSTJSON".equals(postType)){
                postParam=postParam.replace("手机号",phoneNum);
                String respData=HttpClientUtil.doPostJSon(postUrl,postParam);
                System.out.println(postUrl+"短信呼叫返回POSTJSON:"+respData);
            }else if("POST".equals(postType)){
                postParam=postParam.replace("手机号",phoneNum);
                String respData=HttpClientUtil.doPostStr(postUrl,postParam);
                System.out.println(postUrl+"短信呼叫返回POST:"+respData);
            }else{
                postUrl=postUrl.replace("手机号",phoneNum);
                String respData=HttpClientUtil.doGet(postUrl);
                System.out.println(postUrl+"短信呼叫返回:"+respData);
            }
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        run();
    }


}
