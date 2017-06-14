package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controllers.BBBController;

@SpringBootApplication
@ComponentScan(basePackageClasses = BBBController.class)
public class FirmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirmaApplication.class, args);
		
	}
}
