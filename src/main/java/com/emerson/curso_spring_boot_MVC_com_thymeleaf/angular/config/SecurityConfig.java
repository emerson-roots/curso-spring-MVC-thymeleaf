package com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.JWTAuthenticationFilter;
import com.emerson.curso_spring_boot_MVC_com_thymeleaf.angular.security.JWTUtil;

/**
 * foi utilizado como base a seguinte fonte para a implementação desta classe;
 * 
 * https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 * 
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	/** endpoints abertos - para este vetor, permite todas requisições sem
	 * necessidade de autenticação
	 * 
	 * foi autorizado qualquer requisição
	 * para os endpoints implementados no curso
	 * qualquer implementação fora do curso passará pelo processo de
	 * verificação de autorização 
	 * 
	 * ATENÇÃO: o indice "/**" permite todas requisições GET para o frontend ANGULAR também*/
	private static final String[] PUBLIC_MATCHERS_THYMELEAF = { 
			"/departamentos/**", 
			"/cargos/**", 
			"/funcionarios/**", 
			"/webjars/**", 
			"/css/**", 
			"/image/**",
			"/**"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();

		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS_THYMELEAF).permitAll()
			.anyRequest().authenticated();
		
		// adiciona filtro de autenticacao
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));

		// define que a aplicação é STATELESS - assegura que nosso back end nao cria
		// sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configs = new CorsConfiguration().applyPermitDefaultValues();
		configs.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configs);
		return source;
	}

	// sobrecarga de metodo que trata a autênticação
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}