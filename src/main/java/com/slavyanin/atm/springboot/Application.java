package com.slavyanin.atm.springboot;

import com.slavyanin.atm.springboot.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {Application.class, JpaConfig.class}, args);
	}
}
