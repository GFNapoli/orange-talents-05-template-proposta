package br.com.zup.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/proposal/**").hasAuthority("Scope_propostas:read")
				.antMatchers(HttpMethod.POST, "/proposal/**").hasAuthority("Scope_propostas:write")
				.antMatchers(HttpMethod.POST, "/card/**").hasAuthority("Scope_cartao:write")
				.antMatchers(HttpMethod.GET, "/card/**").hasAuthority("Scope_cartao:read")
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.anyRequest().authenticated())
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}

}
