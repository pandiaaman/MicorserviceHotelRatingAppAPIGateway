package com.pandiaaman.APIGateway.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandiaaman.APIGateway.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			){
		
		logger.info("user email " + user.getEmail());
		
		AuthResponse authResp = new AuthResponse();
		
		authResp.setUserId(user.getEmail());
		authResp.setAccessToken(client.getAccessToken().getTokenValue());
		authResp.setRefreshToken(client.getRefreshToken().getTokenValue());
		authResp.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String> authorities = user.getAuthorities().stream().map(authority -> {
			return authority.getAuthority();
		}
		).collect(Collectors.toList());
		
		authResp.setAuthorities(authorities);
		
		return ResponseEntity.status(HttpStatus.OK).body(authResp);
	}
}
