package br.com.isea.centraldoacao.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Desabilita o CSRF
	        .authorizeHttpRequests(authorize -> authorize
	            //.requestMatchers("/api/usuarios/cadastrar").permitAll() // Permite acesso público a rotas específicas
	            .anyRequest().authenticated() // Protege os demais endpoints
	        )
	        .httpBasic(Customizer.withDefaults()); // Nova forma de habilitar a autenticação básica
	    return http.build();
	}
}
