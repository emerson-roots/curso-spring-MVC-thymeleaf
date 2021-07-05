package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 * foi utilizado como base a seguinte fonte para a implementação desta classe;
 * 
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * 
 * */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//endpoints abertos - para este vetor, permite todas requisições sem necessidade de authenticação
	private static final String[] PUBLIC_MATCHERS = {"/**"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		// desabilita proteção de ataques a CSRF pois nosso sistema sera "STATELESS"
		// que significa que nao armazena sessão de usuário
		http.cors().and().csrf().disable();

		// todos os caminhos que estiverem no vetor, será permitido
		// e para todo o resto exige autenticação
		http.authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated();
		
		// define que a aplicação é STATELESS - assegura que nosso back end nao cria
		// sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configs = new CorsConfiguration().applyPermitDefaultValues();
		configs.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configs);
		return source;
	}
}