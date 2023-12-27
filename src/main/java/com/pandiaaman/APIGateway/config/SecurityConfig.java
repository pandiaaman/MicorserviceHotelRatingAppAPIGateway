package com.pandiaaman.APIGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity //since we added the dependency of webflux in pom for this project to use security features
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
			.authorizeExchange()
			.anyExchange()
			.authenticated()
			.and()
			.oauth2Login()
			.and()
			.oauth2ResourceServer()
			.jwt();
		
		return httpSecurity.build();
	}
}
