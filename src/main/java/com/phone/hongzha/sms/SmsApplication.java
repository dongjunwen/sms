package com.phone.hongzha.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"file:sms-config.properties"})
public class SmsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(SmsApplication.class);
		//String phoneNum="18217337379";
		String phoneNum="18217004632";
		if(args!=null && args.length>=1){
			phoneNum=args[0];
		}
		//springApplication.addListeners(new YunHuListener(phoneNum));
		//springApplication.addListeners(new SmsListener(phoneNum));
		springApplication.run(args);
	}
}
