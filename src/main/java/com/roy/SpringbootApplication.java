package com.roy;

import com.roy.config.ClassPathTldsLoader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

	//配置Security标签的支持，用于判断用户是否具有对应权限，从而控制其限制的内容
	@Bean
	@ConditionalOnMissingBean(ClassPathTldsLoader.class)
	public ClassPathTldsLoader classPathTldsLoader(){
		return new ClassPathTldsLoader();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
