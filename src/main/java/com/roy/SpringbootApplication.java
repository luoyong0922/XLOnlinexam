package com.roy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@MapperScan("com.roy.mapper")
@ImportResource("classpath:transaction.xml")//基于xml配置事务
public class SpringbootApplication {

	/**
	 * 注入ServerEndpointExporter之后，
	 *
	 * 这个bean会自动注册使用了@ServerEndpoint注解声明的websocket
	 *
	 */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
