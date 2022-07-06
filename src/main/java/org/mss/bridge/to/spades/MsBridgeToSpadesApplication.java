package org.mss.bridge.to.spades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
//@EnableEurekaClient         // To enable eureka client
//@EnableFeignClients // To enable Open Feign client
public class MsBridgeToSpadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBridgeToSpadesApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
		
	}

}
