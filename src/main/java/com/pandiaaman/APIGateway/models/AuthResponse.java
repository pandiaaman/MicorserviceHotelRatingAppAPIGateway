package com.pandiaaman.APIGateway.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String userId;
	
	private String accessToken;
	
	private String refreshToken;
	
	private long expireAt;
	
	private Collection<String> authorities;
}
