package com.example.xb.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtProperties {
	@Value("${token.secret}")
	private String secretKey ;

	@Value("${token.expireTime}")
	private long validityInMs ; 
}
