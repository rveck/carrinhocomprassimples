package br.com.veck.carrinho.configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class McvConfig implements WebMvcConfigurer {
	  
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {	        
		registry.addViewController("/").setViewName("home");	        
	}
}
