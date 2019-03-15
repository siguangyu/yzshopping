package com.lynu;


import configration.BaseConfig;
import configration.WebMvcConfig;
import configuration.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class YzshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{
				YzshoppingApplication.class,
				BaseConfig.class,
				MyBatisConfig.class,
				WebMvcConfig.class
		}, args);
	}


}
