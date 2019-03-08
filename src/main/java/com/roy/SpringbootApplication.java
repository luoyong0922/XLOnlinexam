package com.roy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.roy.mapper")
@ImportResource("classpath:transaction.xml")//基于xml配置事务
public class SpringbootApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootApplication.class, args);
	}
}
