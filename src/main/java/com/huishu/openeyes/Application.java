package com.huishu.openeyes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.huishu.openeyes.handler.OpeneyesHandler;
import com.huishu.openeyes.service.OpeneyesService;
import com.huishu.openeyes.service.OpeneyesServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.huishu.openeyes")
@EntityScan(basePackages = "com.huishu.openeyes.entity")
@EnableJpaRepositories(basePackages = "com.huishu.openeyes.repository")
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public OpeneyesService getOpeneyesService(){
		return new OpeneyesServiceImpl();
	}
	@Bean
	public OpeneyesHandler getOpeneyesHandler(OpeneyesService openeyesService){
		return new OpeneyesHandler(openeyesService);
	}
}
