package org.aipeel.leemcp;

import org.aipeel.leemcp.service.shoppingcart.ShoppingCart;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LeemcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeemcpApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider shoppingCartTool(ShoppingCart shoppingCart) {
		return MethodToolCallbackProvider.builder().toolObjects(shoppingCart).build();
	}
}
