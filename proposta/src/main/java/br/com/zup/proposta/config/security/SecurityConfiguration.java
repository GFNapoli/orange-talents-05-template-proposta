package br.com.zup.proposta.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/proposal/**").hasAuthority("Scope_propostas:read")
				.antMatchers(HttpMethod.POST, "/proposal/**").hasAuthority("Scope_propostas:write")
				.antMatchers(HttpMethod.POST, "/card/**").hasAuthority("Scope_cartao:write")
				.antMatchers(HttpMethod.GET, "/card/**").hasAuthority("Scope_cartao:read")
				.anyRequest().authenticated())
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}
