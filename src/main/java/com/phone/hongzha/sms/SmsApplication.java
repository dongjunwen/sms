package com.phone.hongzha.sms;

import com.phone.hongzha.sms.voip.YunHuListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(SmsApplication.class);
		springApplication.addListeners(new YunHuListener(args[0]));
		springApplication.run(args);
	}
}
