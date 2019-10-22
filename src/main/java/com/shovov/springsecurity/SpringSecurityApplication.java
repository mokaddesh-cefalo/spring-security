package com.shovov.springsecurity;

import com.shovov.springsecurity.model.Language;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityApplication.class, args);
	}


}
