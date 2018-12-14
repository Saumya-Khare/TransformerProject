package com.sk.transformer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sk.transformer.util.TransformerService;

@Configuration
public class TransformerConfiguration {
	
	@Bean
	public TransformerService transferService() {
		return new TransformerService();
	}
	

}
