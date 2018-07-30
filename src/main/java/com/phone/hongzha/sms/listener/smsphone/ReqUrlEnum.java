package com.phone.hongzha.sms.listener.smsphone;

/**
 * @Author:luiz
 * @Date: 2018/7/30 11:31
 * @Descripton:
 * @Modify :
 **/
public enum ReqUrlEnum {
    URL001("http://www.huobiotc365.com/api/code","{\"username\":\"手机号\",\"type\":\"1\"}","POSTJSON"),
    URL009("http://m.h231.com/Home/User/sendMsg","typeid=1&phone=手机号","POST"),
     URL002("https://reg.qun.hk/v2/captcha/send?phone=手机号&sign=3aeea8da75c84858ad6e9dc5729b01bd&product=qun","","GET"),
    URL005("http://wk559.com/sendsm.asp","c=reg&uphone=手机号&code=","POST"),


    //URL008("http://b2b59.com/ajax.php","moduleid=2&action=member&job=mobile&value=手机号","POST"),
    //URL010("http://www.imfaka.com/register/sms?phone=手机号&t=1532134996417","","GET"),
    //URL006("http://www.datacaciques.com/auth/api_verifySendCode?mobile=手机号&email=&geetest_challenge=&geetest_validate=&geetest_seccode=","","GET"),
   // URL007("http://m.baibaod.com/h5/%E7%94%A8%E6%88%B7/%E5%8F%91%E9%80%81%E9%AA%8C%E8%AF%81%E7%A0%81%E5%AF%B9%E5%A4%96%E7%BC%96%E5%8F%B7=&%E6%89%8B%E6%9C%BA=手机号","","GET"),

    ;

     String urlStr;
     String postType;
     String paramValue;
    ReqUrlEnum(String urlStr,String paramValue,String postType){
        this.urlStr=urlStr;
        this.paramValue=paramValue;
        this.postType=postType;
    }

    public String getUrlStr() {
        return urlStr;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
