package com.seadun.helios;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.seadun.helios.mapper")
public class HeliosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeliosApplication.class, args);
	}
}
