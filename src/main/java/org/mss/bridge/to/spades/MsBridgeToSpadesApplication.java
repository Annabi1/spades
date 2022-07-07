package org.mss.bridge.to.spades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient         // To enable eureka client
//@EnableFeignClients // To enable Open Feign client
public class MsBridgeToSpadesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBridgeToSpadesApplication.class, args);
	}
	

}
