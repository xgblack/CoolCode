package com.xgblack.cool;

import com.xgblack.framework.security.core.annotation.EnableCoolResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCoolResourceServer
@SpringBootApplication
public class CoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolApplication.class, args);
	}

}
