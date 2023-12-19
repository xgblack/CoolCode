package com.xgblack.cool;

import com.feiniaojin.gracefulresponse.EnableGracefulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGracefulResponse
@SpringBootApplication
public class CoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolApplication.class, args);
	}

}
